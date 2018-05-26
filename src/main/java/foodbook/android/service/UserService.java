package foodbook.android.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import foodbook.android.model.City;
import foodbook.android.model.Country;
import foodbook.android.model.Reservation;
import foodbook.android.model.ReservationTable;
import foodbook.android.model.Restaurant;
import foodbook.android.model.RestaurantTable;
import foodbook.android.model.User;
import foodbook.android.model.enumerations.RestaurantType;
import foodbook.android.repository.CityRepository;
import foodbook.android.repository.CountryRepository;
import foodbook.android.repository.ReservationRepository;
import foodbook.android.repository.ReservationTableRepository;
import foodbook.android.repository.RestaurantRepository;
import foodbook.android.repository.RestaurantTableRepository;
import foodbook.android.repository.UserRepository;
import foodbook.android.rest.dto.LoginDTO;
import foodbook.android.rest.dto.ReservationRequestDTO;
import foodbook.android.rest.dto.ReservationResponseDTO;
import foodbook.android.rest.dto.UserDTO;


@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MailService mailManager;

	@Autowired
	private CountryRepository countryRepository; 
	
	@Autowired
	private CityRepository cityRepository; 
	
	@Autowired
	private RestaurantRepository restaurantRepository; 
	
	@Autowired
	private RestaurantTableRepository restaurantTableRepository; 
	
	@Autowired
	private ReservationTableRepository reservationTableRepository; 
	
	@Autowired
	private ReservationRepository reservationRepository; 
	
	/**
	 * Request BCrypt2 encoder
	 * 
	 * @return
	 */
	//@Autowired
	//private PasswordEncoder passwordEncoder;

	public User registerUser(User user) {
		
		City city = cityRepository.findByName("Novi Sad"); 
		Country country = countryRepository.findByName("Republika Srbija"); 
		User newUser = new User(); 
		newUser.setPassword(user.getPassword());
		newUser.setAddress(user.getAddress());
		newUser.setCity(city);
		newUser.setConfirmed_mail(false);
		newUser.setContact("");
		newUser.setEmail(user.getEmail());
		newUser.setName(user.getName());
		newUser.setSurname(user.getSurname());
		newUser.setUsername(user.getUsername());
		
		User saved = userRepository.save(newUser); 
		
		System.out.println(saved);

		if (saved != null) {
			System.out.println("MAIL SENT TO " + newUser.getEmail());
			mailManager.sendMail(newUser);

			return saved;
		}
		return null;
	}
	
	
	public boolean verifyUser(long id) {
		User user = userRepository.findOne(id);
		System.out.println("********************************");
		System.out.println(id);
		System.out.println("********************************");
		
		if (user == null) {
			return false;
		}
		user.setConfirmed_mail(true);
		userRepository.save(user);
		return true;
	}
	/*
	public ProfilePageDTO getProfilePageInfo(Long id) {
		ProfilePageDTO dto = new ProfilePageDTO();

		Guest guest = guestRepository.findOne(id);
		if (guest == null) {
			return null;
		}
		dto.setName(guest.getName());
		dto.setSurname(guest.getSurname());
		dto.setAddress(guest.getAddress());
		long numberOfVisits = 0;
		numberOfVisits += reservationRepository.countByGuest(guest);
		numberOfVisits += invitedToReservationRepository.countByGuest(guest);
		dto.setNumberOfVisits(numberOfVisits);
		List<Guest> friends = guest.getFriends();
		List<ProfilePageDTO> friendsDTO = new ArrayList<>();
		for (Guest friend : friends) {
			ProfilePageDTO friendDTO = new ProfilePageDTO();
			friendDTO.setId(friend.getId());
			friendDTO.setName(friend.getName());
			friendDTO.setSurname(friend.getSurname());
			friendsDTO.add(friendDTO);

		}
		dto.setFriends(friendsDTO);
		return dto;
	}*/


	public UserDTO loginUser(LoginDTO dto) {
		User user = userRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword()); 
		if(!user.isConfirmed_mail()) {
			return null; 
		}
		UserDTO userDto = null; 
		if(user != null)
			userDto = new UserDTO(user.getName(), user.getSurname());
		return userDto; 
	}


	public List<ReservationResponseDTO> getFreeRestaurantsForReservation(ReservationRequestDTO dto) {
		
		City city = cityRepository.findByName(dto.getCity()); 
		
		List<Restaurant> restaurants = restaurantRepository.findByCityAndType(city, RestaurantType.toEnum(dto.getCuisine()));
		Date beginDate = dto.getDate(); 
		int hours = Integer.parseInt(dto.getBegin().split(":")[0]); 
		int minutes = Integer.parseInt(dto.getBegin().split(":")[1]); 
		beginDate.setHours(hours);
		beginDate.setMinutes(minutes);
		Date endDate = new Date(beginDate.getTime()+dto.getDuration()); 
		
		//konacni korak --> dobavi sve slobodne stolove jednog restorana za odredjeni datum i odredjeno vreme(interval) 
		
		//1. za dati restoran dobavi sve rezervacije za taj interval
		
		
		
		/*
		Restoran in Restorans
		  List<Stol> sviStolovi = getSviStolovi(Restoran)
		  List<Rezervacija> rezervacija = getSveRezervacijeUTerminu(Restoran, pocetak, kraj);
		  List<Stol> rezervisaniStolovi = getSveRezervisaneStolove(rezervacije);
					     ->> listStolova = []
		                             ->>   for Rezervacija in Rezervacije
							listaStolova.addAll(revervazija.getStolovi());

		 List<Stol> slobodni =  sviStolovi.filter(rezervisani);
	*/
		List<ReservationResponseDTO> responseDtos = new ArrayList<>(); 
		
		for(Restaurant restaurant : restaurants) {
			List<RestaurantTable> allTables = getAllTables(restaurant); 
			List<Reservation> reservations = getAllReservationsInInterval(restaurant, beginDate, endDate); 
			List<RestaurantTable> reservedTables = getAllReservedTables(reservations); 
			
	
			List<RestaurantTable> availableTables = filterTables(allTables, reservedTables); 
			int availableSeats = getTotalSeats(availableTables); 
			
			if(availableSeats >= dto.getSeats()) {
				ReservationResponseDTO responseDto = new ReservationResponseDTO(); 
				responseDto.setAbout("");
				responseDto.setImageUrl(restaurant.getImageUrl());
				responseDto.setRestaurantId(restaurant.getId()); 
				responseDto.setRestaurantName(restaurant.getName());
				
				responseDtos.add(responseDto); 
			}
		}
		
		
		return responseDtos;
	}

	private int getTotalSeats(List<RestaurantTable> availableTables) {
		int total = 0; 
		
		for(RestaurantTable rt : availableTables) {
			total += rt.getMax_seats(); 
		}
		
		return total; 
	}


	private List<RestaurantTable> filterTables(List<RestaurantTable> allTables, List<RestaurantTable> reservedTables) {
		List<RestaurantTable> availableTables = new ArrayList<>(allTables); 
		
		
		for(RestaurantTable rt : allTables) {		
			for(RestaurantTable rr : reservedTables) {
				if(rt.getId() == rr.getId()) {
					availableTables.remove(rt); 
				}
			}
		}
		
		return availableTables; 
	}


	private List<RestaurantTable> getAllReservedTables(List<Reservation> reservations) {
		
		List<RestaurantTable> tables = new ArrayList<>();
		
		for(Reservation r : reservations) {
			List<RestaurantTable> rt = getAllTablesFromReservation(r); 
			tables.addAll(rt); 
		}
		
		return tables; 
	}

	private List<RestaurantTable> getAllTablesFromReservation(Reservation r) {
		List<RestaurantTable> tables = new ArrayList<>(); 
		List<ReservationTable> reserved = reservationTableRepository.findByReservation(r); 
		for(ReservationTable rt : reserved) {
			tables.add(rt.getRestaurantTable()); 
		}
		return tables;
	}


	private List<Reservation> getAllReservationsInInterval(Restaurant restaurant, Date beginDate, Date endDate) {
		List<Reservation> beginIncluded = reservationRepository.findByRestaurantAndBeginBetween(restaurant, beginDate,  endDate);
		List<Reservation> endIncluded = reservationRepository.findByRestaurantAndEndBetween(restaurant, beginDate,  endDate);
		
		beginIncluded.addAll(endIncluded); 		
		
		return beginIncluded;
	}


	private List<RestaurantTable> getAllTables(Restaurant restaurant) {
		List<RestaurantTable> allTables = restaurantTableRepository.findByRestaurant(restaurant); 
		return allTables; 
	}
	
}
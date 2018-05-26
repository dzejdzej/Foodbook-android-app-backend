package foodbook.android.rest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import foodbook.android.model.City;
import foodbook.android.model.Country;
import foodbook.android.model.Restaurant;
import foodbook.android.model.RestaurantTable;
import foodbook.android.model.User;
import foodbook.android.model.enumerations.RestaurantType;
import foodbook.android.repository.CityRepository;
import foodbook.android.repository.CountryRepository;
import foodbook.android.repository.RestaurantRepository;
import foodbook.android.repository.RestaurantTableRepository;
import foodbook.android.repository.UserRepository;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	CountryRepository countryRepository; 
	
	@Autowired
	CityRepository cityRepository; 
	
	@Autowired
	UserRepository userRepository; 
	
	@Autowired
	RestaurantRepository restaurantRepository; 
	
	@Autowired
	RestaurantTableRepository restaurantTableRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/fill-database")
	public ResponseEntity<?> getProfilePageInfo() {
		
		Country country1 = new Country(); 
		country1.setCode("RS");
		country1.setName("Republika Srbija");
		country1 = countryRepository.save(country1); 
		
		City city1 = new City(); 
		city1.setCountry(country1);
		city1.setName("Novi Sad");
		city1.setPostal_code("21000");
		city1 = cityRepository.save(city1); 
		
		City city2 = new City(); 
		city2.setCountry(country1);
		city2.setName("Beograd");
		city2.setPostal_code("11000");
		city2 = cityRepository.save(city2); 
		
		User user1 = new User(); 
		user1.setAddress("Zeleznicka 19");
		user1.setCity(city1);
		user1.setConfirmed_mail(true);
		user1.setContact("0631652267");
		user1.setEmail("jelena@gmail.com");
		user1.setName("Jelena");
		user1.setPassword("j");
		user1.setSurname("Jankovic");
		user1.setUsername("j");
		user1 = userRepository.save(user1); 
		
		Restaurant r1 = new Restaurant(); 
		r1.setAddress("");
		r1.setCity(city1);
		r1.setContact("");
		r1.setImageUrl("/img/mcdonalds.png");
		r1.setName("McDonald's");
		r1.setReservations(null);
		r1.setReviews(null);
		r1.setTables(new ArrayList<RestaurantTable>());
		r1.setType(RestaurantType.CHINESE);
		r1 = restaurantRepository.save(r1); 
		
		RestaurantTable rt1 = new RestaurantTable(); 
		rt1.setMax_seats(4);
		rt1.setNumber("1");
		rt1.setRestaurant(r1);
		rt1 = restaurantTableRepository.save(rt1); 
		
		RestaurantTable rt2 = new RestaurantTable(); 
		rt2.setMax_seats(4);
		rt2.setNumber("2");
		rt2.setRestaurant(r1);
		rt2 = restaurantTableRepository.save(rt2); 
		
		RestaurantTable rt3 = new RestaurantTable(); 
		rt3.setMax_seats(4);
		rt3.setNumber("3");
		rt3.setRestaurant(r1);
		rt3 = restaurantTableRepository.save(rt3); 
		
		r1.getTables().add(rt1);
		r1.getTables().add(rt2); 
		r1.getTables().add(rt3); 
		r1 = restaurantRepository.save(r1); 
		
		
		System.out.println("DATABASE FILLED");
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

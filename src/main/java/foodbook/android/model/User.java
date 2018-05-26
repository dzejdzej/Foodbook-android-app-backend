package foodbook.android.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id; 
	
	@Column(name="user_name", nullable = false)
	private String name;
	
	@Column(name="user_surname", nullable = false)
	private String surname;
	
	@Column(name="user_password", nullable = false)
	private String password;
	
	@Column(name="user_email", nullable = true, unique=true)
	private String email; 
	
	@Column(name="user_username", nullable = false)
	private String username; 
	
	@Column(name="user_address", nullable = true)
	private String address; 
	
	@Column(name="user_contact", nullable = true)
	private String contact;
	
	@Column(name="user_confirmed_mail", nullable = true)
	private boolean confirmed_mail;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	 @JoinTable(
	   name = "userfriends", 
	   joinColumns = @JoinColumn(name = "user_id"), 
	   inverseJoinColumns = @JoinColumn(name = "friend_id")
	 )
	private List<User> friends = new ArrayList<User>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<Review> reviews = new ArrayList<Review>();
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
   	private List<Reservation> reservations = new ArrayList<Reservation>();
       
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant", nullable = true)
    private Restaurant restaurant;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city", nullable = true)
   	private City city; 
	
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
   	private List<InvitedToReservation> invited = new ArrayList<InvitedToReservation>();
       
    
	public User() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public boolean isConfirmed_mail() {
		return confirmed_mail;
	}

	public void setConfirmed_mail(boolean confirmed_mail) {
		this.confirmed_mail = confirmed_mail;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}	
}
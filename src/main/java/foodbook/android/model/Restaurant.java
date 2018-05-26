package foodbook.android.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import foodbook.android.model.enumerations.RestaurantType;

@Entity
@Table(name="restaurant")
@Inheritance(strategy = InheritanceType.JOINED)
public class Restaurant implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id; 

	@Column(name="restaurant_name", nullable = false)
	private String name;
	
	@Column(name="restaurant_address")
	private String address; 
	
	@Column(name="restaurant_type")
	@Enumerated(EnumType.STRING)
	private RestaurantType type;
	
	@Column(name="restaurant_contact", nullable = false)
	private String contact;
	
	@Column(name="image_url", nullable = true)
	private String imageUrl;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "city", nullable = false)
	private City city;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<Review> reviews = new ArrayList<Review>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<RestaurantTable> tables = new ArrayList<RestaurantTable>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<Reservation> reservations = new ArrayList<Reservation>();
	
	public Restaurant() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public RestaurantType getType() {
		return type;
	}

	public void setType(RestaurantType type) {
		this.type = type;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<RestaurantTable> getTables() {
		return tables;
	}

	public void setTables(List<RestaurantTable> tables) {
		this.tables = tables;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}	
	
	
}

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="city")
@Inheritance(strategy = InheritanceType.JOINED)
public class City implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id; 
	
	@Column(name="city_name", nullable = false)
	private String name;
	
	@Column(name="city_postal_code", nullable = false)
	private String postal_code;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country", nullable = false)
	private Country country;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<User> users = new ArrayList<User>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<Restaurant> restaurants = new ArrayList<Restaurant>();
	
	public City() {
		
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

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

}
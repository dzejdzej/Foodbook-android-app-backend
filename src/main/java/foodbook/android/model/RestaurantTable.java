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
@Table(name="restaurant_table")
@Inheritance(strategy = InheritanceType.JOINED)
public class RestaurantTable implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id; 

	@Column(name="restaurant_table_number")
	private String number;
	
	@Column(name="restaurant_table_max_seats")
	private int max_seats; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "restaurant", nullable = false)
	private Restaurant restaurant;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<ReservationTable> reservedFor = new ArrayList<ReservationTable>();
	       
	public RestaurantTable() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getMax_seats() {
		return max_seats;
	}

	public void setMax_seats(int max_seats) {
		this.max_seats = max_seats;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<ReservationTable> getReservedFor() {
		return reservedFor;
	}

	public void setReservedFor(List<ReservationTable> reservedFor) {
		this.reservedFor = reservedFor;
	}
}
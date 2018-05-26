package foodbook.android.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ReservationTable")
public class ReservationTable implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@JsonIgnore
	@ManyToOne
	private RestaurantTable restaurantTable;
	
	@JsonIgnore
	@ManyToOne
	private Reservation reservation; 
	

	public ReservationTable() {
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public RestaurantTable getRestaurantTable() {
		return restaurantTable;
	}


	public void setRestaurantTable(RestaurantTable restaurantTable) {
		this.restaurantTable = restaurantTable;
	}


	public Reservation getReservation() {
		return reservation;
	}


	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

}
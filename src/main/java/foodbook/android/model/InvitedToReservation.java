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
@Table(name="InvitedToReservation")
public class InvitedToReservation implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="invited_to_reservation_id")
	private Long id;
	
	@JsonIgnore
	@ManyToOne
	private User user;
	
	@JsonIgnore
	@ManyToOne
	private Reservation reservation; 

	@Column(name="invited_to_reservation_confirmed")
	private boolean confirmed;
	
	@Column(name="invited_to_reservation_date")
	private Date date; 
	
//	@Version
    private int version;
	
	

	public InvitedToReservation() {
		
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Reservation getReservation() {
		return reservation;
	}



	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}



	public boolean isConfirmed() {
		return confirmed;
	}



	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public int getVersion() {
		return version;
	}



	public void setVersion(int version) {
		this.version = version;
	}
		
}
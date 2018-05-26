package foodbook.android.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="reservation")
@Inheritance(strategy = InheritanceType.JOINED)
public class Reservation implements Serializable{

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id; 
	
	@Column(name="appointment_begin")
	private Date begin;
	
	@Column(name="appointment_end")
	private Date end;
	
	@Column(name="reservation_canceled")
	private boolean canceled;
	
	@Column(name="reservation_last_updated")
	private Date last_updated;
	
	@Column(name="reservation_created_date")
	private Date created_date;
	
	@JsonIgnore
	@ManyToOne
	private User owner;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<InvitedToReservation> invited = new ArrayList<InvitedToReservation>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<ReservationTable> reservationTables = new ArrayList<ReservationTable>();
	 
	@JsonIgnore
	@ManyToOne
	private Restaurant restaurant;
	
	public Reservation() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public Date getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<InvitedToReservation> getInvited() {
		return invited;
	}

	public void setInvited(List<InvitedToReservation> invited) {
		this.invited = invited;
	}

	public List<ReservationTable> getReservedFor() {
		return reservationTables;
	}

	public void setReservedFor(List<ReservationTable> reservationTables) {
		this.reservationTables = reservationTables;
	}

	public List<ReservationTable> getReservationTables() {
		return reservationTables;
	}

	public void setReservationTables(List<ReservationTable> reservationTables) {
		this.reservationTables = reservationTables;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
}

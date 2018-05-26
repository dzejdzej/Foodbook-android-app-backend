package foodbook.android.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="review")
@Inheritance(strategy = InheritanceType.JOINED)
public class Review implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id; 
	
	@Column(name="review_date", nullable = false)
	private Date date; 
	
	@Column(name="review_ratting", nullable = false)
	private int ratting;
	
	@Column(name="review_comment", nullable = false)
	private String comment;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user", nullable = false)
    private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant", nullable = false)
    private Restaurant restaurant;
	
	public Review() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getRatting() {
		return ratting;
	}

	public void setRatting(int ratting) {
		this.ratting = ratting;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
}
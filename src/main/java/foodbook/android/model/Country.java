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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="country")
@Inheritance(strategy = InheritanceType.JOINED)
public class Country implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id; 
	
	@Column(name="country_name", nullable = false)
	private String name;
	
	@Column(name="country_code", nullable = false)
	private String code;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<City> cities = new ArrayList<City>();
	
	public Country() {
		
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

}
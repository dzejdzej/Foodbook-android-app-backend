package foodbook.android.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import foodbook.android.model.City;
import foodbook.android.model.Country;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	List<City> findByCountry(Country country);
	City findByName(String name); 
}
package foodbook.android.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import foodbook.android.model.City;
import foodbook.android.model.Restaurant;
import foodbook.android.model.enumerations.RestaurantType;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	List<Restaurant> findByCityAndType(City city, RestaurantType type); 
}
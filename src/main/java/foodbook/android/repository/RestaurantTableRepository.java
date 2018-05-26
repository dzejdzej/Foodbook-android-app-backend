package foodbook.android.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import foodbook.android.model.Restaurant;
import foodbook.android.model.RestaurantTable;

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {

	List<RestaurantTable> findByRestaurant(Restaurant restaurant); 
}
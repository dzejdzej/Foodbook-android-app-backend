package foodbook.android.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import foodbook.android.model.Reservation;
import foodbook.android.model.ReservationTable;
import foodbook.android.model.RestaurantTable;

@Repository
public interface ReservationTableRepository extends JpaRepository<ReservationTable, Long> {

	List<ReservationTable> findByRestaurantTable(RestaurantTable table);
	List<ReservationTable> findByReservation(Reservation reservation); 
	
}
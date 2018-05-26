package foodbook.android.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import foodbook.android.model.InvitedToReservation;

@Repository
public interface InvitedToReservationRepository extends JpaRepository<InvitedToReservation, Long> {

	
}
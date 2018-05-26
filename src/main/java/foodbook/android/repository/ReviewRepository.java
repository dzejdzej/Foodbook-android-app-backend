package foodbook.android.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import foodbook.android.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	
}
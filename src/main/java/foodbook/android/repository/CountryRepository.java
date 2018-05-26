package foodbook.android.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import foodbook.android.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

	Country findByName(String name); 
}


package foodbook.android.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import foodbook.android.model.Country;
import foodbook.android.repository.CityRepository;
import foodbook.android.repository.CountryRepository;

@Service
@Transactional
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private CityRepository cityRepository;

	public List<Country> getAllCountries() {
		List<Country> countries = countryRepository.findAll();

		return countries;
	}

	public List<Country> getAllCountriesWithCities() {
		List<Country> countries = countryRepository.findAll();

		for (Country country : countries) {
			country.setCities(cityRepository.findByCountry(country));
		}

		return countries;
	}

}

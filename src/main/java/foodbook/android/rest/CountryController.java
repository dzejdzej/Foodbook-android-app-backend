package foodbook.android.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import foodbook.android.model.Country;
import foodbook.android.service.CountryService;

@RequestMapping("/api/country")
@RestController
public class CountryController {

	@Autowired
	private CountryService countryService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCountries() {
		List<Country> countries = countryService.getAllCountries();
	
		return new ResponseEntity<Object>(countries, HttpStatus.OK);
		
	}

	@RequestMapping(value = "/all/cities", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCountriesWithCities() {

		List<Country> countries = countryService.getAllCountriesWithCities();
		
		return new ResponseEntity<Object>(countries, HttpStatus.OK);
	}
}
package foodbook.android.rest;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import foodbook.android.model.User;
import foodbook.android.rest.dto.LoginDTO;
import foodbook.android.rest.dto.ReservationRequestDTO;
import foodbook.android.rest.dto.ReservationResponseDTO;
import foodbook.android.rest.dto.UserDTO;
import foodbook.android.service.UserService;


@RequestMapping("/api/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		
		User registeredUser = userService.registerUser(user);
		UserDTO dto = new UserDTO(registeredUser.getName(), registeredUser.getSurname()); 
		if (registeredUser != null) {
			return new ResponseEntity<Object>(dto, HttpStatus.CREATED);
		}
		return new ResponseEntity<Object>(dto, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/confirmed-mail/{user-id}", method = RequestMethod.GET)
	public ResponseEntity<?> confirmUserRegistration(@PathVariable(value = "user-id") long id) {

		boolean isValid = userService.verifyUser(id);

		if (isValid) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> loginUser(@RequestBody LoginDTO dto) {
		UserDTO user = userService.loginUser(dto);

		if (user != null) {
			return new ResponseEntity<Object>(user, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(user, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/available-restaurants-for-reservation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> loginUser(@RequestBody ReservationRequestDTO dto) {
		List<ReservationResponseDTO> responseDto = userService.getFreeRestaurantsForReservation(dto);

		if (responseDto != null) {
			return new ResponseEntity<Object>(responseDto, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(responseDto, HttpStatus.BAD_REQUEST);
	}
	
}


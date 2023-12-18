package aptech.dating.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aptech.dating.DTO.FamilyDTO;
import aptech.dating.DTO.UserHobbyDTO;
import aptech.dating.DTO.UserMusicDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.UserHobby;
import aptech.dating.model.UserMusic;
import aptech.dating.service.UserHobbyService;

@RestController
@RequestMapping("/api/userHobby")
@CrossOrigin(origins = "http://localhost:4200")
public class UserHobbyController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final UserHobbyService userHobbyService;

	// Use constructor-based dependency injection
	@Autowired
	public UserHobbyController(UserHobbyService userHobbyService) {
		this.userHobbyService = userHobbyService;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<List<UserHobbyDTO>> getAllUserHobbys() {
		List<UserHobby> userHobby = userHobbyService.getAllUserHobbys();

		List<UserHobbyDTO> userHobbyDTO = userHobby.stream().map(element -> modelMapper.map(element, UserHobbyDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(userHobbyDTO);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<UserHobbyDTO> getUserHobbyById(@PathVariable int id) {
		Optional<UserHobby> userHobby = userHobbyService.getUserHobbyById(id);

		UserHobbyDTO userHobbyDTO = modelMapper.map(userHobby, UserHobbyDTO.class);
		
		return userHobbyDTO!=null?ResponseEntity.ok(userHobbyDTO):ResponseEntity.notFound().build();
	}

	@GetMapping("/hobby/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<List<UserHobbyDTO>> getUserHobbiesByUserId(@PathVariable int id) {
		List<UserHobby> userHobby = userHobbyService.getUserHobbiesByUserId(id);

		List<UserHobbyDTO> userHobbyDTO = userHobby.stream().map(element -> modelMapper.map(element, UserHobbyDTO.class))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(userHobbyDTO);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<UserHobby> createUserHobby(@RequestBody @Validated UserHobbyDTO userHobbyDTO) {
		UserHobby userHobby = modelMapper.map(userHobbyDTO, UserHobby.class);
		return ResponseEntity.ok(userHobbyService.saveUserHobby(userHobby));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<UserHobby> updateUserHobby(@PathVariable int id, @RequestBody @Validated UserHobbyDTO userHobbyDTO) {
		Optional<UserHobby> userHobby = userHobbyService.getUserHobbyById(id);

	    if (userHobby.isPresent()) {
	    	UserHobby updateUserHobby = userHobby.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(userHobbyDTO, updateUserHobby);

	        // Save the updated admin
	        return ResponseEntity.ok(userHobbyService.saveUserHobby(updateUserHobby));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Void> deleteUserHobby(@PathVariable int id) {
		userHobbyService.deleteUserHobby(id);
		return ResponseEntity.ok().build();
	}
}
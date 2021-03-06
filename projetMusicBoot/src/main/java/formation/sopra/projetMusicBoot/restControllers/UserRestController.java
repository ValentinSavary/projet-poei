package formation.sopra.projetMusicBoot.restControllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import formation.sopra.projetMusicBoot.entities.JsonViews;
import formation.sopra.projetMusicBoot.entities.User;
import formation.sopra.projetMusicBoot.repositories.UserRepository;
import formation.sopra.projetMusicBoot.services.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserRestController {

	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("")
	@JsonView(JsonViews.User.class)
	public List<User> all() {
		return userService.all();
	}

	@GetMapping("/{id}")
	@JsonView(JsonViews.User.class)
	public User byId(@PathVariable("id") Long id) {
		return userService.byId(id);
	}
	
	@GetMapping("/{login}")
	@JsonView(JsonViews.User.class)
	public User byLogin(@PathVariable("login") String login) {
		return userService.byLogin(login);
	}
	
	@GetMapping("/register/{login}")
	public boolean isUsed(@PathVariable("login") String login) {
		return userService.isUsed(login);
	}

	@GetMapping("/playlist/{name}")
	@JsonView(JsonViews.User.class)
	public List<User> byPlaylist(@PathVariable("name") String name) {
		return userService.ByPlaylist(name);
	}

	@PostMapping("")
	@JsonView(JsonViews.User.class)
	@ResponseStatus(code = HttpStatus.CREATED)
	public User create(@Valid @RequestBody User user, BindingResult br) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userService.create(user);
	}

	@PutMapping("/{id}")
	@JsonView(JsonViews.User.class)
	public User update(@Valid @RequestBody User user, BindingResult br, @PathVariable("id") Long id) {
		User userEnBase = userService.byId(id);
		userEnBase.setLogin(user.getLogin());
		userEnBase.setPassword(passwordEncoder.encode(user.getPassword()));
		userEnBase.setAccountType(user.getAccountType());
		userEnBase.setVersion(user.getVersion());
		userEnBase.setPlaylists(user.getPlaylists());
		return userService.update(userEnBase);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		userService.delete(id);
	}
}
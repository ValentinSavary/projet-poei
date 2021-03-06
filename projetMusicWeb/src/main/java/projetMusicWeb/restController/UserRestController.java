package projetMusicWeb.restController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
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

import projetMusic.entity.JsonViews;
import projetMusic.entity.User;
import projetMusic.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

	@Autowired
	UserService userService;

	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<User> all() {
		return userService.all();
	}

	@GetMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public User byId(@PathVariable("id") Long id) {
		return userService.byId(id);
	}

	@PostMapping("")
	@JsonView(JsonViews.Common.class)
	@ResponseStatus(code = HttpStatus.CREATED)
	public User create(@Valid @RequestBody User user, BindingResult br) {
		return userService.save(user);
	}

	@PutMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public User update(@Valid @RequestBody User user, BindingResult br, @PathVariable("id") Long id) {
		User userEnBase = userService.byId(id);
		userEnBase.setLogin(user.getLogin());
		userEnBase.setPassword(user.getPassword());
		userEnBase.setAccountType(user.getAccountType());
		userEnBase.setVersion(user.getVersion());
		userEnBase.setPlaylists(user.getPlaylists());
		return userService.save(userEnBase);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		userService.delete(id);
	}
}
package projetMusic.services;

import java.util.Set;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetMusic.entity.User;
import projetMusic.exceptions.UserException;
import projetMusic.repositories.UserRepository;

//Service : code où l'on applique les requetes

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PlaylistService playlistService;
	@Autowired
	private Validator validator;

	public User save(User user) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		if (violations.isEmpty()) {
			return userRepository.save(user);
		} else {
			throw new UserException();
		}
	}

	public User byId(Long id) {
		return userRepository.findById(id).orElseThrow(UserException::new);
	}

	public List<User> all() {
		return userRepository.findAll();
	}

	public void delete(User user) {
		User userEnBase = userRepository.findById(user.getId()).orElseThrow(UserException::new);
		// Supression des playlists du user
		userEnBase.getPlaylists().forEach(playlist -> {
			playlistService.delete(playlist);
		});
		// Supression du user
		userRepository.delete(userEnBase);
	}

	public void delete(Long id) {
		delete(userRepository.getById(id));
	}
}
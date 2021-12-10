package formation.sopra.projetMusicBoot.services;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import formation.sopra.projetMusicBoot.entities.AccountType;
import formation.sopra.projetMusicBoot.entities.User;
import formation.sopra.projetMusicBoot.exceptions.UserException;
import formation.sopra.projetMusicBoot.repositories.UserRepository;

//Service : code ou l'on applique les requetes

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PlaylistService playlistService;
	@Autowired
	private PasswordEncoder passwordEncoder;

//	public User save(User user) {
//		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//		Set<ConstraintViolation<User>> violations = validator.validate(user);
//		if (violations.isEmpty()) {
//			return userRepository.save(user);
//		} else {
//			throw new UserException();
//		}
//	}
	
	public User create(User	user) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		if (violations.isEmpty()) {
				userRepository.save(user);
				return user;
		} else {
			throw new UserException();
		}
	}
	
	public User update(User user) {
		return userRepository.save(user);
	}

	public User byId(Long id) {
		return userRepository.findById(id).orElseThrow(UserException::new);
	}

	// Cette fonction retourne tous les users
	public List<User> all() {
		return userRepository.findAll();
	}
	
	// Cette fonction retourne tous les users par le nom de la playlist
	public List<User> ByPlaylist(String name) {
		return userRepository.findByPlaylist(name);
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
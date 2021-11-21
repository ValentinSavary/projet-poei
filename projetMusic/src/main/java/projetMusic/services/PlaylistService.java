package projetMusic.services;

<<<<<<< HEAD
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
=======
>>>>>>> master
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import projetMusic.entity.Playlist;
import projetMusic.exceptions.PlaylistException;
=======
>>>>>>> master
import projetMusic.repositories.PlaylistRepository;

@Service
public class PlaylistService {

	@Autowired
	private PlaylistRepository playlistRepository;
<<<<<<< HEAD
	@Autowired
	private Validator validator;

	public void save(Playlist playlist) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Playlist>> violations = validator.validate(playlist);
		if (violations.isEmpty()) {
			playlistRepository.save(playlist);
		} else {
			throw new PlaylistException();
		}
	}
}
=======

	@Autowired
	private Validator validator;

}
>>>>>>> master

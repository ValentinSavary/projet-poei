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
import projetMusic.entity.User;
import projetMusic.exceptions.UserException;
=======
>>>>>>> master
import projetMusic.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
<<<<<<< HEAD
	private UserRepository userRepository;
	@Autowired
	private Validator validator;

	public void save(User user) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		if (violations.isEmpty()) {
			userRepository.save(user);
		} else {
			throw new UserException();
		}
	}
}
=======
	private UserRepository repository;

	@Autowired
	private Validator validator;

}
>>>>>>> master

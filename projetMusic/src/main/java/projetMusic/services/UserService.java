package projetMusic.services;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetMusic.entity.User;
import projetMusic.exceptions.UserException;
import projetMusic.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

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
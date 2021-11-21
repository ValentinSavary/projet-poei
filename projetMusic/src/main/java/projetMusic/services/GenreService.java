package projetMusic.services;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetMusic.repositories.GenreRepository;

@Service
public class GenreService {

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private Validator validator;
}

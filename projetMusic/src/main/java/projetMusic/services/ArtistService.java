package projetMusic.services;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetMusic.repositories.ArtistRepository;

@Service
public class ArtistService {

	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private Validator validator;

}

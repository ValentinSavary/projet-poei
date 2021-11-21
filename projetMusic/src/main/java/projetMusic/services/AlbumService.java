package projetMusic.services;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetMusic.repositories.AlbumRepository;

@Service
public class AlbumService {

	@Autowired
	private AlbumRepository albumRepository;

	@Autowired
	private Validator validator;

}

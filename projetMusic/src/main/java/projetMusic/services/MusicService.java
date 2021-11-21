package projetMusic.services;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetMusic.repositories.MusicRepository;

@Service
public class MusicService {

	@Autowired
	private MusicRepository musicRepository;

	@Autowired
	private Validator validator;

}

package projetMusic.services;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetMusic.repositories.PlaylistRepository;

@Service
public class PlaylistService {

	@Autowired
	private PlaylistRepository playlistRepository;

	@Autowired
	private Validator validator;

}

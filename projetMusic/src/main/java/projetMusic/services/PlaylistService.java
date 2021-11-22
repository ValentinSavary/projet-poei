package projetMusic.services;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetMusic.entity.Music;
import projetMusic.entity.Playlist;
import projetMusic.exceptions.PlaylistException;
import projetMusic.repositories.MusicRepository;
import projetMusic.repositories.PlaylistRepository;

@Service
public class PlaylistService {
	@Autowired
	private PlaylistRepository playlistRepository;
	@Autowired
	private MusicRepository musicRepository;
	@Autowired
	private Validator validator;

	// Créer une playlist / Modifier une playlist
	public void save(Playlist playlist) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Playlist>> violations = validator.validate(playlist);
		if (violations.isEmpty()) {
			playlistRepository.save(playlist);
		} else {
			throw new PlaylistException();
		}
	}
	
	public Playlist byId(Long id) {
		return playlistRepository.findById(id).orElseThrow(PlaylistException::new);
	}

	// Cette fonction ajoute une musique dans la playlist
	public void addMusic(Music music, Playlist playlist) {
		playlist.getMusics().add(music);
		playlistRepository.save(playlist);
	}

	// Cette fonction supprime une musique de la playlist
	public void removeMusic(Music music, Playlist playlist) {
		playlist.getMusics().remove(music);
		playlistRepository.save(playlist);
	}
	
	// Cette fonction supprime une playlist
	public void delete(Playlist playlist) {
		playlistRepository.delete(playlist);
	}
	

}
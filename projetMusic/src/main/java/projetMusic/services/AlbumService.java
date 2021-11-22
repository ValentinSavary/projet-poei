package projetMusic.services;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetMusic.entity.Album;
import projetMusic.exceptions.AlbumException;
import projetMusic.repositories.AlbumRepository;
import projetMusic.repositories.ArtistRepository;
import projetMusic.repositories.MusicRepository;

//Service : code où l'on applique les requetes

@Service
public class AlbumService {
	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private MusicRepository musicRepository;
	@Autowired
	private Validator validator;
	@Autowired
	private MusicService musicService;

	// Création / modification d'un album
	public void save(Album album) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Album>> violations = validator.validate(album);
		if (violations.isEmpty()) {
			albumRepository.save(album);
		} else {
			throw new AlbumException();
		}
	}

	// Suppression d'un album
	public void delete(Album album) {
		Album albumEnBase = albumRepository.findById(album.getId()).orElseThrow(AlbumException::new);
		// Suppression de l'album pour les musiques associées
		albumEnBase.getMusics().forEach(music -> {
			if (music.getAlbums().size() == 1) {
				musicService.delete(music);
			}
		});
		// Suppression de l'album
		albumRepository.delete(albumEnBase);
	}

	public List<Album> allAlbum() {
		return albumRepository.findAll();
	}

	public Album byId(Long id) {
		return albumRepository.findById(id).orElseThrow(AlbumException::new);
	}
}
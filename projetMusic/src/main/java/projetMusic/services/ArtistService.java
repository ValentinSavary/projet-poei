package projetMusic.services;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetMusic.entity.Artist;
import projetMusic.exceptions.ArtistException;
import projetMusic.repositories.AlbumRepository;
import projetMusic.repositories.ArtistRepository;
import projetMusic.repositories.MusicRepository;

@Service
public class ArtistService {

	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private MusicRepository musicRepository;

	public void save(Artist artist) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Artist>> violations = validator.validate(artist);
		if (violations.isEmpty()) {
			artistRepository.save(artist);
		} else {
			throw new ArtistException();
		}
	}

	// Suppression d'un artiste
	public void delete(Artist artist) {
		Artist artistEnBase = artistRepository.findById(artist.getId()).orElseThrow(ArtistException::new);
		// Suppression de l'artiste pour les musiques et albums associés
		artistEnBase.getAlbums().forEach(album -> {
			album.getMusics().forEach(music -> {
				music.removeArtist(artistEnBase);
				musicRepository.save(music);
			});
			album.removeArtist(artistEnBase);
			albumRepository.save(album);
		});
		// Suppression de l'artiste
		artistRepository.delete(artistEnBase);
	}

	public List<Artist> allArtist() {
		return artistRepository.findAll();
	}

	public Artist byId(Long id) {
		return artistRepository.findById(id).orElseThrow(ArtistException::new);
	}
}
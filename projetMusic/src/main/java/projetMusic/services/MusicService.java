package projetMusic.services;

<<<<<<< HEAD
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
=======
>>>>>>> master
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import projetMusic.entity.Music;
import projetMusic.exceptions.MusicException;
import projetMusic.repositories.AlbumRepository;
import projetMusic.repositories.ArtistRepository;
=======
>>>>>>> master
import projetMusic.repositories.MusicRepository;

@Service
public class MusicService {

	@Autowired
<<<<<<< HEAD
	private AlbumRepository albumRepository;
	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private MusicRepository musicRepository;
	@Autowired
	private Validator validator;

	public void save(Music music) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Music>> violations = validator.validate(music);
		if (violations.isEmpty()) {
			musicRepository.save(music);
		} else {
			throw new MusicException();
		}
	}
	
	// Suppression d'une musique
	public void delete(Music music) {
		Music musicEnBase = musicRepository.findById(music.getId()).orElseThrow(MusicException::new);		
		// Suppression de l'artiste pour les musiques et albums associés
		musicEnBase.getAlbums().forEach(album -> {
			album.getArtists().forEach(artist -> {
				artist.removeMusic(musicEnBase);
				artistRepository.save(artist);
			});
			album.removeMusic(musicEnBase);
			albumRepository.save(album);
		});
		
		// Suppression de la musique
		musicRepository.delete(musicEnBase);
	}

	public List<Music> allMusic() {
		return musicRepository.findAll();
	}

	public Music byId(Long id) {
		return musicRepository.findById(id).orElseThrow(MusicException::new);
	}
}
=======
	private MusicRepository musicRepository;

	@Autowired
	private Validator validator;

}
>>>>>>> master

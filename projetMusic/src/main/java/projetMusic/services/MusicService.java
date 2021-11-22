package projetMusic.services;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetMusic.entity.Album;
import projetMusic.entity.Artist;
import projetMusic.entity.Music;
import projetMusic.exceptions.MusicException;
import projetMusic.repositories.AlbumRepository;
import projetMusic.repositories.ArtistRepository;
import projetMusic.repositories.MusicRepository;

// Service : code où l'on applique les requetes

@Service
public class MusicService {

	@Autowired
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

	public void delete(Music music) {
		Music musicEnBase = musicRepository.findById(music.getId()).orElseThrow(MusicException::new);
//		// Suppression de l'artiste pour les musiques et albums associés
//		musicEnBase.getAlbums().forEach(album -> {
//			album.getArtists().forEach(artist -> {
//				artist.removeMusic(musicEnBase);
//				artistRepository.save(artist);
//			});
//			album.removeMusic(musicEnBase);
//			albumRepository.save(album);
//		});

		// Suppression de la musique
		musicRepository.delete(musicEnBase);
	}

	public List<Music> allMusic() {
		return musicRepository.findAll();
	}

	public List<Music> byArtist(String artist) {
		return musicRepository.findByArtist(artist);
	}

	public List<Music> byAlbum(String album) {
		return musicRepository.findByAlbum(album);
	}

	public List<Music> byTitle(String title) {
		return musicRepository.findByTitle(title);
	}

	public List<Music> byPlaylist(String playlist) {
		return musicRepository.findByPlaylist(playlist);
	}

	public Music byId(Long id) {
		return musicRepository.findById(id).orElseThrow(MusicException::new);
	}
	
	// Cette fonction ajoute une musique dans l'album
	public void addAlbum(Album album, Music music) {
		music.getAlbums().add(album);
		musicRepository.save(music);
	}
}
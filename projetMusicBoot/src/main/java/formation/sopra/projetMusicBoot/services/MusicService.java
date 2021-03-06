package formation.sopra.projetMusicBoot.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import formation.sopra.projetMusicBoot.entities.Album;
import formation.sopra.projetMusicBoot.entities.Genre;
import formation.sopra.projetMusicBoot.entities.Music;
import formation.sopra.projetMusicBoot.exceptions.MusicException;
import formation.sopra.projetMusicBoot.repositories.MusicRepository;

// Service : code ou l'on applique les requetes

@Service
public class MusicService {
	
	private final Path root = Paths.get("uploads");

	@Autowired
	private MusicRepository musicRepository;

	public Music save(Music music) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Music>> violations = validator.validate(music);
		if (violations.isEmpty()) {
			return musicRepository.save(music);
		} else {
			throw new MusicException();
		}
	}

	public void delete(Music music) {
		Music musicEnBase = musicRepository.findById(music.getId()).orElseThrow(MusicException::new);
		// Suppression de la musique
		musicRepository.delete(musicEnBase);
	}

	public void delete(Long id) {
		delete(musicRepository.findById(id).get());
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

	public List<Music> byGenre(Set<Genre> genre) {
		return musicRepository.findByGenre(genre);
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
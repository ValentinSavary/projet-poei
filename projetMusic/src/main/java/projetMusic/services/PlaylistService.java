package projetMusic.services;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetMusic.entity.Album;
import projetMusic.entity.Artist;
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
		// if (playlist typePrivate == true){
		// Throw(PlaylistException::new);}
		// if playlist typePrivate == false){
		return playlistRepository.findById(id).orElseThrow(PlaylistException::new);
		// }
	}

	// Cette fonction ajoute une musique dans la playlist
	public void addMusic(Music music, Playlist playlist) {
		playlist.getMusics().add(music);
		playlistRepository.save(playlist);
	}

	// Cette fonction ajoute toutes les musiques d'un album dans la playlist
	public void addAlbum(Album album, Playlist playlist) {
		album.getMusics().forEach(music -> {
			playlist.getMusics().add(music);
		});
		playlistRepository.save(playlist);
	}

	// Cette fonction ajoute toutes les musiques de tous albums d'un artiste dans la
	// playlist
	public void addArtist(Artist artist, Playlist playlist) {
		artist.getAlbums().forEach(album -> {
			album.getMusics().forEach(music -> {
				playlist.getMusics().add(music);
			});
		});
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
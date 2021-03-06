package formation.sopra.projetMusicBoot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.sopra.projetMusicBoot.entities.Album;
import formation.sopra.projetMusicBoot.entities.Artist;
import formation.sopra.projetMusicBoot.entities.Music;
import formation.sopra.projetMusicBoot.entities.Playlist;
import formation.sopra.projetMusicBoot.exceptions.PlaylistException;
import formation.sopra.projetMusicBoot.repositories.PlaylistRepository;

//Service : code ou l'on applique les requetes

@Service
public class PlaylistService {
	@Autowired
	private PlaylistRepository playlistRepository;

	// Creer une playlist / Modifier une playlist
	public Playlist save(Playlist playlist) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Playlist>> violations = validator.validate(playlist);
		if (violations.isEmpty()) {
			return playlistRepository.save(playlist);
		} else {
			throw new PlaylistException();
		}
	}

	// Cette fonction retrouve une playlist publique à partir de son ID
	public Playlist byId(Long id) {
		if (playlistRepository.findById(id).get().isTypePrivate() == true) {
			throw new PlaylistException();
		} else {
			return playlistRepository.findById(id).orElseThrow(PlaylistException::new);
		}
	}

	// Cette fonction retrouve une playlist publique à partir de son nom
	public List<Playlist> byName(String name) {
		List<Playlist> playlists = new ArrayList<Playlist>();
		playlistRepository.findByName(name).forEach(playlistEnBase -> {
			if (playlistEnBase.isTypePrivate() == false) {
				playlists.add(playlistEnBase);
			}
		});
		return playlists;
	}

	public List<Playlist> byUser(String username) {
		List<Playlist> playlists = new ArrayList<Playlist>();
		playlistRepository.findByUser(username).forEach(playlistEnBase -> {
			if (playlistEnBase.isTypePrivate() == false) {
				playlists.add(playlistEnBase);
			}
		});
		return playlists;
	}

//	// Cette fonction ajoute une musique dans la playlist
//	public void addMusic(Music music, Playlist playlist) {
//		playlist.getMusics().add(music);
//		playlistRepository.save(playlist);
//	}
//
//	// Cette fonction ajoute toutes les musiques d'un album dans la playlist
//	public void addAlbum(Album album, Playlist playlist) {
//		album.getMusics().forEach(music -> {
//			playlist.getMusics().add(music);
//		});
//		playlistRepository.save(playlist);
//	}
//
//	// Cette fonction ajoute toutes les musiques de tous albums d'un artiste dans la
//	// playlist
//	public void addArtist(Artist artist, Playlist playlist) {
//		artist.getAlbums().forEach(album -> {
//			album.getMusics().forEach(music -> {
//				playlist.getMusics().add(music);
//			});
//		});
//		playlistRepository.save(playlist);
//	}
//
//	// Cette fonction supprime une musique de la playlist
//	public void removeMusic(Music music, Playlist playlist) {
//		playlist.getMusics().remove(music);
//		playlistRepository.save(playlist);
//	}

	// Cette fonction supprime une playlist
	public void delete(Playlist playlist) {
		playlistRepository.delete(playlist);
	}

	public void delete(Long id) {
		delete(playlistRepository.getById(id));
	}
}
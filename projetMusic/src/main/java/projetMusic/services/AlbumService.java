package projetMusic.services;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetMusic.entity.Album;
import projetMusic.entity.Artist;
import projetMusic.entity.Genre;
import projetMusic.entity.Music;
import projetMusic.exceptions.AlbumException;
import projetMusic.repositories.AlbumRepository;
import projetMusic.repositories.ArtistRepository;

//Service : code où l'on applique les requetes

@Service
public class AlbumService {
	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private Validator validator;
	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private MusicService musicService;

	// Création / modification d'un album
	public void save(Album album) {
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

	// Cette méthode renvoie la liste de tous les albums par nom
	public List<Album> ByName(String name) {
		return albumRepository.findByName(name);
	}

	// Cette méthode renvoie la liste de tous les albums par musique
	public List<Album> ByMusic(String title) {
		return albumRepository.findByMusic(title);
	}

	// Cette méthode renvoie la liste de tous les albums par artiste
	public List<Album> ByArtist(String name) {
		return albumRepository.findByArtist(name);
	}

	// Cette méthode renvoie la liste de tous les albums par artiste
	public List<Album> ByGenre(Genre genre) {
		return albumRepository.findByGenre(genre.toString());
	}

	public Album byId(Long id) {
		return albumRepository.findById(id).orElseThrow(AlbumException::new);
	}

	// Cette fonction ajoute une musique dans l'album
	public void addMusic(Music music, Album album) {
		album.getMusics().add(music);
		albumRepository.save(album);
	}

	// Cette fonction ajoute un artiste dans l'album
	public void addArtist(Artist artist, Album album) {
		album.getArtists().add(artist);
		albumRepository.save(album);
	}

}
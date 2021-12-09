package formation.sopra.projetMusicBoot.services;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.sopra.projetMusicBoot.entities.Album;
import formation.sopra.projetMusicBoot.entities.Artist;
import formation.sopra.projetMusicBoot.entities.Genre;
import formation.sopra.projetMusicBoot.entities.Music;
import formation.sopra.projetMusicBoot.exceptions.AlbumException;
import formation.sopra.projetMusicBoot.repositories.AlbumRepository;

//Service : code ou on applique les requetes

@Service
public class AlbumService {
	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private Validator validator;
	@Autowired
	private MusicService musicService;

	// Creation / modification d'un album
	public Album save(Album album) {
		Set<ConstraintViolation<Album>> violations = validator.validate(album);
		if (violations.isEmpty()) {
			return albumRepository.save(album);
		} else {
			throw new AlbumException();
		}
	}

	// Suppression d'un album
	public void delete(Album album) {
		Album albumEnBase = albumRepository.findById(album.getId()).orElseThrow(AlbumException::new);
		// Suppression de l'album pour les musiques associ�es
		albumEnBase.getMusics().forEach(music -> {
			if (music.getAlbums().size() == 1) {
				musicService.delete(music);
			}
		});
		// Suppression de l'album
		albumRepository.delete(albumEnBase);
	}

	public void delete(Long id) {
		delete(albumRepository.findById(id).get());
	}

	public List<Album> allAlbum() {
		return albumRepository.findAll();
	}

	// Cette methode renvoie la liste de tous les albums par nom
	public List<Album> ByName(String name) {
		return albumRepository.findByName(name);
	}

	public List<Album> byGenre(Set<Genre> genre) {
		return albumRepository.findByGenre(genre);
	}

	// Cette methode renvoie la liste de tous les albums par musique
	public List<Album> ByMusic(String title) {
		return albumRepository.findByMusic(title);
	}

	// Cette methode renvoie la liste de tous les albums par artiste
	public List<Album> ByArtist(String name) {
		return albumRepository.findByArtist(name);
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
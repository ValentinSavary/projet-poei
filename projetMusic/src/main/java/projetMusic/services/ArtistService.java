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
import projetMusic.entity.Playlist;
import projetMusic.exceptions.ArtistException;
import projetMusic.exceptions.PlaylistException;
import projetMusic.repositories.AlbumRepository;
import projetMusic.repositories.ArtistRepository;
import projetMusic.repositories.MusicRepository;

//Service : code où l'on applique les requetes

@Service
public class ArtistService {

	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private Validator validator;

	// Création d'un artiste / Mise à jour d'un artiste
	public void save(Artist artist) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Artist>> violations = validator.validate(artist);
		if (violations.isEmpty()) {
			artistRepository.save(artist);
		} else {
			throw new ArtistException();
		}
	}
	
	// Cette méthode renvoie un artiste
	public Artist byId(Long id) {
		return artistRepository.findById(id).orElseThrow(ArtistException::new);
	}
	
	// Cette méthode renvoie la liste de tous les artistes
	public List<Artist> allArtist() {
		return artistRepository.findAll();
	}
	
	// Cette méthode renvoie la liste de tous les artistes par nom
	public List<Artist> ByName(String name) {
		return artistRepository.findByName(name);
	}
	
	// Cette méthode renvoie la liste de tous les artistes par album
	public List<Artist> ByAlbum(String album) {
		return artistRepository.findByAlbum(album);
	}
	
	// Cette méthode renvoie la liste de tous les artistes par music
	public List<Artist> ByMusic(String music) {
		return artistRepository.findByMusic(music);
	}
	
	// Cette méthode renvoie la liste de tous les artistes par genre
	public List<Artist> ByGenre(String genre) {
		return artistRepository.findByGenre(genre);
	}
	
	// Suppression d'un artiste
	public void delete(Artist artist) {
		Artist artistEnBase = artistRepository.findById(artist.getId()).orElseThrow(ArtistException::new);
		// Suppression de l'artiste pour les albums associés
		artistEnBase.getAlbums().forEach(album -> {
			if (album.getArtists().size() == 1) {
				AlbumService.delete(album); // Fonction de Seb
			}	
		});
		//Suppression de l'artiste
		artistRepository.delete(artistEnBase);
	}
}
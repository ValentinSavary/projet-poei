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
import formation.sopra.projetMusicBoot.exceptions.ArtistException;
import formation.sopra.projetMusicBoot.repositories.ArtistRepository;

//Service : code o� l'on applique les requetes

@Service
public class ArtistService {

	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private Validator validator;
	@Autowired
	private AlbumService albumService;

	// Cr�ation d'un artiste / Mise � jour d'un artiste
	public Artist save(Artist artist) {
		Set<ConstraintViolation<Artist>> violations = validator.validate(artist);
		if (violations.isEmpty()) {
			return artistRepository.save(artist);
		} else {
			throw new ArtistException();
		}
	}

	// Cette m�thode renvoie un artiste
	public Artist byId(Long id) {
		return artistRepository.findById(id).orElseThrow(ArtistException::new);
	}

	// Cette m�thode renvoie la liste de tous les artistes
	public List<Artist> allArtist() {
		return artistRepository.findAll();
	}

	// Cette m�thode renvoie la liste de tous les artistes par nom
	public List<Artist> ByName(String name) {
		return artistRepository.findByName(name);
	}

	// Cette m�thode renvoie la liste de tous les artistes par album
	public List<Artist> ByAlbum(String name) {
		return artistRepository.findByAlbum(name);
	}

	// Cette m�thode renvoie la liste de tous les artistes par music
	public List<Artist> ByMusic(String music) {
		return artistRepository.findByMusic(music);
	}

	public List<Artist> byGenre(Set<Genre> genre) {
		return artistRepository.findByGenre(genre);
	}

	// Suppression d'un artiste
	public void delete(Artist artist) {
		Artist artistEnBase = artistRepository.findById(artist.getId()).orElseThrow(ArtistException::new);
		// Suppression de l'artiste pour les albums associ�s
		artistEnBase.getAlbums().forEach(album -> {
			if (album.getArtists().size() == 1) {
				albumService.delete(album); // Fonction de Seb
			}
		});
		// Suppression de l'artiste
		artistRepository.delete(artistEnBase);
	}

	public void delete(Long id) {
		delete(artistRepository.findById(id).get());
	}

//	// Cette fonction ajoute un artiste dans l'album
//	public void addAlbum(Album album, Artist artist) {
//		artist.getAlbums().add(album);
//		artistRepository.save(artist);
//	}
//
//	// Cette fonction supprime un artiste dans l'album
//	public void removeAlbum(Album album, Artist artist) {
//		artist.getAlbums().remove(album);
//		artistRepository.save(artist);
//	}
}
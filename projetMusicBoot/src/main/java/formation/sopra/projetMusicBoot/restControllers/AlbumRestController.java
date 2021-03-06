package formation.sopra.projetMusicBoot.restControllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import formation.sopra.projetMusicBoot.entities.Album;
import formation.sopra.projetMusicBoot.entities.Genre;
import formation.sopra.projetMusicBoot.entities.JsonViews;
import formation.sopra.projetMusicBoot.entities.Playlist;
import formation.sopra.projetMusicBoot.services.AlbumService;
import formation.sopra.projetMusicBoot.services.ArtistService;
import formation.sopra.projetMusicBoot.services.MusicService;

@RestController
@RequestMapping("/api/album")
@CrossOrigin(origins = "*")
public class AlbumRestController {

	@Autowired
	private AlbumService albumService;
	@Autowired
	private MusicService musicService;
	@Autowired
	private ArtistService artistService;

	@GetMapping("")
	@JsonView(JsonViews.Album.class)
	public List<Album> all() {
		return albumService.allAlbum();
	}

	@GetMapping("/{id}")
	@JsonView(JsonViews.Album.class)
	public Album byId(@PathVariable("id") Long id) {
		return albumService.byId(id);
	}

	@GetMapping("/name/{name}")
	@JsonView(JsonViews.Album.class)
	public List<Album> byName(@PathVariable("name") String name) {
		return albumService.ByName(name);
	}

	@GetMapping("/artist/{name}")
	@JsonView(JsonViews.Album.class)
	public List<Album> byArtist(@PathVariable("name") String name) {
		return albumService.ByArtist(name);
	}

	@GetMapping("/music/{title}")
	@JsonView(JsonViews.Album.class)
	public List<Album> byMusic(@PathVariable("title") String title) {
		return albumService.ByMusic(title);
	}

	@GetMapping("/genre/{genre}")
	@JsonView(JsonViews.Album.class)
	public List<Album> byGenre(@PathVariable("genre") String genreEnString) {
		Set<Genre> genres = new HashSet<Genre>();
		genres.add(Genre.valueOf(genreEnString));
		return albumService.byGenre(genres);
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Album.class)
	public Album create(@Valid @RequestBody Album album, BindingResult br) {
		return albumService.save(album);
	}

//	// Cette fonction ajoute une musique dans l'album
//	@PutMapping("/add-music/{idAlbum}/{idMusic")
//	public void addMusic(@Valid @RequestBody Playlist playlist, BindingResult br,
//			@PathVariable("idPlaylist") Long idPlaylist, @PathVariable("idMusic") Long idMusic) {
//		albumService.addMusic(musicService.byId(idMusic), albumService.byId(idPlaylist));
//	}
//
//	// Cette fonction ajoute un artiste dans l'album
//	@PutMapping("/add-artist/{idAlbum}/{idArtist")
//	public void addArtist(@Valid @RequestBody Playlist playlist, BindingResult br,
//			@PathVariable("idPlaylist") Long idPlaylist, @PathVariable("idArtist") Long idArtist) {
//		albumService.addArtist(artistService.byId(idArtist), albumService.byId(idPlaylist));
//	}

	@PutMapping("/{id}")
	@JsonView(JsonViews.Album.class)
	public Album update(@PathVariable("id") Long id, @Valid @RequestBody Album album, BindingResult br) {
		Album albumEnBase = albumService.byId(id);
		albumEnBase.setName(album.getName());
		albumEnBase.setCover(album.getCover());
		albumEnBase.setArtists(album.getArtists());
		albumEnBase.setMusics(album.getMusics());
		albumEnBase.setYear(album.getYear());
		return albumService.save(albumEnBase);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		albumService.delete(id);
	}
}
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

import formation.sopra.projetMusicBoot.entities.Artist;
import formation.sopra.projetMusicBoot.entities.Genre;
import formation.sopra.projetMusicBoot.entities.JsonViews;
import formation.sopra.projetMusicBoot.services.ArtistService;

@RestController
@RequestMapping("/api/artist")
@CrossOrigin(origins = "*")
public class ArtistRestController {

	@Autowired
	ArtistService artistService;

	@GetMapping("")
	@JsonView(JsonViews.Artist.class)
	public List<Artist> all() {
		return artistService.allArtist();
	}

	@GetMapping("/{id}")
	@JsonView(JsonViews.Artist.class)
	public Artist byId(@PathVariable("id") Long id) {
		return artistService.byId(id);
	}

	@GetMapping("/name/{name}")
	@JsonView(JsonViews.Artist.class)
	public List<Artist> byName(@PathVariable("name") String name) {
		return artistService.ByName(name);
	}

	@GetMapping("/album/{name}")
	@JsonView(JsonViews.Artist.class)
	public List<Artist> byAlbum(@PathVariable("name") String name) {
		return artistService.ByAlbum(name);
	}

	@GetMapping("/music/{title}")
	@JsonView(JsonViews.Artist.class)
	public List<Artist> byMusic(@PathVariable("title") String title) {
		return artistService.ByMusic(title);
	}

	@GetMapping("/genre/{genre}")
	@JsonView(JsonViews.Artist.class)
	public List<Artist> byGenre(@PathVariable("genre") String genreEnString) {
		Set<Genre> genres = new HashSet<Genre>();
		genres.add(Genre.valueOf(genreEnString));
		return artistService.byGenre(genres);
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Artist.class)
	public Artist create(@Valid @RequestBody Artist artist, BindingResult br) {
		return artistService.save(artist);
	}

	@PutMapping("/{id}")
	@JsonView(JsonViews.Artist.class)
	public Artist update(@Valid @RequestBody Artist artist, BindingResult br, @PathVariable("id") Long id) {
		Artist artistEnBase = artistService.byId(id);
		artistEnBase.setName(artist.getName());
		artistEnBase.setCountry(artist.getCountry());
		return artistService.save(artistEnBase);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		artistService.delete(id);
	}
}

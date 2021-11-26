package projetMusicWeb.restController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
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

import projetMusic.entity.Artist;
import projetMusic.entity.JsonViews;
import projetMusic.services.ArtistService;

@RestController
@RequestMapping("/api/artist")
public class ArtistRestController {

	@Autowired
	ArtistService artistService;

	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Artist> all() {
		return artistService.allArtist();
	}

	@GetMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Artist byId(@PathVariable("id") Long id) {
		return artistService.byId(id);
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Common.class)
	public Artist create(@Valid @RequestBody Artist artist, BindingResult br) {
		return artistService.save(artist);
	}

	@PutMapping("/{id}")
	@JsonView(JsonViews.Common.class)
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

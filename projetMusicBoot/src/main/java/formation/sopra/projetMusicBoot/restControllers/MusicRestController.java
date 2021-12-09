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

import formation.sopra.projetMusicBoot.entities.Genre;
import formation.sopra.projetMusicBoot.entities.JsonViews;
import formation.sopra.projetMusicBoot.entities.Music;
import formation.sopra.projetMusicBoot.services.MusicService;

@RestController
@RequestMapping("/api/music")
@CrossOrigin(origins = "*")
public class MusicRestController {

	@Autowired
	MusicService musicService;

	@GetMapping("")
	@JsonView(JsonViews.Music.class)
	public List<Music> all() {
		return musicService.allMusic();
	}

	@GetMapping("/{id}")
	@JsonView(JsonViews.Music.class)
	public Music byId(@PathVariable("id") Long id) {
		return musicService.byId(id);
	}

	@GetMapping("/title/{title}")
	@JsonView(JsonViews.Music.class)
	public List<Music> byTitle(@PathVariable("title") String title) {
		return musicService.byTitle(title);
	}

	@GetMapping("/artist/{name}")
	@JsonView(JsonViews.Music.class)
	public List<Music> byArtis(@PathVariable("name") String name) {
		return musicService.byArtist(name);
	}

	@GetMapping("/album/{name}")
	@JsonView(JsonViews.Music.class)
	public List<Music> byAlbum(@PathVariable("name") String name) {
		return musicService.byAlbum(name);
	}

	@GetMapping("/playlist/{name}")
	@JsonView(JsonViews.Music.class)
	public List<Music> byPlaylist(@PathVariable("name") String name) {
		return musicService.byPlaylist(name);
	}

	@GetMapping("/genre/{genre}")
	@JsonView(JsonViews.Music.class)
	public List<Music> byGenre(@PathVariable("genre") String genreEnString) {
		Set<Genre> genres = new HashSet<Genre>();
		genres.add(Genre.valueOf(genreEnString));
		return musicService.byGenre(genres);
	}

	@PostMapping("")
	@JsonView(JsonViews.Music.class)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Music create(@Valid @RequestBody Music music, BindingResult br) {
		return musicService.save(music);
	}

	@PutMapping("/{id}")
	@JsonView(JsonViews.Music.class)
	public Music update(@Valid @RequestBody Music music, BindingResult br, @PathVariable("id") Long id) {
		Music musicEnBase = musicService.byId(id);
		musicEnBase.setTitle(music.getTitle());
		musicEnBase.setDuration(music.getDuration());
		musicEnBase.setMusicFile(music.getMusicFile());
		return musicService.save(musicEnBase);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		musicService.delete(id);
	}
}
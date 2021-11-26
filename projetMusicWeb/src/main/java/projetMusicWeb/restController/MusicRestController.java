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

import projetMusic.entity.JsonViews;
import projetMusic.entity.Music;
import projetMusic.services.MusicService;

@RestController
@RequestMapping("/api/music")
public class MusicRestController {

	@Autowired
	MusicService musicService;

	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Music> all() {
		return musicService.allMusic();
	}

	@GetMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Music byId(@PathVariable("id") Long id) {
		return musicService.byId(id);
	}

	@PostMapping("")
	@JsonView(JsonViews.Common.class)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Music create(@Valid @RequestBody Music music, BindingResult br) {
		return musicService.save(music);
	}

	@PutMapping("/{id}")
	@JsonView(JsonViews.Common.class)
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
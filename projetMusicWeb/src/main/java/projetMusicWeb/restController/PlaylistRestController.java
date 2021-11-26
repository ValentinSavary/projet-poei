package projetMusicWeb.restController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
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
import projetMusic.entity.Playlist;
import projetMusic.services.PlaylistService;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistRestController {

	@Autowired
	PlaylistService playlistService;

//	@GetMapping("")
//	@JsonView(JsonViews.Common.class)
//	public List<Playlist> all(){
//		return playlistService.all();
//	}

	@GetMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Playlist byId(@PathVariable("id") Long id) {
		return playlistService.byId(id);
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Common.class)
	public Playlist create(@Valid @RequestBody Playlist playlist, BindingResult br) {
		return playlistService.save(playlist);
	}

	@PutMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Playlist update(@Valid @RequestBody Playlist playlist, BindingResult br, @PathVariable("id") Long id) {
		Playlist playlistEnBase = playlistService.byId(id);
		playlistEnBase.setName(playlist.getName());
		playlistEnBase.setTypePrivate(playlist.isTypePrivate());
		playlistEnBase.setMusics(playlist.getMusics());
		playlistEnBase.setUser(playlist.getUser());
		playlistEnBase.setVersion(playlist.getVersion());
		return playlistService.save(playlistEnBase);
	}
}
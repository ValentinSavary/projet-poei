package formation.sopra.projetMusicBoot.restControllers;

import java.util.List;

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

import formation.sopra.projetMusicBoot.entities.JsonViews;
import formation.sopra.projetMusicBoot.entities.Playlist;
import formation.sopra.projetMusicBoot.services.PlaylistService;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistRestController {

	@Autowired
	PlaylistService playlistService;

//	@GetMapping("")
//	@JsonView(JsonViews.Playlist.class)
//	public List<Playlist> all(){
//		return playlistService.all();
//	}

	@GetMapping("/{id}")
	@JsonView(JsonViews.Playlist.class)
	public Playlist byId(@PathVariable("id") Long id) {
		return playlistService.byId(id);
	}

	@GetMapping("/name/{name}")
	@JsonView(JsonViews.Playlist.class)
	public List<Playlist> byName(@PathVariable("name") String name) {
		return playlistService.byName(name);
	}

	@GetMapping("/user/{username}")
	@JsonView(JsonViews.Playlist.class)
	public List<Playlist> byUser(@PathVariable("username") String username) {
		return playlistService.byUser(username);
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Playlist.class)
	public Playlist create(@Valid @RequestBody Playlist playlist, BindingResult br) {
		return playlistService.save(playlist);
	}

	@PutMapping("/{id}")
	@JsonView(JsonViews.Playlist.class)
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
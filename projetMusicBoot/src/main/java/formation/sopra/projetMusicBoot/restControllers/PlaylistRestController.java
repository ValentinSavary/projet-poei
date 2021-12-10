package formation.sopra.projetMusicBoot.restControllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import formation.sopra.projetMusicBoot.services.AlbumService;
import formation.sopra.projetMusicBoot.services.ArtistService;
import formation.sopra.projetMusicBoot.services.MusicService;
import formation.sopra.projetMusicBoot.services.PlaylistService;

@RestController
@RequestMapping("/api/playlist")
@CrossOrigin(origins = "*")
public class PlaylistRestController {

	@Autowired
	PlaylistService playlistService;
	@Autowired
	MusicService musicService;
	@Autowired
	ArtistService artistService;
	@Autowired
	AlbumService albumService;

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
	
//	@PutMapping("/add-music/{idPlaylist}/{idMusic")
//	public void addMusic(@Valid @ RequestBody Playlist playlist, BindingResult br, @PathVariable("idPlaylist") Long idPlaylist, @PathVariable("idMusic") Long idMusic) {
//		playlistService.addMusic(musicService.byId(idMusic), playlistService.byId(idPlaylist));
//	}
//	
//	@PutMapping("/add-artist/{idPlaylist}/{idArtist")
//	public void addArtist(@Valid @ RequestBody Playlist playlist, BindingResult br, @PathVariable("idPlaylist") Long idPlaylist, @PathVariable("idArtist") Long idArtist) {
//		playlistService.addArtist(artistService.byId(idArtist), playlistService.byId(idPlaylist));
//	}
//	
//	@PutMapping("/add-artist/{idPlaylist}/{idAlbum")
//	public void addAlbum(@Valid @ RequestBody Playlist playlist, BindingResult br, @PathVariable("idPlaylist") Long idPlaylist, @PathVariable("idAlbum") Long idAlbum) {
//		playlistService.addAlbum(albumService.byId(idAlbum), playlistService.byId(idPlaylist));
//	}
//	
//	@PutMapping("/remove-music/{idPlaylist}/{idMusic")
//	public void removeMusic(@Valid @ RequestBody Playlist playlist, BindingResult br, @PathVariable("idPlaylist") Long idPlaylist, @PathVariable("idMusic") Long idMusic) {
//		playlistService.removeMusic(musicService.byId(idMusic), playlistService.byId(idPlaylist));
//	}
}
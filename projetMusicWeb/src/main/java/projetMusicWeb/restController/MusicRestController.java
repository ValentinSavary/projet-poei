package projetMusicWeb.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projetMusic.services.MusicService;

@RestController
@RequestMapping("/api/music")
public class MusicRestController {

	@Autowired
	MusicService musicService;
}

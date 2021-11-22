package projetMusic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projetMusic.config.AppConfig;
import projetMusic.entity.Playlist;
import projetMusic.services.PlaylistService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class PlaylistServiceTest {

	@Autowired
	private PlaylistService playlistService;

	@Test
	public void createTest() {
		Playlist p = new Playlist();
		p.setName("playlistA");
		playlistService.save(p);
	}

	@Test
	public void addMusicTest(Playlist playlist ) {

	}
}
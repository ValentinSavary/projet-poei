package projetMusic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projetMusic.config.AppConfig;
import projetMusic.entity.Music;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class MusicServiceTest {

	@Autowired
	private MusicServiceTest MusicService;

	@Test
	public void removeTest() {
		Music m = new Music();
		MusicService.delete(m);
	}
}
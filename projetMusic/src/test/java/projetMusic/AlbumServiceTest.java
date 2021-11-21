package projetMusic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projetMusic.config.AppConfig;
import projetMusic.entity.Album;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class AlbumServiceTest {

	@Autowired
	private AlbumServiceTest albumService;

	@Test
	public void removeTest() {
		Album alb = new Album();
		albumService.delete(alb);
	}
}
package projetMusic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projetMusic.config.AppConfig;
import projetMusic.entity.Artist;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class ArtistServiceTest {

	@Autowired
	private ArtistServiceTest artistService;

	@Test
	public void removeTest() {
		Artist art = new Artist();
		artistService.delete(art);
	}
}
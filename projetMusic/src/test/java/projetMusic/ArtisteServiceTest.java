package projetMusic;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import projetMusic.config.AppConfig;
import projetMusic.entity.Artist;
import projetMusic.services.ArtistService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@Transactional
@Rollback(value = true)
public class ArtisteServiceTest {

	@Autowired
	private ArtistService artistService;
	
	private Artist artist;
	
	//@Test
	public void testSave() {
		artist = new Artist();
		artist.setName("ArtisteDuGhetto2");
		artistService.save(artist);
		assertNotNull(artistService.byId(artist.getId()));
	}

	//@Test
	public void testById() {
		assertNotNull(artistService.byId(1L));
	}

	//@Test
	public void testAllArtist() {
		assertNotNull(artistService.allArtist());
	}

	//@Test
	public void testByName() {
		assertNotNull(artistService.ByName("hetto"));
	}

	//@Test
	public void testByAlbum() {
		assertNotNull(artistService.ByAlbum("albumA"));
	}

	//@Test
	public void testByMusic() {
		assertNotNull(artistService.ByMusic("mus"));
	}

	//@Test //Ne fonctionne pas 
	public void testByGenre() {
		fail("Not yet implemented");
	}

	//@Test
	public void testDelete() {
		artist = artistService.byId(110L);
		artistService.delete(artist);
	}

	//@Test
	public void testAddAlbum() {
		artist = new Artist();
		artist.setName("Nouvel artsiste, tenez vous");
		artistService.save(artist);
		assertNotNull(artistService.byId(artist.getId()));
	}

}

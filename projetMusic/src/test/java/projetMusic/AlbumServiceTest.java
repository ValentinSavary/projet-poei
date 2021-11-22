package projetMusic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import projetMusic.config.AppConfig;
import projetMusic.entity.Album;
import projetMusic.entity.Artist;
import projetMusic.entity.Genre;
import projetMusic.services.AlbumService;
import projetMusic.services.ArtistService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@Transactional
@Rollback(value = false)
public class AlbumServiceTest {

	@Autowired
	private AlbumService albumService;
	@Autowired
	private ArtistService artistService;

	private Album album;
	private Artist artist;

	// @Test
	public void createTest() {
		album = new Album();
		album.setName("albumA");
		albumService.save(album);
	}

	// @Test
	public void changeNameTest() {
		album = new Album();
		album = albumService.byId(2L);
		album.setName("albumNameChanged");
		albumService.save(album);
	}

	// @Test
	public void addArtistTest() {
		album = new Album();
		album = albumService.byId(2L);
		artist = new Artist();
		artist = artistService.byId(10L);
		albumService.addArtist(artist, album);
	}

	// @Test
	public void deleteTest() {
		album = new Album();
		album = albumService.byId(2L);
		albumService.delete(album);
	}

	// @Test
	public void nameTest() {
		System.out.println(albumService.ByName("alb3"));
	}

	// @Test
	public void byArtistTest() {
		System.out.println(albumService.ByArtist("fr"));
	}

	// @Test
	public void byMusicTest() {
		System.out.println(albumService.ByMusic("musique1"));
	}

	//@Test // Ne marche pas
	public void byGenreTest() {
		System.out.println(albumService.ByGenre(Genre.valueOf("Rap")));
//		ByGenre(album.getMusics().forEach(music -> {
//	music.getGenre(Genre.valueOf("Rap"));
//})));
//}
	}
				


}
package projetMusic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projetMusic.config.AppConfig;
import projetMusic.entity.Music;
import projetMusic.entity.Playlist;
import projetMusic.repositories.PlaylistRepository;
import projetMusic.services.MusicService;
import projetMusic.services.PlaylistService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class PlaylistServiceTest {

	@Autowired
	private PlaylistService playlistService;
	@Autowired
	private PlaylistRepository playlistRepository;
	@Autowired
	private MusicService musicService;

	//@Test
	public void createTest() {
		Playlist p = new Playlist();
		p.setName("playlistA");
		playlistService.save(p);
	}
	
	@Test
		public void changeNameTest() {
			Playlist p = new Playlist();
			p = playlistService.byId(101L);
			p.setName("playlistNameChanged");
			playlistService.save(p);
		}

	// @Test
	public void addMusicTest() {
		Playlist playlist = new Playlist();
		playlist = playlistService.byId(100L);
		Music music = new Music();
		music = musicService.byId(102L);
		playlistService.addMusic(music, playlist);
	}

	// @Test
	public void removeMusicTest() {
		Playlist playlist = new Playlist();
		playlist = playlistService.byId(100L);
		Music music = new Music();
		music = musicService.byId(101L);
		System.out.println(playlist.getMusics());
		playlistService.removeMusic(music, playlist);
		System.out.println(playlist.getMusics());
	}

	//@Test
	public void deleteTest() {
		Playlist playlist = new Playlist();
		playlist = playlistService.byId(100L);
		System.out.println(musicService.allMusic());
		playlistService.delete(playlist);
		System.out.println(musicService.allMusic());
	}
}
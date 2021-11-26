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
import projetMusic.entity.Music;
import projetMusic.entity.Playlist;
import projetMusic.repositories.AlbumRepository;
import projetMusic.repositories.ArtistRepository;
import projetMusic.repositories.PlaylistRepository;
import projetMusic.services.AlbumService;
import projetMusic.services.ArtistService;
import projetMusic.services.MusicService;
import projetMusic.services.PlaylistService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@Transactional
@Rollback(value = true)
public class PlaylistServiceTest {

	@Autowired
	private PlaylistService playlistService;
	@Autowired
	private MusicService musicService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private ArtistService artistService;

	private Playlist playlist;
	private Album album;
	private Artist artist;

	//@Test
	public void createTest() {
		Playlist p = new Playlist();
		p.setName("playlistA");
		playlistService.save(p);
	}

	//@Test
	public void changeNameTest() {
		Playlist p = new Playlist();
		p = playlistService.byId(101L);
		p.setName("playlistNameChanged");
		playlistService.save(p);
	}

	//@Test
	public void addMusicTest() {
		playlist = new Playlist();
		playlist = playlistService.byId(104L);
		Music music = new Music();
		music = musicService.byId(102L);
		playlistService.addMusic(music, playlist);
	}

	//@Test
	public void addAlbumTest() {
		playlist = new Playlist();
		playlist = playlistService.byId(104L);
		album = new Album();
		album = albumService.byId(102L);
		playlistService.addAlbum(album, playlist);
	}

	//@Test
	public void addArtistTest() {
		playlist = new Playlist();
		playlist = playlistService.byId(104L);
		artist = new Artist();
		artist = artistService.byId(102L);
		playlistService.addArtist(artist, playlist);
	}

	//@Test
	public void removeMusicTest() {
		Playlist playlist = new Playlist();
		playlist = playlistService.byId(104L);
		Music music = new Music();
		music = musicService.byId(101L);
		System.out.println(playlist.getMusics());
		playlistService.removeMusic(music, playlist);
		System.out.println(playlist.getMusics());
	}

	//@Test
	public void deleteTest() {
		Playlist playlist = new Playlist();
		playlist = playlistService.byId(106L);
		System.out.println(musicService.allMusic());
		playlistService.delete(playlist);
		System.out.println(musicService.allMusic());
	}
}
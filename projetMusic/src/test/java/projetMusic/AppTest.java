package projetMusic;

import projetMusic.dao.DaoAlbum;
import projetMusic.dao.DaoAlbumFactory;
import projetMusic.dao.DaoArtist;
import projetMusic.dao.DaoArtistFactory;
import projetMusic.dao.DaoMusic;
import projetMusic.dao.DaoMusicFactory;
import projetMusic.dao.DaoUser;
import projetMusic.dao.DaoUserFactory;
import projetMusic.entity.AccountType;
import projetMusic.entity.Album;
import projetMusic.entity.Artist;
import projetMusic.entity.Music;
import projetMusic.entity.User;
import projetMusic.util.Context;

public class AppTest {

	public static void main(String[] args) {
		
		DaoMusic daoMusic = DaoMusicFactory.getInstance();
		DaoArtist daoArtist = DaoArtistFactory.getInstance();
		DaoAlbum daoAlbum = DaoAlbumFactory.getInstance();
		DaoUser daoUser = DaoUserFactory.getInstance();
		
		
		Music music = new Music("Angele", 180);
		daoMusic.insert(music);
		
		Artist artist = new Artist("Valentin", "France");
		daoArtist.insert(artist);
		
		Album album = new Album();
		album.setName("Album Valentin");
		daoAlbum.insert(album);
		
		User user = new User();
		user.setAccountType(AccountType.free);
		user.setLogin("123");
		user.setPassword("pass123");
		daoUser.insert(user);
		
		Context.destroy();
	}

}

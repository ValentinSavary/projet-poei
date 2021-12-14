package formation.sopra.projetMusicBoot;

import java.nio.file.Paths;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import formation.sopra.projetMusicBoot.entities.AccountType;
import formation.sopra.projetMusicBoot.entities.Album;
import formation.sopra.projetMusicBoot.entities.Artist;
import formation.sopra.projetMusicBoot.entities.Genre;
import formation.sopra.projetMusicBoot.entities.Music;
import formation.sopra.projetMusicBoot.entities.Playlist;
import formation.sopra.projetMusicBoot.entities.User;
import formation.sopra.projetMusicBoot.services.AlbumService;
import formation.sopra.projetMusicBoot.services.ArtistService;
import formation.sopra.projetMusicBoot.services.MusicService;
import formation.sopra.projetMusicBoot.services.PlaylistService;
import formation.sopra.projetMusicBoot.services.UserService;

@SpringBootTest
class ProjetMusicBootApplicationTests {

	@Autowired
	private ArtistService artistService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private MusicService musicService;
	@Autowired
	private UserService userService;
	@Autowired
	private PlaylistService playlistService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	//@Test
	void path() {
		System.out.println(Paths.get("./musics").resolve("ddd"));
		System.out.println(
				ServletUriComponentsBuilder.fromCurrentContextPath().path(":8080/download/").path("ddd").toUriString());
	}

	//@Test
	void contextLoads() {
	}

<<<<<<< Updated upstream
	//@Test
=======
	 //@Test
>>>>>>> Stashed changes
	void loadDB() {
		Artist thyArt = new Artist();
		thyArt.setName("Thy Art Is Murder");
		thyArt.setCountry("Australia");
		Set<Genre> genreThyArt = new HashSet<Genre>();
		genreThyArt.add(Genre.Metal);
		genreThyArt.add(Genre.TechnicalDeathcore);

		Album human = new Album();
		human.setName("Human Target");
		human.setYear(Year.of(2019));

		Music atonement = new Music();
		atonement.setTitle("Atonement");
		atonement.setDuration(239);
		atonement.setGenres(genreThyArt);
		musicService.save(atonement);
		Music eternalSuffering = new Music();
		eternalSuffering.setTitle("Eternal Suffering");
		eternalSuffering.setDuration(305);
		eternalSuffering.setGenres(genreThyArt);
		musicService.save(eternalSuffering);

		Set<Music> musHuman = new HashSet<Music>();
		musHuman.add(eternalSuffering);
		musHuman.add(atonement);
		human.setMusics(musHuman);
		albumService.save(human);

		Album holy = new Album();
		holy.setName("Holy War");
		holy.setYear(Year.of(2015));

		Music lightBearer = new Music();
		lightBearer.setTitle("Light Bearer");
		lightBearer.setDuration(235);
		lightBearer.setGenres(genreThyArt);
		musicService.save(lightBearer);

		Music coffinDragger = new Music();
		coffinDragger.setTitle("Coffin Dragger");
		coffinDragger.setDuration(175);
		coffinDragger.setGenres(genreThyArt);
		musicService.save(coffinDragger);

		Set<Music> musHoly = new HashSet<Music>();
		musHoly.add(lightBearer);
		musHoly.add(coffinDragger);
		holy.setMusics(musHoly);
		albumService.save(holy);

		Set<Album> albThyArt = new HashSet<Album>();
		albThyArt.add(holy);
		albThyArt.add(human);
		thyArt.setAlbums(albThyArt);
		artistService.save(thyArt);

		// DB Valentin
		Set<Music> musicsValo = new HashSet<Music>();
		Set<Artist> artistsValo = new HashSet<Artist>();
		Set<Album> albumsValo = new HashSet<Album>();
		Set<Genre> genresValo = new HashSet<Genre>();
		// Musics

		Music mus1 = new Music();
		mus1.setDuration(180);
		mus1.setTitle("J'me tire");
		genresValo.add(Genre.Rap);
		mus1.setGenres(genresValo);
		musicsValo.add(mus1);
		musicService.save(mus1);

		Music mus2 = new Music();
		mus2.setDuration(204);
		mus2.setTitle("Bella");
		mus2.setGenres(genresValo);
		musicsValo.add(mus2);
		musicService.save(mus2);

		Music mus3 = new Music();
		mus3.setDuration(154);
		mus3.setTitle("One Shot");
		mus3.setGenres(genresValo);
		musicsValo.add(mus3);
		musicService.save(mus3);

		Artist art = new Artist("Maitre Gims", "France");
		Album alb = new Album();
//		artistsValo.add(art);
//		alb.setArtists(artistsValo);
		alb.setName("Subliminal");
		alb.setMusics(musicsValo);
		albumService.save(alb);
		albumsValo.add(alb);
		art.setAlbums(albumsValo);
		artistService.save(art);

		// Creation musiques des albums de l'artiste de Seb

		Music musicSebA = new Music();
		Set<Album> albumsMusicA = new HashSet<Album>();
		Set<Playlist> playlistsMusicA = new HashSet<Playlist>();
		Set<Genre> genresMusicA = new HashSet<Genre>();
		genresMusicA.add(Genre.Rock);
		genresMusicA.add(Genre.Metal);
		musicSebA.setTitle("musicSebA");
		musicSebA.setGenres(genresMusicA);
		musicSebA.setDuration(42);
		musicSebA.setAlbums(albumsMusicA);
		musicSebA.setPlaylists(playlistsMusicA);
		musicService.save(musicSebA);

		Music musicSebAB = new Music();
		Set<Album> albumsMusicAB = new HashSet<Album>();
		Set<Playlist> playlistsMusicAB = new HashSet<Playlist>();
		Set<Genre> genresMusicAB = new HashSet<Genre>();
		musicSebAB.setTitle("musicSebAB");
		genresMusicAB.add(Genre.Rock);
		genresMusicAB.add(Genre.Pop);
		musicSebAB.setGenres(genresMusicAB);
		musicSebAB.setDuration(42);
		musicSebAB.setAlbums(albumsMusicAB);
		musicSebAB.setPlaylists(playlistsMusicAB);
		musicService.save(musicSebAB);

		Music musicSebB = new Music();
		Set<Album> albumsMusicB = new HashSet<Album>();
		Set<Playlist> playlistsMusicB = new HashSet<Playlist>();
		Set<Genre> genresMusicB = new HashSet<Genre>();
		musicSebB.setTitle("musicSebB");
		genresMusicB.add(Genre.Pop);
		musicSebB.setGenres(genresMusicB);
		musicSebB.setDuration(42);
		musicSebB.setAlbums(albumsMusicB);
		musicSebB.setPlaylists(playlistsMusicB);
		musicService.save(musicSebB);

		// Creation albums de l'artiste de Seb

		Album albumSebA = new Album();
		Set<Music> musicsA = new HashSet<Music>();
		musicsA.add(musicSebA);
		musicsA.add(musicSebAB);
		Set<Artist> artistsA = new HashSet<Artist>();
		albumSebA.setName("albumSebA");
		albumSebA.setYear(Year.of(2000));
		albumSebA.setMusics(musicsA);
		albumSebA.setArtists(artistsA);
		albumService.save(albumSebA);

		Album albumSebB = new Album();
		Set<Music> musicsB = new HashSet<Music>();
		musicsB.add(musicSebB);
		musicsB.add(musicSebAB);
		Set<Artist> artistsB = new HashSet<Artist>();
		albumSebB.setName("albumSebB");
		albumSebB.setYear(Year.of(2020));
		albumSebB.setMusics(musicsB);
		albumSebB.setArtists(artistsB);
		albumService.save(albumSebB);

		// Creation artiste de Seb

		Artist artSeb = new Artist();
		artSeb.setName("artistSeb");
		artSeb.setCountry("France");
		Set<Album> albumsArtSeb = new HashSet<Album>();
		albumsArtSeb.add(albumSebA);
		albumsArtSeb.add(albumSebB);
		artSeb.setAlbums(albumsArtSeb);
		artistService.save(artSeb);

		// Creation users

		User user1 = new User();
		user1.setUsername("Olivier");
		user1.setLogin("loginOlivier");
		user1.setAccountType(AccountType.ROLE_ADMIN);
		user1.setPassword(passwordEncoder.encode("oooo"));
		userService.create(user1);

		User user2 = new User();
		user2.setUsername("Valentin");
		user2.setLogin("loginValentin");
		user2.setAccountType(AccountType.ROLE_ADMIN);
		user2.setPassword(passwordEncoder.encode("vvvv"));
		userService.create(user2);

		User user3 = new User();
		user3.setUsername("Sebastien");
		user3.setLogin("loginSebastien");
		user3.setAccountType(AccountType.ROLE_ADMIN);
		user3.setPassword(passwordEncoder.encode("ssss"));
		userService.create(user3);

		// Creation playlists
		Playlist playlist1 = new Playlist();
		playlist1.setName("pla1");
		playlist1.setTypePrivate(false);
		playlist1.setUser(user1);

		Set<Music> musPla = new HashSet<Music>();
		musPla.add(musicSebB);
		musPla.add(lightBearer);
		musPla.add(mus2);
		playlist1.setMusics(musPla);
		playlistService.save(playlist1);

		Playlist playlist2 = new Playlist();
		playlist2.setName("pla2");
		playlist2.setTypePrivate(false);
		playlist2.setUser(user2);
	}
}
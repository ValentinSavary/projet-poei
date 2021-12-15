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

	//@Test
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
		
		Artist inMourning = new Artist();
		inMourning.setName("In Mourning");
		inMourning.setCountry("Sweden");
		Set<Genre> genreInMourning = new HashSet<Genre>();
		genreInMourning.add(Genre.Metal);
		genreInMourning.add(Genre.ProgressiveMelodicDeath);
		genreInMourning.add(Genre.MelodicDeath);
		
		Album weightOfOceans = new Album();
		weightOfOceans.setName("The Weight Of Oceans");
		weightOfOceans.setYear(Year.of(2012));
		Music colossus = new Music();
		colossus.setTitle("Colossus");
		colossus.setDuration(573);
		colossus.setGenres(genreInMourning);
		musicService.save(colossus);
		Music vowToConquer = new Music();
		vowToConquer.setTitle("A Vow To Conquer The Ocean");
		vowToConquer.setDuration(443);
		vowToConquer.setGenres(genreInMourning);
		musicService.save(vowToConquer);
		Music drowningSun = new Music();
		drowningSun.setTitle("The Drowning Sun");
		drowningSun.setDuration(520);
		drowningSun.setGenres(genreInMourning);
		musicService.save(drowningSun);
		Set<Music> musWeightOfOceans = new HashSet<Music>();
		musWeightOfOceans.add(colossus);
		musWeightOfOceans.add(vowToConquer);
		musWeightOfOceans.add(drowningSun);
		weightOfOceans.setMusics(musWeightOfOceans);
		albumService.save(weightOfOceans);
		
		Set<Album> albInMounring = new HashSet<Album>();
		albInMounring.add(weightOfOceans);
		inMourning.setAlbums(albInMounring);
		artistService.save(inMourning);
		
		Artist prequell = new Artist();
		prequell.setName("Prequell");
		prequell.setCountry("France");
		Set<Genre> genrePrequell = new HashSet<Genre>();
		genrePrequell.add(Genre.Classical);
		genrePrequell.add(Genre.Electro);
		
		Album futureComesBefore = new Album();
		futureComesBefore.setName("The Futur Comes Before");
		futureComesBefore.setYear(Year.of(2017));
		
		Music partXV = new Music();
		partXV.setTitle("Part XV");
		partXV.setDuration(312);
		partXV.setGenres(genrePrequell);
		musicService.save(partXV);
		
		Music partVII = new Music();
		partVII.setTitle("Part VII");
		partVII.setDuration(230);
		partVII.setGenres(genrePrequell);
		musicService.save(partVII);
		
		Set<Music> musFuturComesBefore = new HashSet<Music>();
		musFuturComesBefore.add(partXV);
		musFuturComesBefore.add(partVII);
		futureComesBefore.setMusics(musFuturComesBefore);
		albumService.save(futureComesBefore);
		
		Set<Album> albPrequell = new HashSet<Album>();
		albPrequell.add(futureComesBefore);
		prequell.setAlbums(albPrequell);
		artistService.save(prequell);
		
		Artist theHu = new Artist();
		theHu.setName("The HU");
		theHu.setCountry("Mongolia");
		Set<Genre> genreTheHu = new HashSet<Genre>();
		genreTheHu.add(Genre.Rock);
		genreTheHu.add(Genre.HunnuRock);
		
		Album theGereg = new Album();
		theGereg.setName("The Gereg");
		theGereg.setYear(Year.of(2019));
		
		Music wolfTotem = new Music();
		wolfTotem.setTitle("Wolf Totem");
		wolfTotem.setDuration(338);
		wolfTotem.setGenres(genreTheHu);
		musicService.save(wolfTotem);
		
		Music yuve = new Music();
		yuve.setTitle("Yuve Yuve Yu");
		yuve.setDuration(282);
		yuve.setGenres(genreTheHu);
		musicService.save(yuve);
		
		Music theSame = new Music();
		theSame.setTitle("The Same");
		theSame.setDuration(327);
		theSame.setGenres(genreTheHu);
		musicService.save(theSame);
		
		Set<Music> musGereg = new HashSet<Music>();
		musGereg.add(wolfTotem);
		musGereg.add(yuve);
		musGereg.add(theSame);
		theGereg.setMusics(musGereg);
		albumService.save(theGereg);
		
		Set<Album> albTheHu = new HashSet<Album>();
		albTheHu.add(theGereg);
		theHu.setAlbums(albTheHu);
		artistService.save(theHu);
		

//		// DB Valentin
//		Set<Music> musicsValo = new HashSet<Music>();
//		Set<Artist> artistsValo = new HashSet<Artist>();
//		Set<Album> albumsValo = new HashSet<Album>();
//		Set<Genre> genresValo = new HashSet<Genre>();
//		// Musics
//
//		Music mus1 = new Music();
//		mus1.setDuration(180);
//		mus1.setTitle("J'me tire");
//		genresValo.add(Genre.Rap);
//		mus1.setGenres(genresValo);
//		musicsValo.add(mus1);
//		musicService.save(mus1);
//
//		Music mus2 = new Music();
//		mus2.setDuration(204);
//		mus2.setTitle("Bella");
//		mus2.setGenres(genresValo);
//		musicsValo.add(mus2);
//		musicService.save(mus2);
//
//		Music mus3 = new Music();
//		mus3.setDuration(154);
//		mus3.setTitle("One Shot");
//		mus3.setGenres(genresValo);
//		musicsValo.add(mus3);
//		musicService.save(mus3);
//
//		Artist art = new Artist("Maitre Gims", "France");
//		Album alb = new Album();
////		artistsValo.add(art);
////		alb.setArtists(artistsValo);
//		alb.setName("Subliminal");
//		alb.setMusics(musicsValo);
//		albumService.save(alb);
//		albumsValo.add(alb);
//		art.setAlbums(albumsValo);
//		artistService.save(art);

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
		
		User user4 = new User();
		user4.setUsername("Theo");
		user4.setLogin("loginTheo");
		user4.setAccountType(AccountType.ROLE_ADMIN);
		user4.setPassword(passwordEncoder.encode("tttt"));
		userService.create(user4);

		// Creation playlists
		Playlist playlist1 = new Playlist();
		playlist1.setName("pla1");
		playlist1.setTypePrivate(false);
		playlist1.setUser(user1);

		Set<Music> musPla = new HashSet<Music>();
		musPla.add(colossus);
		musPla.add(lightBearer);
		musPla.add(partVII);
		playlist1.setMusics(musPla);
		playlistService.save(playlist1);

		Playlist playlist2 = new Playlist();
		playlist2.setName("pla2");
		playlist2.setTypePrivate(false);
		playlist2.setUser(user2);
	}
}
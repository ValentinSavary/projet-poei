package formation.sopra.projetMusicBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.sopra.projetMusicBoot.entities.Music;

// Repository : code o� l'on d�finit les requetes

public interface MusicRepository extends JpaRepository<Music, Long> {

	@Query("select distinct mus from Music mus left join fetch mus.albums as alb left join fetch alb.artists as art")
	List<Music> findAll();

	@Query("select mus from Music mus left join fetch mus.albums as alb left join fetch alb.artists as art where mus.title like %:title%")
	List<Music> findByTitle(@Param("title") String title);

	@Query("select mus from Music mus left join fetch mus.albums as alb where alb.name=:name")
	List<Music> findByAlbum(@Param("name") String name);

	@Query("select mus from Music mus left join fetch mus.playlists as pla where pla.name=:name")
	List<Music> findByPlaylist(@Param("name") String name);

	@Query("select mus from Music mus left join fetch mus.albums as alb left join fetch alb.artists as art where art.name=:name")
	List<Music> findByArtist(@Param("name") String name);
}
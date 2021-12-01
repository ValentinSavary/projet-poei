package formation.sopra.projetMusicBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.sopra.projetMusicBoot.entities.Artist;

//Repository : code o� l'on d�finit les requetes

public interface ArtistRepository extends JpaRepository<Artist, Long> {

	@Query("select distinct art from Artist art left join fetch art.albums")
	List<Artist> findAll();

	@Query("select art from Artist art left join fetch art.albums as alb left join fetch alb.musics as mus where art.name like %:name%")
	List<Artist> findByName(@Param("name") String name);

	@Query("select art from Artist art left join fetch art.albums as alb where alb.name like %:name%")
	List<Artist> findByAlbum(@Param("name") String name);

	@Query("select art from Artist art left join fetch art.albums as alb left join fetch alb.musics as mus where mus.title like %:title%")
	List<Artist> findByMusic(@Param("title") String title);

	@Query("select art from Artist art left join fetch art.albums as alb left join fetch alb.musics as mus where alb.musics=:genre")
	List<Artist> findByGenre(@Param("genre") String genre);

}
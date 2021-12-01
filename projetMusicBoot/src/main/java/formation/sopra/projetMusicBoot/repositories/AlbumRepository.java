package formation.sopra.projetMusicBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.sopra.projetMusicBoot.entity.Album;

//Repository : code o� l'on d�finit les requetes

public interface AlbumRepository extends JpaRepository<Album, Long> {

	@Query("select distinct alb from Album alb left join fetch alb.musics")
	List<Album> findAll();

	@Query("select alb from Album alb left join fetch alb.artists left join fetch alb.musics where alb.name like %:name%")
	List<Album> findByName(@Param("name") String name);

	@Query("select alb from Album alb left join fetch alb.musics as mus where mus.title =:title")
	List<Album> findByMusic(@Param("title") String title);
	
	@Query("select alb from Album alb left join fetch alb.artists as art where art.name = :name")// order by alb.artists.name
	List<Album> findByArtist(@Param("name") String name);

	@Query("select alb from Album alb left join fetch alb.musics as mus where mus.genre =:genre")
	List<Album> findByGenre(@Param("genre") String genre);

}
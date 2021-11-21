package projetMusic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projetMusic.entity.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {

	@Query("select alb from Album alb left join fetch alb.musics")
	List<Album> findAll();

	@Query("select alb from Album alb left join fetch alb.artists left join fetch alb.musics where alb.name =:name")
	List<Album> findByName(@Param("name") String name);

	@Query("select alb from Album alb left join fetch alb.musics where alb.musics =:music")
	List<Album> findByMusic(@Param("music") String music);

	@Query("select alb from Album alb left join fetch alb.artists where alb.artists =:artist")
	List<Album> findByArtist(@Param("artist") String artist);

	@Query("select alb from Album alb left join fetch alb.musics where alb.musics =:genre")
	List<Album> findByGenre(@Param("genre") String genre);
}

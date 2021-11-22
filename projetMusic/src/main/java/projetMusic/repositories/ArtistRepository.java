package projetMusic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projetMusic.entity.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

	@Query("select art from Artist art left join fetch art.albums")
	List<Artist> findAll();

	@Query("select art from Artist art left join fetch art.albums as alb left join fetch alb.musics as mus where art.name=:name")
	List<Artist> findByName(@Param("name") String name);

	@Query("select art from Artist art left join fetch art.albums where art.albums =:album")
	List<Artist> findByAlbum(@Param("album") String album);

	@Query("select art from Artist art left join fetch art.albums as alb left join fetch alb.musics as mus where alb.musics=:music")
	List<Artist> findByMusic(@Param("music") String music);

	@Query("select art from Artist art left join fetch art.albums as alb left join fetch alb.musics as mus where alb.musics=:genre")
	List<Artist> findByGenre(@Param("genre") String genre);
	
	
}
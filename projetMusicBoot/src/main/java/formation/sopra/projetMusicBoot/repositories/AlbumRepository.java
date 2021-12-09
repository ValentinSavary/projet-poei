package formation.sopra.projetMusicBoot.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.sopra.projetMusicBoot.entities.Album;
import formation.sopra.projetMusicBoot.entities.Genre;

//Repository : code ou l'on definit les requetes pour recuperer un album

public interface AlbumRepository extends JpaRepository<Album, Long> {

	@Query("select distinct alb from Album alb left join fetch alb.musics as mus left join fetch alb.artists as art")
	List<Album> findAll();

	@Query("select distinct alb from Album alb left join fetch alb.musics as mus left join fetch alb.artists as art where lower(alb.name) like lower(concat('%',:name,'%'))")
	List<Album> findByName(@Param("name") String name);

	@Query("select distinct alb from Album alb left join fetch alb.musics as mus left join fetch alb.artists as art where lower(mus.title) like lower(concat('%',:title,'%'))")
	List<Album> findByMusic(@Param("title") String title);
	
	@Query("select distinct alb from Album alb left join fetch alb.musics as mus left join fetch alb.artists as art where lower(art.name) like lower(concat('%',:name,'%'))")
	List<Album> findByArtist(@Param("name") String name);

	@Query("select distinct alb from Album alb left join fetch alb.artists as art left join fetch alb.musics as mus where :#{#genre} member of mus.genres")
	List<Album> findByGenre(@Param("genre") Set<Genre> genre);

}
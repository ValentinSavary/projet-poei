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

	@Query("select distinct mus from Music mus left join fetch mus.albums as alb left join fetch alb.artists as art where lower(mus.title) like lower(concat('%',:title,'%'))")
	List<Music> findByTitle(@Param("title") String title);

	@Query("select distinct mus from Music mus left join fetch mus.albums as alb where lower(alb.name) like lower(concat('%',:name,'%'))")
	List<Music> findByAlbum(@Param("name") String name);

	@Query("select distinct mus from Music mus left join fetch mus.playlists as pla where lower(pla.name) like lower(concat('%',:name,'%'))")
	List<Music> findByPlaylist(@Param("name") String name);

	@Query("select distinct mus from Music mus left join fetch mus.albums as alb left join fetch alb.artists as art where lower(art.name) like lower(concat('%',:name,'%'))")
	List<Music> findByArtist(@Param("name") String name);

	@Query("select distinct mus from Music mus left join fetch mus.albums as alb left join fetch alb.artists as art where mus.genres in (select gen from Genre gen where lower(gen.name) like lower(concat('%',:name,'%')))")
	List<Music> findByGenre(@Param("name") String name);

//	mus.genres.any().in(:genres)

//requete avec requete imbriquee
//	@Query("select distinct mus from Music mus left join fetch mus.albums as alb left join fetch alb.artists as art where lower(mus.genres.any().in(:genres))")
//	List<Music> findByGenre(@Param("genres") String genre);
//
//where e.department_id in (select em.department_id
//		            from hr.employees em
//		            where lower(em.last_name) = 'zlotkey');
//	
}
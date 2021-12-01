package formation.sopra.projetMusicBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.sopra.projetMusicBoot.entities.Playlist;

//Repository : code ou l'on definit les requetes

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

	@Query("select distinct pla from Playlist pla left join fetch pla.musics as mus where lower(pla.name) like lower(concat('%',:name,'%'))")
	List<Playlist> findByName(@Param("name") String name);

	@Query("select distinct pla from Playlist pla left join fetch pla.musics as mus left join fetch pla.user as use where lower(use.username) like lower(concat('%',:username,'%'))")
	List<Playlist> findByUser(@Param("username") String username);

}
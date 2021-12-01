package formation.sopra.projetMusicBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.sopra.projetMusicBoot.entity.Playlist;

//Repository : code o� l'on d�finit les requetes

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

	@Query("select pla from Playlist pla left join fetch pla.musics as mus left join fetch mus.albums as alb left join fetch alb.artists art where pla.name=:name")
	List<Playlist> findByName(@Param("name") String name);
}
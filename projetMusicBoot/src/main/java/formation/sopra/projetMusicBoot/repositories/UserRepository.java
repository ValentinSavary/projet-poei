package formation.sopra.projetMusicBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.sopra.projetMusicBoot.entities.User;

//Repository : code ou l'on definit les requetes

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select distinct use from User use left join fetch use.playlists as pla")
	List<User> findAll();
	
	@Query("select distinct use from User use left join fetch use.playlists as pla where lower(pla.name) like lower(concat('%',:name,'%'))")
	List<User> findByPlaylist(@Param("name") String name);
	
}
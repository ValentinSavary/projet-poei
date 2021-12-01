package formation.sopra.projetMusicBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.sopra.projetMusicBoot.entities.User;

//Repository : code o� l'on d�finit les requetes

public interface UserRepository extends JpaRepository<User, Long> {
}
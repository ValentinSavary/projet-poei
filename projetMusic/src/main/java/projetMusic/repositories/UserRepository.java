package projetMusic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projetMusic.entity.User;

//Repository : code o� l'on d�finit les requetes

public interface UserRepository extends JpaRepository<User, Long> {
}
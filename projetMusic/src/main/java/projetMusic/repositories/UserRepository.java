package projetMusic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projetMusic.entity.User;

//Repository : code où l'on définit les requetes

public interface UserRepository extends JpaRepository<User, Long> {
}
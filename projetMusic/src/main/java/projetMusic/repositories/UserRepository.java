package projetMusic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projetMusic.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

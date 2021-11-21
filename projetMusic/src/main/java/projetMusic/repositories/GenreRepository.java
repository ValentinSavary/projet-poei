package projetMusic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projetMusic.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}

package projetMusic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projetMusic.entity.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

}
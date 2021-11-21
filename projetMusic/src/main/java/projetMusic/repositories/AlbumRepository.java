package projetMusic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projetMusic.entity.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {

}
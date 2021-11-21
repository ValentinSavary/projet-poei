package projetMusic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projetMusic.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

}
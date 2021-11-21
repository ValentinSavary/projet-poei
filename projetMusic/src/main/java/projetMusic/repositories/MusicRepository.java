package projetMusic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projetMusic.entity.Music;

public interface MusicRepository extends JpaRepository<Music, Long> {

}
package projetMusic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projetMusic.entity.Music;

public interface MusicRepository extends JpaRepository<Music, Long> {

	@Query("select mus from Music mus left join fetch mus.albums as alb left join fetch alb.artists as art")
	List<Music> findAll();

	@Query("select mus from Music mus left join fetch mus.albums as alb left join fetch alb.artists as art where mus.title=:title")
	List<Music> findByTitle(@Param("title") String title);

	@Query("select mus from Music mus left join fetch mus.albums where mus.albums=:album")
	List<Music> findByAlbum(@Param("album") String album);

	@Query("select mus from Music mus left join fetch mus.playlists where mus.playlists=:playlist")
	List<Music> findByPlaylist(@Param("playlist") String playlist);

	@Query("select mus from Music mus left join fetch mus.albums as alb left join fetch alb.artists as art where art=:artist")
	List<Music> findByArtist(@Param("artist") String artist);
<<<<<<< HEAD
}
=======
}
>>>>>>> master

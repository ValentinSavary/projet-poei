package projetMusic.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "music")
@NamedQueries({
		@NamedQuery(name = "Music.findAll", query = "select mus from Music mus left join fetch mus.albums as alb left join fetch alb.artists as art"),
		@NamedQuery(name = "Music.findByTitle", query = "select mus from Music mus left join fetch mus.albums as alb left join fetch alb.artists as art where mus.title=:title"),
		@NamedQuery(name = "Music.findByAlbum", query = "select mus from Music mus left join fetch mus.albums where mus.albums=:album"),
		@NamedQuery(name = "Music.findByPlaylist", query = "select mus from Music mus left join fetch mus.playlists where mus.playlists=:playlist"),
		@NamedQuery(name = "Music.findByArtist", query = "select mus from Music mus left join fetch mus.albums as alb left join fetch alb.artists as art where art=:artist")})
@SequenceGenerator(name = "seqMusic", sequenceName = "seq_music", allocationSize = 1)

//@NamedQuery(name = "Music.findAll", query = "select mus from Music mus left join fetch mus.albums left join fetch mus.albums.artists")})
//@NamedQuery(name = "Music.findByTitle", query = "select mus from Music mus left join fetch mus.albums left join fetch mus.albums.artists where mus.title=:title")
//@NamedQuery(name = "Music.findByArtist", query = "select mus from Music mus left join fetch mus.albums left join fetch mus.albums.artists where mus.albums.artists=:artist")})
//@NamedQuery(name = "Music.findByGenre", query = "select mus from Music mus left join fetch mus.albums as alb left join fetch alb.artists art where mus.genres=:genre") })

public class Music {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMusic")
	@Column(name = "music_id")
	private Long id;
	@Column(name = "music_title")
	private String title;
	@Column(name = "music_duration")
	private Integer duration;
	@Lob
	@Column(name = "music_file")
	private byte[] musicFile;

	// Gestion de l'énumération de genre musical

//	@ElementCollection(targetClass = Genre.class, fetch = FetchType.EAGER)
//	@CollectionTable(name = "genre", joinColumns = @JoinColumn(name = "music_id", referencedColumnName = "id"))
//	@Enumerated(EnumType.STRING)
//	@Column(name = "music_genre")
//	private Set<Genre> genres;

	// Jointures

	@ManyToMany
	@JoinTable(name = "AlbumMusicAssociation", joinColumns = @JoinColumn(name = "id_music"), inverseJoinColumns = @JoinColumn(name = "id_album"))
	private Set<Album> albums;
	@ManyToMany
	@JoinTable(name = "PlaylistMusicAssociation", joinColumns = @JoinColumn(name = "id_music"), inverseJoinColumns = @JoinColumn(name = "id_playlist"))
	private Set<Playlist> playlists;

	// Constructeurs

	public Music() {
		super();
	}
	
	public Music(String title, Integer duration) {
		super();
		this.title = title;
		this.duration = duration;
	}

	// Getters & Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public byte[] getMusicFile() {
		return musicFile;
	}

	public void setMusicFile(byte[] musicFile) {
		this.musicFile = musicFile;
	}

//	public Set<Genre> getGenres() {
//		return genres;
//	}
//
//	public void setGenres(Set<Genre> genres) {
//		this.genres = genres;
//	}

	// Methods

	public void getInfo() {
	}

	// Hashcode & equals

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Music other = (Music) obj;
		return Objects.equals(id, other.id);
	}
}
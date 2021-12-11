package formation.sopra.projetMusicBoot.entities;

import java.util.HashSet;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "music")

@SequenceGenerator(name = "seqMusic", sequenceName = "seq_music", allocationSize = 1)
public class Music {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMusic")
	@Column(name = "music_id")
	@JsonView(JsonViews.Music.class)
	private Long id;
	@Column(name = "music_title")
	@JsonView({ JsonViews.Common.class, JsonViews.Album.class, JsonViews.Music.class, JsonViews.Playlist.class })
	private String title;
	@Column(name = "music_duration")
	@JsonView({ JsonViews.Common.class, JsonViews.Album.class, JsonViews.Music.class, JsonViews.Playlist.class })
	private Integer duration;
	@Lob
	@Column(name = "music_file")
	@JsonView(JsonViews.Music.class)
	private byte[] musicFile;

	// Gestion de l'enumeration de genre musical

	@CollectionTable(name = "GENRES")
	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = Genre.class)
	@JsonView(JsonViews.Music.class)
	private Set<Genre> genres;

	// Jointure de tables album et music via colonnes id_album et id_music ;
	// l'attribut albums recupere la jointure ; la colonne est deja nommee dans la
	// classe album
	// rajout de HashSet pour �viter les null pointer exceptions
	@ManyToMany(fetch = FetchType.LAZY)
	@JsonView({ JsonViews.Music.class, JsonViews.Playlist.class })
	@JoinTable(name = "AlbumMusicAssociation", joinColumns = @JoinColumn(name = "id_music"), inverseJoinColumns = @JoinColumn(name = "id_album"))
	private Set<Album> albums = new HashSet<Album>();

	// Jointure de tables playlist et music via colonnes id_music et id_playlist ;
	// l'attribut playlists recupere la jointure ; la colonne est deja nommee dans
	// la classe playlist
	// rajout de HashSet pour eviter les null pointer exceptions
	@ManyToMany(fetch = FetchType.LAZY)
	// @JsonView(JsonViews.MusicAvecPlaylist.class)
	@JoinTable(name = "PlaylistMusicAssociation", joinColumns = @JoinColumn(name = "id_music"), inverseJoinColumns = @JoinColumn(name = "id_playlist"))
	private Set<Playlist> playlists = new HashSet<Playlist>();

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

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	public Set<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Set<Playlist> playlists) {
		this.playlists = playlists;
	}

	public byte[] getMusicFile() {
		return musicFile;
	}

	public void setMusicFile(byte[] musicFile) {
		this.musicFile = musicFile;
	}


	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

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
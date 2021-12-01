package formation.sopra.projetMusicBoot.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
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

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "music")
@NamedQueries({
		// Selection de toutes les musiques (et leurs albums et artistes)
		@NamedQuery(name = "Music.findAll", query = "select mus from Music mus left join fetch mus.albums as alb left join fetch alb.artists as art"),

		// Selection de toutes les musiques (et leurs albums et artistes) nomm�es comme
		// l'input
		@NamedQuery(name = "Music.findByTitle", query = "select mus from Music mus left join fetch mus.albums as alb left join fetch alb.artists as art where mus.title=:title"),

		// Selection de toutes les musiques (et leurs albums) qui ont un album nomm�
		// comme l'input
		@NamedQuery(name = "Music.findByAlbum", query = "select mus from Music mus left join fetch mus.albums where mus.albums=:album"),

		// Selection de toutes les musiques (et leurs playlists) qui ont une playlist
		// nomm�e comme l'input
		@NamedQuery(name = "Music.findByPlaylist", query = "select mus from Music mus left join fetch mus.playlists where mus.playlists=:playlist"),

		// Selection de toutes les musiques (et leurs albums et artistes) qui ont un
		// artiste nomm� comme l'input
		@NamedQuery(name = "Music.findByArtist", query = "select mus from Music mus left join fetch mus.albums as alb left join fetch alb.artists as art where art=:artist") })

@SequenceGenerator(name = "seqMusic", sequenceName = "seq_music", allocationSize = 1)
public class Music {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMusic")
	@Column(name = "music_id")
	@JsonView(JsonViews.Admin.class)
	private Long id;
	@Column(name = "music_title")
	@JsonView(JsonViews.Common.class)
	private String title;
	@Column(name = "music_duration")
	@JsonView(JsonViews.Common.class)
	private Integer duration;
	@Lob
	@Column(name = "music_file")
	@JsonView(JsonViews.Admin.class)
	private byte[] musicFile;

	// Gestion de l'�num�ration de genre musical

//	@ElementCollection(targetClass = Genre.class, fetch = FetchType.EAGER)
//	@CollectionTable(name = "genre", joinColumns = @JoinColumn(name = "music_id", referencedColumnName = "id"))
	@Enumerated(EnumType.STRING)
	@Column(name = "music_genre")
	@JsonView(JsonViews.Music.class)
//	private Set<Genre> genres;
	private Genre genre;

	// Jointure de tables album et music via colonnes id_album et id_music ;
	// l'attribut albums r�cup�re la jointure ; la colonne est d�j� nomm�e dans la
	// classe album
	// rajout de HashSet pour �viter les null pointer exceptions
	@ManyToMany(fetch = FetchType.EAGER)
	@JsonView(JsonViews.MusicAvecAlbum.class)
	@JoinTable(name = "AlbumMusicAssociation", joinColumns = @JoinColumn(name = "id_music"), inverseJoinColumns = @JoinColumn(name = "id_album"))
	private Set<Album> albums = new HashSet<Album>();

	// Jointure de tables playlist et music via colonnes id_music et id_playlist ;
	// l'attribut playlists r�cup�re la jointure ; la colonne est d�j� nomm�e dans
	// la classe playlist
	// rajout de HashSet pour �viter les null pointer exceptions
	@ManyToMany(fetch = FetchType.EAGER)
	//@JsonView(JsonViews.MusicAvecPlaylist.class)
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

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
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
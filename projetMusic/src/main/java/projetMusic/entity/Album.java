package projetMusic.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "album")
// Requêtes
@NamedQueries({
		// Selection de tous les albums et leurs musiques par la jointure associée à
		// l'attribut musics
		@NamedQuery(name = "Album.findAll", query = "select alb from Album alb left join fetch alb.musics"),
		@NamedQuery(name = "Album.findByName", query = "select alb from Album alb left join fetch alb.artists left join fetch alb.musics where alb.name =:name"),
		// Selection de tous les albums (et leurs albums) qui ont une musique nommée
		// comme l'input
		@NamedQuery(name = "Album.findByMusic", query = "select alb from Album alb left join fetch alb.musics where alb.musics =:music"),
		// Selection de tous les albums (et leurs albums) qui ont un artiste nommé comme
		// l'input
		@NamedQuery(name = "Album.findByArtist", query = "select alb from Album alb left join fetch alb.artists where alb.artists =:artist"),
		@NamedQuery(name = "Album.findByGenre", query = "select alb from Album alb left join fetch alb.musics where alb.musics =:genre") })

@SequenceGenerator(name = "seqAlbum", sequenceName = "seq_album", allocationSize = 1)
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAlbum")
	@Column(name = "album_id")
	private Long id;
	@Column(name = "album_name")
	private String name;
	@Column(name = "album_music", length = 40)
	@ManyToMany
	@JoinTable(name = "AlbumMusicAssociation", joinColumns = @JoinColumn(name = "id_album"), inverseJoinColumns = @JoinColumn(name = "id_music"))
	private Set<Music> musics;
	@ManyToMany
	@JoinTable(name = "ArtistAlbumAssociation", joinColumns = @JoinColumn(name = "id_album"), inverseJoinColumns = @JoinColumn(name = "id_artist"))
	private Set<Artist> artists;
	@Lob
	@Column(name = "album_cover")
	private byte[] cover;

	// Constructeurs

	public Album() {
		super();
	}

	// Getters & Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Music> getMusics() {
		return musics;
	}

	public void setMusics(Set<Music> musics) {
		this.musics = musics;
	}

	public Set<Artist> getArtists() {
		return artists;
	}

	public void setArtists(Set<Artist> artists) {
		this.artists = artists;
	}

	public byte[] getCover() {
		return cover;
	}

	public void setCover(byte[] cover) {
		this.cover = cover;
	}

	// Methods

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
		Album other = (Album) obj;
		return Objects.equals(id, other.id);
	}

}
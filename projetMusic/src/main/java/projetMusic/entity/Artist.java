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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "artist")
// Requ�tes
@NamedQueries({
		// Selection de tous les artistes et leurs albums par la jointure associ�e �
		// l'attribut albums
		@NamedQuery(name = "Artist.findAll", query = "select art from Artist art left join fetch art.albums"),
		@NamedQuery(name = "Artist.findByName", query = "select art from Artist art left join fetch art.albums left join fetch art.albums.musics where art.name=:name"),
		// Selection de tous les artistes (et leurs albums) qui ont un album nomm� comme
		// l'input
		@NamedQuery(name = "Artist.findByAlbum", query = "select art from Artist art left join fetch art.albums where art.albums =:album"),
		// Selection de tous les artistes (et leurs albums) qui ont une musique nomm�e
		// comme l'input
		@NamedQuery(name = "Artist.findByMusic", query = "select art from Artist art left join fetch art.albums left join fetch art.albums.musics where art.albums.musics=:music"),
		@NamedQuery(name = "Artist.findByGenre", query = "select art from Artist art left join fetch art.albums left join fetch art.albums.musics where art.albums.musics=:genre") })
@SequenceGenerator(name = "seqArtist", sequenceName = "seq_artist", allocationSize = 1)
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqArtist")
	@Column(name = "artist_id")
	private Long id;
	@Column(name = "artist_name", nullable = false, length = 50)
	private String name;
	@Column(name = "artist_country", length = 20)
	private String country;

	// Jointure de tables artist et album via colonnes id_artist et id_album ;
	// l'attribut albums r�cup�re la jointure
	@Column(name = "artist_album", length = 40)
	@ManyToMany
	@JoinTable(name = "ArtistAlbumAssociation", joinColumns = @JoinColumn(name = "id_artist"), inverseJoinColumns = @JoinColumn(name = "id_album"))
	private Set<Album> albums;
//	@Column(name = "artist_genre")
//	//jointure
//	private Set<Genre> genres;

	// Constructeurs

	public Artist() {
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

//	public Set<Genre> getGenres() {
//		return genres;
//	}
//
//	public void setGenres(Set<Genre> genres) {
//		this.genres = genres;
//	}

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
		Artist other = (Artist) obj;
		return Objects.equals(id, other.id);
	}
}
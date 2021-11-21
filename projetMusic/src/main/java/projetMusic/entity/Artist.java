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
// Requêtes
@NamedQueries({
		// Selection de tous les artistes (et leurs albums) par la jointure associée à l'attribut albums
		@NamedQuery(name = "Artist.findAll", query = "select art from Artist art left join fetch art.albums"),
		
		// Selection des artistes (et leurs albums) nommés comme l'input
		@NamedQuery(name = "Artist.findByName", query = "select art from Artist art left join fetch art.albums as alb left join fetch alb.musics as mus where art.name=:name"),		
		
		// Selection de tous les artistes (et leurs albums) qui ont un album nommé comme l'input
		@NamedQuery(name = "Artist.findByAlbum", query = "select art from Artist art left join fetch art.albums where art.albums =:album"),
		
		// Selection de tous les artistes (et leurs albums) qui ont une musique nommée comme l'input
		@NamedQuery(name = "Artist.findByMusic", query = "select art from Artist art left join fetch art.albums as alb left join fetch alb.musics as mus where alb.musics=:music"),
		
		// Selection de tous les artistes (et leurs albums) qui ont un genre nommé comme l'input
		@NamedQuery(name = "Artist.findByGenre", query = "select art from Artist art left join fetch art.albums as alb left join fetch alb.musics as mus where alb.musics=:genre")})

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

	// Jointure de tables artist et album via colonnes id_artist et id_album ; l'attribut albums récupère la jointure
	@Column(name = "artist_album", length = 40)
	@ManyToMany
	@JoinTable(name = "ArtistAlbumAssociation", joinColumns = @JoinColumn(name = "id_artist"), inverseJoinColumns = @JoinColumn(name = "id_album"))
	private Set<Album> albums;

	// Constructeurs

	public Artist() {
		super();
	}

	public Artist(String name, String country) {
		super();
		this.name = name;
		this.country = country;
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
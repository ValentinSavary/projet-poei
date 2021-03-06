package formation.sopra.projetMusicBoot.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "artist")

@SequenceGenerator(name = "seqArtist", sequenceName = "seq_artist", allocationSize = 1)
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqArtist")
	@Column(name = "artist_id")
	@JsonView(JsonViews.Artist.class)
	private Long id;
	@Column(name = "artist_name", nullable = false, length = 50)
	@JsonView({JsonViews.Common.class, JsonViews.Album.class, JsonViews.Artist.class, JsonViews.Music.class, JsonViews.Playlist.class})
	private String name;
	@Column(name = "artist_country", length = 20)
	@JsonView({JsonViews.Common.class, JsonViews.Artist.class})
	private String country;

	// Jointure de tables artist et album via colonnes id_artist et id_album ;
	// l'attribut albums recupere la jointure
	// rajout de HashSet pour eviter les null pointer exceptions
	@Column(name = "artist_album", length = 40)
	@ManyToMany(fetch = FetchType.LAZY)
	@JsonView(JsonViews.Artist.class)
	@JoinTable(name = "ArtistAlbumAssociation", joinColumns = @JoinColumn(name = "id_artist"), inverseJoinColumns = @JoinColumn(name = "id_album"))
	private Set<Album> albums = new HashSet<Album>();

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
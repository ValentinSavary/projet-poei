package formation.sopra.projetMusicBoot.entities;

import java.time.Year;
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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "album")

@SequenceGenerator(name = "seqAlbum", sequenceName = "seq_album", allocationSize = 1)
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAlbum")
	@Column(name = "album_id")
	@JsonView({JsonViews.Admin.class, JsonViews.Album.class})
	private Long id;
	@Column(name = "album_name")
	@JsonView({ JsonViews.Common.class, JsonViews.Album.class, JsonViews.Artist.class, JsonViews.Music.class,
			JsonViews.Playlist.class })
	private String name;
	@Column(name = "album_year")
	@JsonView({ JsonViews.Common.class, JsonViews.Album.class, JsonViews.Artist.class })
	private Year year;
	@Column(name = "album_cover")
	@Lob
	@JsonView({ JsonViews.Common.class, JsonViews.Album.class, JsonViews.Artist.class, JsonViews.Music.class,
			JsonViews.Playlist.class })
	private byte[] cover;

	// Jointure de tables album et artist via colonnes id_album et id_artist ;
	// l'attribut artists r�cup�re la jointure ; la colonne est d�j� nomm�e dans la
	// classe artist
	// rajout de HashSet pour �viter les null pointer exceptions
	@ManyToMany(fetch = FetchType.LAZY)
	@JsonView({ JsonViews.Album.class, JsonViews.Playlist.class })
	@JoinTable(name = "ArtistAlbumAssociation", joinColumns = @JoinColumn(name = "id_album"), inverseJoinColumns = @JoinColumn(name = "id_artist"))
	private Set<Artist> artists = new HashSet<Artist>();

	// Jointure de tables album et music via colonnes id_artist et id_music ;
	// l'attribut musics r�cup�re la jointure
	// rajout de HashSet pour �viter les null pointer exceptions
	@Column(name = "album_music", length = 40)
	@ManyToMany(fetch = FetchType.LAZY)
	@JsonView(JsonViews.Album.class)
	@JoinTable(name = "AlbumMusicAssociation", joinColumns = @JoinColumn(name = "id_album"), inverseJoinColumns = @JoinColumn(name = "id_music"))
	private Set<Music> musics = new HashSet<Music>();

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

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
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
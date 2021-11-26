package projetMusic.entity;

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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "playlist")
@NamedQueries({
		// Selection des playlists (et leurs musiques) nommée comme l'input
		@NamedQuery(name = "Playlist.findByName", query = "select pla from Playlist pla left join fetch pla.musics as mus left join fetch mus.albums as alb left join fetch alb.artists art where pla.name=:name") })

@SequenceGenerator(name = "seqPlaylist", sequenceName = "seq_playlist", allocationSize = 1, initialValue = 100)
public class Playlist {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPlaylist")
	@Column(name = "playlist_id")
	@JsonView(JsonViews.Admin.class)
	private Long id;

	@Column(name = "playlist_name")
	@JsonView(JsonViews.Common.class)
	private String name;

	@Column(name = "playlist_typePrivate")
	@JsonView(JsonViews.Common.class)
	private boolean typePrivate = false;

	// Jointure de tables playlist et music via colonnes id_playlist et id_music ;
	// l'attribut musics récupère la jointure
	// rajout de HashSet pour éviter les null pointer exceptions
	@Column(name = "playlist_music", length = 40)
	@ManyToMany(fetch = FetchType.EAGER)
	@JsonView(JsonViews.PlaylistAvecMusic.class)
	@JoinTable(name = "PlaylistMusicAssociation", joinColumns = @JoinColumn(name = "id_playlist"), inverseJoinColumns = @JoinColumn(name = "id_music"))
	private Set<Music> musics = new HashSet<Music>();

	// Jointure de tables user et playlists
	@ManyToOne
	@JsonView(JsonViews.Common.class)
	@JoinColumn(name = "user_id")
	private User user;

	@Version
	@Column(name = "playlist_version")
	private int version;

	// Constructeurs

	public Playlist() {
		super();
	}

	public Playlist(String name) {
		super();
		this.name = name;
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

	public boolean isTypePrivate() {
		return typePrivate;
	}

	public void setTypePrivate(boolean typePrivate) {
		this.typePrivate = typePrivate;
	}

	public Set<Music> getMusics() {
		return musics;
	}

	public void setMusics(Set<Music> musics) {
		this.musics = musics;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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
		Playlist other = (Playlist) obj;
		return Objects.equals(id, other.id);
	}
}
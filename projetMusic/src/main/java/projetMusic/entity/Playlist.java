package projetMusic.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "playlist")
@SequenceGenerator(name = "seqPlaylist", sequenceName = "seq_playlist", allocationSize = 1, initialValue = 100)
public class Playlist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPlaylist")
	@Column(name = "playlist_id")
	private Long id;
	
	@Column(name = "playlist_name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Version
	@Column(name = "playlist_version")
	private int version;

	public Playlist() {
		super();
	}

	public Playlist(String name) {
		super();
		this.name = name;
	}

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

	public void addTrack(Music music) {
		
	}
	
	public void removeTrack(Music music) {
		
	}
	
	public void delete() {
		
	}

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

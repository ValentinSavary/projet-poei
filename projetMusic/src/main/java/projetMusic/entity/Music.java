package projetMusic.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "music")
@SequenceGenerator(name = "seqMusic", sequenceName = "seq_music", allocationSize = 1)
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
	@Column(name = "music_genre")
	private Set<Genre> genres;

	// Constructeurs

	public Music() {
		super();
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

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

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
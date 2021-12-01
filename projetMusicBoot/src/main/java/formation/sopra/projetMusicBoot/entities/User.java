package formation.sopra.projetMusicBoot.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "users")

@SequenceGenerator(name = "seqUser", sequenceName = "seq_user", allocationSize = 1, initialValue = 100)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUser")
	@Column(name = "user_id")
	@JsonView(JsonViews.Admin.class)
	private Long id;
	@Column(name = "user_username")
	@JsonView(JsonViews.Common.class)
	private String username;
	@Column(name = "user_login")
	@JsonView(JsonViews.User.class)
	private String login;
	@Column(name = "user_password")
	@JsonView(JsonViews.User.class)
	private String password;
	@Column(name = "user_accountType")
	@Enumerated(EnumType.STRING)
	@JsonView(JsonViews.User.class)
	private AccountType accountType;
	@Version
	@Column(name = "user_version")
	private int version;

	// Jointure de tables user et playlist ; l'attribut playlists r�cup�re la
	// jointure
	// rajout de HashSet pour �viter les null pointer exceptions
	@OneToMany(mappedBy = "user")
	private Set<Playlist> playlists = new HashSet<Playlist>();

	// Constructeurs

	public User() {
		super();
	}

	public User(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	// Getters & Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Set<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Set<Playlist> playlists) {
		this.playlists = playlists;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
}
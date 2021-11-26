package projetMusic.entity;

public class JsonViews {

	public static class Common {

	}

	public static class Admin extends User {

	}

	public static class User extends Common {

	}

	public static class AlbumAvecArtist extends MusicAvecAlbum {

	}

	public static class AlbumAvecMusic extends Common {

	}

	public static class PlaylistAvecMusic extends Common {

	}

	public static class Artist extends Common {

	}

	public static class Music extends Common {

	}

	public static class MusicAvecAlbum extends Music {

	}

	public static class ArtistAvecAlbum extends Artist {

	}

}

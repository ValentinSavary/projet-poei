package formation.sopra.projetMusicBoot.exceptions;

public class AlbumException extends RuntimeException {

	private static final long serialVersionUID = -9127802093331858619L;

	public AlbumException() {
	}

	public AlbumException(String message) {
		super(message);
	}
}
package projetMusic.exceptions;

public class PlaylistException extends RuntimeException {
	public PlaylistException() {
	}

	public PlaylistException(String message) {
		super(message);
	}
}
package projetMusic.dao;

public class DaoAlbumFactory {
	private static DaoAlbum daoAlbum = null;

	public static DaoAlbum getInstance() {
		if (daoAlbum == null) {
			daoAlbum = new DaoAlbumJpaImpl();
		}
		return daoAlbum;
	}
}

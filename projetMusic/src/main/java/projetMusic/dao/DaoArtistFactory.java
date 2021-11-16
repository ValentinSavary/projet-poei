package projetMusic.dao;

public class DaoArtistFactory {
	private static DaoArtist daoArstist = null;

	public static DaoArtist getInstance() {
		if (daoArstist == null) {
			daoArstist = new DaoArtistJpaImpl();
		}
		return daoArstist;
	}
}

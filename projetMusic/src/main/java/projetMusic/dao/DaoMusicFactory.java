package projetMusic.dao;

public class DaoMusicFactory {
	private static DaoMusic daoMusic = null;

	public static DaoMusic getInstance() {
		if (daoMusic == null) {
			daoMusic = new DaoMusicJpaImpl();
		}
		return daoMusic;
	}
}

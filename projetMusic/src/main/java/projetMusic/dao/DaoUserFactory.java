package projetMusic.dao;

public class DaoUserFactory {
	private static DaoUser daoUser = null;

	public static DaoUser getInstance() {
		if (daoUser == null) {
			daoUser = new DaoUserJpaImpl();
		}
		return daoUser;
	}
}

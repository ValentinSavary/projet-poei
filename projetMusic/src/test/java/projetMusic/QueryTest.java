package projetMusic;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;

import projetMusic.entity.Genre;
import projetMusic.entity.Music;
import projetMusic.util.Context;

public class QueryTest {

	@Test
	public void testMusic() {
		// Context.getInstance();
		Music music1 = new Music();
		music1.setTitle("test");
		Set<Genre> genres = new HashSet<Genre>();
		genres.add(Genre.Classical);
		//music1.setGenres(genres);

		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(music1);
		tx.commit();
		em.close();

		Context.destroy();
	}

}

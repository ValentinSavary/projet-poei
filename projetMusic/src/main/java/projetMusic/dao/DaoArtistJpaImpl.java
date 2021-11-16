package projetMusic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import projetMusic.entity.Artist;
import projetMusic.util.Context;

class DaoArtistJpaImpl implements DaoArtist {

	@Override
	public List<Artist> findAll() {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		TypedQuery<Artist> query = em.createQuery("from Artist art", Artist.class);
		List<Artist> artists = query.getResultList();
		em.close();
		return artists;
	}

	@Override
	public Artist findByKey(Long key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		Artist artist = em.find(Artist.class, key);
		em.close();
		return artist;
	}

	@Override
	public void insert(Artist obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(obj);
		tx.commit();
		em.close();
	}

	@Override
	public Artist update(Artist obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(obj);
		tx.commit();
		em.close();
		return obj;
	}

	@Override
	public void delete(Artist obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(em.merge(obj));
		tx.commit();
		em.close();
	}

	@Override
	public void deleteByKey(Long key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(em.find(Artist.class, key));
		tx.commit();
		em.close();
	}

}

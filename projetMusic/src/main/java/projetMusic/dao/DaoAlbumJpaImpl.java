package projetMusic.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import projetMusic.entity.Album;
import projetMusic.util.Context;

class DaoAlbumJpaImpl implements DaoAlbum {

	@Override
	public List<Album> findAll() {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		TypedQuery<Album> query = em.createQuery("from Album alb", Album.class);
		List<Album> albums = query.getResultList();
		em.close();
		return albums;
	}

	@Override
	public Album findByKey(Long key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		Album album = em.find(Album.class, key);
		em.close();
		return album;
	}

	@Override
	public void insert(Album obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(obj);
		tx.commit();
		em.close();
	}

	@Override
	public Album update(Album obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(obj);
		tx.commit();
		em.close();
		return obj;
	}

	@Override
	public void delete(Album obj) {
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
		em.remove(em.find(Album.class, key));
		tx.commit();
		em.close();
	}

}

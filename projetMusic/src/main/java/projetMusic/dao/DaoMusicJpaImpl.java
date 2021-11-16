package projetMusic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import projetMusic.entity.Music;
import projetMusic.util.Context;

class DaoMusicJpaImpl implements DaoMusic {

	@Override
	public List<Music> findAll() {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		TypedQuery<Music> query = em.createQuery("from Music mus", Music.class);
		List<Music> musics = query.getResultList();
		em.close();
		return musics;
	}

	@Override
	public Music findByKey(Long key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		Music music = em.find(Music.class, key);
		em.close();
		return music;
	}

	@Override
	public void insert(Music obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(obj);
		tx.commit();
		em.close();
	}

	@Override
	public Music update(Music obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(obj);
		tx.commit();
		em.close();
		return obj;
	}

	@Override
	public void delete(Music obj) {
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
		em.remove(em.find(Music.class, key));
		tx.commit();
		em.close();
	}

}

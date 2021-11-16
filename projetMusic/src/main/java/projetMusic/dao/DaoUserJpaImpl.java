package projetMusic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import projetMusic.entity.User;
import projetMusic.util.Context;

class DaoUserJpaImpl implements DaoUser {

	@Override
	public List<User> findAll() {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		TypedQuery<User> query = em.createQuery("from User user", User.class);
		List<User> users = query.getResultList();
		em.close();
		return users;
	}

	@Override
	public User findByKey(Long key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		User user = em.find(User.class, key);
		em.close();
		return user;
	}

	@Override
	public void insert(User obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(obj);
		tx.commit();
		em.close();
	}

	@Override
	public User update(User obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(obj);
		tx.commit();
		em.close();
		return obj;
	}

	@Override
	public void delete(User obj) {
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
		em.remove(em.find(User.class, key));
		tx.commit();
		em.close();
	}

}

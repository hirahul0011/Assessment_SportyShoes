package com.ecommerce.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
//import org.hibernate.query.Query;
//import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ecommerce.entity.Purchase;

import jakarta.persistence.EntityManager;

import jakarta.persistence.Query; 

@Repository
@Component
public class PurchaseDAO {

//	@Autowired
//    private SessionFactory sessionFactory;
	
	@Autowired
	EntityManager em;

//	@SuppressWarnings("unchecked")
//	public Purchase getPurchaseById(long id) {
//		String strId = String.valueOf(id);
////		List<Purchase> list = this.sessionFactory.getCurrentSession().createQuery("from Purchase where id=" + strId).list();
//		List<Purchase> list = this.sessionFactory.openSession().createQuery("from Purchase where id=" + strId).list();
//		if (list.size() > 0)
//			return (Purchase) list.get(0);
//		else
//			return null;
//		
//	}
	
	@SuppressWarnings("unchecked")
	public Purchase getPurchaseById(long id) {
		String strId = String.valueOf(id);
//		List<Purchase> list = this.sessionFactory.getCurrentSession().createQuery("from Purchase where id=" + strId).list();
		List<Purchase> list = em.createQuery("from Purchase where id=" + strId).getResultList();
		if (list.size() > 0)
			return (Purchase) list.get(0);
		else
			return null;
		
	}
	
//	@SuppressWarnings("unchecked")
//	public List<Purchase> getAllItems() {
////		List<Purchase> list = this.sessionFactory.getCurrentSession().createQuery("from Purchase order by ID desc").list();
//		List<Purchase> list = this.sessionFactory.openSession().createQuery("from Purchase order by ID desc").list();
//		return list;
//	}
	
	@SuppressWarnings("unchecked")
	public List<Purchase> getAllItems() {
//		List<Purchase> list = this.sessionFactory.getCurrentSession().createQuery("from Purchase order by ID desc").list();
		List<Purchase> list = em.createQuery("from Purchase order by ID desc").getResultList();
		return list;
	}
		
//	@SuppressWarnings("unchecked")
//	public List<Purchase> getAllItemsByUserId(long userId) {
//		String strId = String.valueOf(userId);
////		List<Purchase> list = this.sessionFactory.getCurrentSession().createQuery("from Purchase where user_id=" + strId + " order by ID desc").list();
//		List<Purchase> list = this.sessionFactory.openSession().createQuery("from Purchase where user_id=" + strId + " order by ID desc").list();
//		return list;
//	}
	
	@SuppressWarnings("unchecked")
	public List<Purchase> getAllItemsByUserId(long userId) {
		String strId = String.valueOf(userId);
//		List<Purchase> list = this.sessionFactory.getCurrentSession().createQuery("from Purchase where user_id=" + strId + " order by ID desc").list();
//		List<Purchase> list = em.createQuery("from Purchase where ID=" + strId + " order by ID desc").getResultList();
		List<Purchase> list = em.createQuery("from Purchase where userId=:userId order by ID desc")
				.setParameter("userId", userId)
				.getResultList();
		return list;
	}
	
//	@SuppressWarnings("unchecked")
//	public long  updatePurchase(Purchase purchase) {
//		String sql = "";
//		long newId = 0;
//		if (purchase.getID() == 0) {
////			this.sessionFactory.getCurrentSession().save(purchase);
//			this.sessionFactory.openSession().save(purchase);
//			newId = purchase.getID();
//		} else {
////			Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
//			Query query = this.sessionFactory.openSession().createQuery(sql);
//			query.setParameter("user_id", purchase.getUserId());
//			query.setParameter("gross_total", purchase.getTotal());
//			
//			query.executeUpdate();
//		}
//		return newId;
//	}
	
	@SuppressWarnings("unchecked")
	public long  updatePurchase(Purchase purchase) {
		String sql = "";
		long newId = 0;
		if (purchase.getID() == 0) {
//			this.sessionFactory.getCurrentSession().save(purchase);
			em.persist(purchase);
			newId = purchase.getID();
		} else {
//			Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
			Query query = em.createQuery(sql);
			query.setParameter("user_id", purchase.getUserId());
			query.setParameter("gross_total", purchase.getTotal());
			
			query.executeUpdate();
		}
		return newId;
	}
	

	
}

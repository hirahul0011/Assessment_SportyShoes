package com.ecommerce.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Session;
//import org.hibernate.query.Query;
//import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.entity.Category;

import jakarta.persistence.EntityManager;

import jakarta.persistence.Query; 

@Repository
@Component
@Transactional
public class CategoryDAO {

//	@Autowired
//    private SessionFactory sessionFactory;
	
	@Autowired
	EntityManager em;
	
//	@SuppressWarnings("deprecation")
//	public void insertValuesCategory() {
//		this.sessionFactory.openSession().createNativeQuery(
//				"INSERT INTO CATEGORY  VALUES(20001, 'Category_20001');"
//				+ "INSERT INTO CATEGORY  VALUES(20002, 'Category_20002');"
//				+ "INSERT INTO CATEGORY  VALUES(20003, 'Category_20003');"
//				+ "INSERT INTO CATEGORY  VALUES(20004, 'Category_20004');"
//				+ "INSERT INTO CATEGORY  VALUES(20005, 'Category_20005');")
//		.executeUpdate();
//	}
	
	public void insertValuesCategoryEM() {
		em.createNativeQuery(
				"INSERT INTO CATEGORY  VALUES(20001, 'Category_20001');"
				+ "INSERT INTO CATEGORY  VALUES(20002, 'Category_20002');"
				+ "INSERT INTO CATEGORY  VALUES(20003, 'Category_20003');"
				+ "INSERT INTO CATEGORY  VALUES(20004, 'Category_20004');"
				+ "INSERT INTO CATEGORY  VALUES(20005, 'Category_20005');")
		.executeUpdate();
	}

//	@SuppressWarnings("unchecked")
//	public Category getCategoryById(long id) {
//		String strId = String.valueOf(id);
////		List<Category> list = this.sessionFactory.getCurrentSession().createQuery("from Category where id=" + strId).list();
//		List<Category> list = this.sessionFactory.openSession().createQuery("from Category where id=" + strId).list();
//		if (list.size() > 0)
//			return (Category) list.get(0);
//		else
//			return null;
//		
//	}
	
	@SuppressWarnings("unchecked")
	public Category getCategoryById(long id) {
		String strId = String.valueOf(id);
//		List<Category> list = this.sessionFactory.getCurrentSession().createQuery("from Category where id=" + strId).list();
		List<Category> list = em.createQuery("from Category where id=" + strId).getResultList();
		if (list.size() > 0)
			return (Category) list.get(0);
		else
			return null;
		
	}
	
	
//	@SuppressWarnings("unchecked")
//	public void updateCategory(Category category) {
//		String sql = "";
//		if (category.getID() == 0)
////			this.sessionFactory.getCurrentSession().save(category);
//			this.sessionFactory.openSession().save(category);
//		else if (category.getID() > 0) {
//			sql = "update Category set name=:name where " +
//					" ID=:id";
////			Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
//			Query query = this.sessionFactory.openSession().createQuery(sql);
//			query.setParameter("name", category.getName());
//			if (category.getID() > 0)
//				query.setParameter("id", category.getID());
//			
//			query.executeUpdate();
//		}
//		
//	}
	
	@SuppressWarnings("unchecked")
	public void updateCategory(Category category) {
		String sql = "";
		if (category.getID() == 0)
//			this.sessionFactory.getCurrentSession().save(category);
			em.persist(category);
		else if (category.getID() > 0) {
			sql = "update Category set name=:name where " +
					" ID=:id";
//			Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
			Query query = em.createQuery(sql);
			query.setParameter("name", category.getName());
			if (category.getID() > 0)
				query.setParameter("id", category.getID());
			
			query.executeUpdate();
		}
		
	}
	

//	@SuppressWarnings("unchecked")
//	public void deleteCategory(long id) {
//		// mark all category_id of products with this category to zero before deleting the category row 
//		String sql = "";
//		sql = "update Product set category_id=0 where category_id=:id";
//
////		Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
//		Query query = this.sessionFactory.openSession().createQuery(sql);
//		query.setParameter("id", id);
//		query.executeUpdate();
//
//		sql = "delete from Category where ID=:id";
////		query = this.sessionFactory.getCurrentSession().createQuery(sql);
//		query = this.sessionFactory.openSession().createQuery(sql);
//		query.setParameter("id", id);
//		query.executeUpdate();
//		
//	}
	
	@SuppressWarnings("unchecked")
	public void deleteCategory(long id) {
		// mark all category_id of products with this category to zero before deleting the category row 
		String sql = "";
		sql = "update Product set categoryId=0 where categoryId=:id";

//		Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
		Query query = em.createQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();

		sql = "delete from Category where ID=:id";
//		query = this.sessionFactory.getCurrentSession().createQuery(sql);
		query = em.createQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();
		
	}

//	@SuppressWarnings("unchecked")
//	public List<Category> getAllCategories() {
////		List<Category> list = this.sessionFactory.getCurrentSession().createQuery("from Category order by name").list();
//		List<Category> list = this.sessionFactory.openSession().createQuery("from Category order by name").list();
//
//		return list;
//	}
	
	@SuppressWarnings("unchecked")
	public List<Category> getAllCategories() {
//		List<Category> list = this.sessionFactory.getCurrentSession().createQuery("from Category order by name").list();
		List<Category> list = em.createQuery("from Category order by name").getResultList();

		return list;
	}
	
}

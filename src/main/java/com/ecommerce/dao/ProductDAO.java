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
import com.ecommerce.entity.Product;

import jakarta.persistence.EntityManager;

import jakarta.persistence.Query; 

@Repository
@Component
@Transactional
public class ProductDAO {

//	@Autowired
//    private SessionFactory sessionFactory;
	
	@Autowired
	EntityManager em;
	
//	@SuppressWarnings("deprecation")
//	public void insertValuesProduct() {
//		this.sessionFactory.openSession().createNativeQuery(
//				"INSERT INTO EPRODUCT VALUES(20000,20001,'2020-03-20',30001,'Product_30001');"
//				+ "INSERT INTO EPRODUCT VALUES(30000,20002,'2020-03-20',30002,'Product_30002');"
//				+ "INSERT INTO EPRODUCT VALUES(40000,20003,'2020-03-20',30003,'Product_30003');"
//				+ "INSERT INTO EPRODUCT VALUES(50000,20004,'2020-03-20',30004,'Product_30004');"
//				+ "INSERT INTO EPRODUCT VALUES(60000,20005,'2020-03-20',30005,'Product_30005');")
//		.executeUpdate();
//	}
	
	public void insertValuesProductEM() {
		em.createNativeQuery(
				"INSERT INTO EPRODUCT VALUES(20000,20001,'2020-03-20',30001,'Product_30001');"
				+ "INSERT INTO EPRODUCT VALUES(30000,20002,'2020-03-20',30002,'Product_30002');"
				+ "INSERT INTO EPRODUCT VALUES(40000,20003,'2020-03-20',30003,'Product_30003');"
				+ "INSERT INTO EPRODUCT VALUES(50000,20004,'2020-03-20',30004,'Product_30004');"
				+ "INSERT INTO EPRODUCT VALUES(60000,20005,'2020-03-20',30005,'Product_30005');")
		.executeUpdate();
	}	

//	@SuppressWarnings("unchecked")
//	public Product getProductById(long id) {
//		String strId = String.valueOf(id);
////		List<Product> list = this.sessionFactory.getCurrentSession().createQuery("from Product where id=" + strId).list();
//		List<Product> list = this.sessionFactory.openSession().createQuery("from Product where id=" + strId).list();
//		if (list.size() > 0)
//			return (Product) list.get(0);
//		else
//			return null;
//		
//	}
	
	@SuppressWarnings("unchecked")
	public Product getProductById(long id) {
		String strId = String.valueOf(id);
//		List<Product> list = this.sessionFactory.getCurrentSession().createQuery("from Product where id=" + strId).list();
		List<Product> list = em.createQuery("from Product where id=" + strId).getResultList();
		if (list.size() > 0)
			return (Product) list.get(0);
		else
			return null;
		
	}
	
	
//	@SuppressWarnings("unchecked")
//	public void updateProduct(Product product) {
//		String sql = "";
//		if (product.getID() == 0)
////				this.sessionFactory.getCurrentSession().save(product);
//				this.sessionFactory.openSession().save(product);
//		else if (product.getID() > 0) {
//			sql = "update Product set name=:name, price=:price, category_id=:category_id where " +
//					" ID=:id";
////			Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
//			Query query = this.sessionFactory.openSession().createQuery(sql);
//			query.setParameter("name", product.getName());
//			query.setParameter("price", product.getPrice());
//			query.setParameter("category_id", product.getCategoryId());
//			query.setParameter("id", product.getID());
//			
//			query.executeUpdate();
//		}
//		
//	}
	
	@SuppressWarnings("unchecked")
	public void updateProduct(Product product) {
		String sql = "";
		if (product.getID() == 0)
//				this.sessionFactory.getCurrentSession().save(product);
				em.persist(product);
		else if (product.getID() > 0) {
			sql = "update Product set name=:name, price=:price, categoryId=:category_id where " +
					" ID=:id";
//			Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
			Query query = em.createQuery(sql);
			query.setParameter("name", product.getName());
			query.setParameter("price", product.getPrice());
			query.setParameter("category_id", product.getCategoryId());
			query.setParameter("id", product.getID());
			
			query.executeUpdate();
		}
		
	}
	

//	@SuppressWarnings("unchecked")
//	public void deleteProduct(long id) {
//		// delete all purchase items for this product before deleting this product
//		// Pending:Purchase total is not updated in the purchase total - it shows the old value
//		
//		String sql = "";
//		sql = "delete from PurchaseItem where product_id=:id";
////		Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
//		Query query = this.sessionFactory.openSession().createQuery(sql);
//		query.setParameter("id", id);
//
//		sql = "delete from Product where ID=:id";
////		query = this.sessionFactory.getCurrentSession().createQuery(sql);
//		query = this.sessionFactory.openSession().createQuery(sql);
//		query.setParameter("id", id);
//		 
//		query.executeUpdate();
//		
//	}
	
	@SuppressWarnings("unchecked")
	public void deleteProduct(long id) {
		// delete all purchase items for this product before deleting this product
		// Pending:Purchase total is not updated in the purchase total - it shows the old value
		
		String sql = "";
		sql = "delete from PurchaseItem where productId=:id";
//		Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
		Query query = em.createQuery(sql);
		query.setParameter("id", id);

		sql = "delete from Product where ID=:id";
//		query = this.sessionFactory.getCurrentSession().createQuery(sql);
		query = em.createQuery(sql);
		query.setParameter("id", id);
		 
		query.executeUpdate();
		
	}

//	@SuppressWarnings("unchecked")
//	public List<Product> getAllProducts() {
////		List<Product> list = this.sessionFactory.getCurrentSession().createQuery("from Product order by name").list();
//		List<Product> list = this.sessionFactory.openSession().createQuery("from Product order by name").list();
//
//		return list;
//	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {
//		List<Product> list = this.sessionFactory.getCurrentSession().createQuery("from Product order by name").list();
		List<Product> list = em.createQuery("from Product order by name").getResultList();

		return list;
	}
	
	
	
}

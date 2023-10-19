package com.ecommerce.dao;

import java.util.List;

import org.hibernate.SessionFactory;
//import org.hibernate.query.Query;
import org.hibernate.Session;
//import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ecommerce.entity.Admin;

import jakarta.persistence.EntityManager;

import jakarta.persistence.Query; 

@Repository
@Component
@Transactional
public class AdminDAO {

//	@Autowired
//    private SessionFactory sessionFactory;
	
	@Autowired
	EntityManager em;
	
//	@SuppressWarnings("deprecation")
//	public void insertValuesAdmin() {
//		this.sessionFactory.openSession().createNativeQuery(
//				"INSERT INTO ADMIN VALUES(10001, 'Admin_10001','Admin_100010*');"
//				+ "INSERT INTO ADMIN VALUES(10002, 'Admin_10002','Admin_100020*');"
//				+ "INSERT INTO ADMIN VALUES(10003, 'Admin_10003','Admin_100030*');"
//				+ "INSERT INTO ADMIN VALUES(10004, 'Admin_10004','Admin_100040*');"
//				+ "INSERT INTO ADMIN VALUES(10005, 'Admin_10005','Admin_100050*');")
//		.executeUpdate();
//	}
	
	public void insertValuesAdminEM() {
		em.createNativeQuery(
				"INSERT INTO ADMIN VALUES(10001, 'Admin_10001','Admin_100010*');"
				+ "INSERT INTO ADMIN VALUES(10002, 'Admin_10002','Admin_100020*');"
				+ "INSERT INTO ADMIN VALUES(10003, 'Admin_10003','Admin_100030*');"
				+ "INSERT INTO ADMIN VALUES(10004, 'Admin_10004','Admin_100040*');"
				+ "INSERT INTO ADMIN VALUES(10005, 'Admin_10005','Admin_100050*');")
		.executeUpdate();
	}

//	@SuppressWarnings("unchecked")
//	public Admin authenticate(String adminId, String pwd) {
////		List<Admin> list = this.sessionFactory.getCurrentSession().createQuery("from Admin where admin_id=:admin_id and admin_pwd=:admin_pwd")
//		List<Admin> list = this.sessionFactory.openSession().createQuery("from Admin where admin_id=:admin_id and admin_pwd=:admin_pwd")
//				.setParameter("admin_id", adminId)
//				.setParameter("admin_pwd", pwd)
//				.list();
//		
//		if (list.size() > 0)
//			return (Admin) list.get(0);
//		else
//			return null;
//		
//	}
	
	@SuppressWarnings("unchecked")
	public Admin authenticate(String adminId, String pwd) {
//		List<Admin> list = this.sessionFactory.getCurrentSession().createQuery("from Admin where admin_id=:admin_id and admin_pwd=:admin_pwd")
		List<Admin> list = em.createQuery("from Admin where adminId=:admin_id and pwd=:admin_pwd")
				.setParameter("admin_id", adminId)
				.setParameter("admin_pwd", pwd)
				.getResultList();
		
		if (list.size() > 0)
			return (Admin) list.get(0);
		else
			return null;
		
	}
	
//	@SuppressWarnings("unchecked")
//	public Admin getAdminById(long id) {
////		List<Admin> list = this.sessionFactory.getCurrentSession().createQuery("from Admin where ID=:admin_id")
//		List<Admin> list = this.sessionFactory.openSession().createQuery("from Admin where ID=:admin_id")
//				.setParameter("admin_id", id)
//				.list();
//		if (list.size() > 0)
//			return (Admin) list.get(0);
//		else
//			return null;
//		
//	}
	
	@SuppressWarnings("unchecked")
	public Admin getAdminById(long id) {
//		List<Admin> list = this.sessionFactory.getCurrentSession().createQuery("from Admin where ID=:admin_id")
		List<Admin> list = em.createQuery("from Admin where ID=:admin_id")
				.setParameter("admin_id", id)
				.getResultList();
		if (list.size() > 0)
			return (Admin) list.get(0);
		else
			return null;
		
	}
	
	
//	@SuppressWarnings("unchecked")
//	public void updatePwd(Admin admin) {
//		
//		String sql = "update Admin set admin_pwd=:admin_pwd where ID=:id";
////		Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
//		Query query = this.sessionFactory.openSession().createQuery(sql);
//		query.setParameter("admin_pwd", admin.getAdminPwd());
//		query.setParameter("id", admin.getID());
//		
//		query.executeUpdate();
//		
//	}
	
	@SuppressWarnings("unchecked")
	public void updatePwd(Admin admin) {
		
		String sql = "update Admin set pwd=:admin_pwd where ID=:id";
//		Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
		Query query = em.createQuery(sql);
		query.setParameter("admin_pwd", admin.getAdminPwd());
		query.setParameter("id", admin.getID());
		
		query.executeUpdate();
		
	}

	
}

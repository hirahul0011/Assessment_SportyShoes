package com.ecommerce.dao;

import java.util.Calendar;
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

import com.ecommerce.entity.Admin;
import com.ecommerce.entity.User;

import jakarta.persistence.EntityManager;

import jakarta.persistence.Query; 

@Repository
@Component
@Transactional
public class UserDAO {

//	@Autowired
//    private SessionFactory sessionFactory;
	
	@Autowired
	EntityManager em;
	
//	@SuppressWarnings("deprecation")
//	public void insertValuesUser() {
//		this.sessionFactory.openSession().createNativeQuery(
//				"INSERT INTO USERS VALUES(20,'2021-03-25',40001,'40001_Address','40001_EmailId','40001_FirstName','40001_LastName','40001_Password');"
//				+ "INSERT INTO USERS VALUES(30,'2021-03-25',40002,'40002_Address','40002_EmailId','40002_FirstName','40002_LastName','40002_Password');"
//				+ "INSERT INTO USERS VALUES(40,'2021-03-25',40003,'40003_Address','40003_EmailId','40003_FirstName','40003_LastName','40003_Password');"
//				+ "INSERT INTO USERS VALUES(50,'2021-03-25',40004,'40004_Address','40004_EmailId','40004_FirstName','40004_LastName','40004_Password');"
//				+ "INSERT INTO USERS VALUES(60,'2021-03-25',40005,'40005_Address','40005_EmailId','40005_FirstName','40005_LastName','40005_Password');")
//		.executeUpdate();
//	}
	
	public void insertValuesUserEM() {
		em.createNativeQuery(
				"INSERT INTO USERS VALUES(20,'2021-03-25',40001,'40001_Address','40001_EmailId','40001_FirstName','40001_LastName','40001_Password');"
				+ "INSERT INTO USERS VALUES(30,'2021-03-25',40002,'40002_Address','40002_EmailId','40002_FirstName','40002_LastName','40002_Password');"
				+ "INSERT INTO USERS VALUES(40,'2021-03-25',40003,'40003_Address','40003_EmailId','40003_FirstName','40003_LastName','40003_Password');"
				+ "INSERT INTO USERS VALUES(50,'2021-03-25',40004,'40004_Address','40004_EmailId','40004_FirstName','40004_LastName','40004_Password');"
				+ "INSERT INTO USERS VALUES(60,'2021-03-25',40005,'40005_Address','40005_EmailId','40005_FirstName','40005_LastName','40005_Password');")
		.executeUpdate();
	}

	
//	@SuppressWarnings("unchecked")
//	public User authenticate(String emailId, String pwd) {
////		List<User> list = this.sessionFactory.getCurrentSession().createQuery("from User where emailid=:emailid and pwd=:pwd")
//		List<User> list = this.sessionFactory.openSession().createQuery("from User where emailid=:emailid and pwd=:pwd")		
//				.setParameter("emailid", emailId)
//				.setParameter("pwd", pwd)
//				.list();
//		if (list.size() > 0)
//			return (User) list.get(0);
//		else
//			return null;
//		
//	}
	
	@SuppressWarnings("unchecked")
	public User authenticate(String emailId, String pwd) {
//		List<User> list = this.sessionFactory.getCurrentSession().createQuery("from User where emailid=:emailid and pwd=:pwd")
		List<User> list = em.createQuery("from User where emailId=:emailid and pwd=:pwd")		
				.setParameter("emailid", emailId)
				.setParameter("pwd", pwd)
				.getResultList();
		System.out.println(list);
		if (list.size() > 0)
			return (User) list.get(0);
		else
			return null;
		
	}
	
//	@SuppressWarnings("unchecked")
//	public User getUserById(long id) {
//		String strId = String.valueOf(id);
////		List<User> list = this.sessionFactory.getCurrentSession().createQuery("from User where id=" + strId).list();
//		List<User> list = this.sessionFactory.openSession().createQuery("from User where id=" + strId).list();
//		if (list.size() > 0)
//			return (User) list.get(0);
//		else
//			return null;
//		
//	}
	
	@SuppressWarnings("unchecked")
	public User getUserById(long id) {
		String strId = String.valueOf(id);
//		List<User> list = this.sessionFactory.getCurrentSession().createQuery("from User where id=" + strId).list();
		List<User> list = em.createQuery("from User where id=" + strId).getResultList();
		if (list.size() > 0)
			return (User) list.get(0);
		else
			return null;
		
	}	
	
//	@SuppressWarnings("unchecked")
//	public User getUserByEmailId(String emailId) {
////		List<User> list = this.sessionFactory.getCurrentSession().createQuery("from User where emailid='" + emailId + "'").list();
//		List<User> list = this.sessionFactory.openSession().createQuery("from User where emailid='" + emailId + "'").list();
//		if (list.size() > 0)
//			return (User) list.get(0);
//		else
//			return null;
//		
//	}
	
	@SuppressWarnings("unchecked")
	public User getUserByEmailId(String emailId) {
//		List<User> list = this.sessionFactory.getCurrentSession().createQuery("from User where emailid='" + emailId + "'").list();
		List<User> list = em.createQuery("from User where emailId='" + emailId + "'").getResultList();
		if (list.size() > 0)
			return (User) list.get(0);
		else
			return null;
		
	}
	
//	@SuppressWarnings("unchecked")
//	public void updateUser(User user) {
//		String sql = "";
//		if (user.getID() == 0) {
//			user.setDateAdded(Calendar.getInstance().getTime());
////			this.sessionFactory.getCurrentSession().save(user);
//			this.sessionFactory.openSession().save(user);
//		} else if (user.getID() > 0) {
//			sql = "update User set fname=:fname, lname=:lname, address=:address, age=:age, pwd=:pwd where " +
//					" ID=:id";
////			Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
//			Query query = this.sessionFactory.openSession().createQuery(sql);
//			query.setParameter("fname", user.getFname());
//			query.setParameter("lname", user.getLname());
//			query.setParameter("address", user.getAddress());
//			query.setParameter("age", user.getAge());
//			query.setParameter("pwd", user.getPwd());
//			if (user.getID() > 0)
//				query.setParameter("id", user.getID());
//			
//			query.executeUpdate();
//		}
//		
//	}
	
	@SuppressWarnings("unchecked")
	public void updateUser(User user) {
		String sql = "";
		if (user.getID() == 0) {
			user.setDateAdded(Calendar.getInstance().getTime());
//			this.sessionFactory.getCurrentSession().save(user);
			em.persist(user);
		} else if (user.getID() > 0) {
			sql = "update User set fname=:fname, lname=:lname, address=:address, age=:age, pwd=:pwd where " +
					" ID=:id";
//			Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
			Query query = em.createQuery(sql);
			query.setParameter("fname", user.getFname());
			query.setParameter("lname", user.getLname());
			query.setParameter("address", user.getAddress());
			query.setParameter("age", user.getAge());
			query.setParameter("pwd", user.getPwd());
			if (user.getID() > 0)
				query.setParameter("id", user.getID());
			
			query.executeUpdate();
		}
		
	}

//	@SuppressWarnings("unchecked")
//	public List<User> getAllUsers() {
////		List<User> list = this.sessionFactory.getCurrentSession().createQuery("from User order by date_added").list();
//		List<User> list = this.sessionFactory.openSession().createQuery("from User order by date_added").list();
//
//		return list;
//	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
//		List<User> list = this.sessionFactory.getCurrentSession().createQuery("from User order by date_added").list();
		List<User> list = em.createQuery("from User order by dateAdded").getResultList();

		return list;
	}
	
}

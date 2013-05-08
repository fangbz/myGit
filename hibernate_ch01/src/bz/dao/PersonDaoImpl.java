package bz.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bz.hibernate.bean.Person;
import bz.util.HibernateUtil;

public class PersonDaoImpl implements PersonDao {

	@Override
	public void savePerson(Person person) 
	{
			Session session = HibernateUtil.openSession();
			
			Transaction tx = session.beginTransaction();
			try {
				session.save(person);
				tx.commit();
			} catch (Exception e) {
				if(tx!=null){
					tx.rollback();
				}
				e.printStackTrace();
			}finally{
				HibernateUtil.closeSessoin(session);
			}
			
			
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Person> listAllPersons()
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		
		List<Person> list = null;
		
		try {
			Query query = session.createQuery("from Person");
			list = (List<Person>)query.list();
			tx.commit();
		} catch (Exception e) {
			if(tx!=null)
			{
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			HibernateUtil.closeSessoin(session);
		}
		
		return list;
	}

	@Override
	public void deletePersonById(int id) {
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			 Person p = (Person)session.get(Person.class, id);
			 session.delete(p);
			tx.commit();
		} catch (Exception e) {
			if(tx!=null){
				tx.rollback();
			}
		}finally{
			HibernateUtil.closeSessoin(session);
		}
	}

	@Override
	public Person getPersonById(int id) {
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		 Person p = null;
		 try {
			 p = (Person)session.get(Person.class, id);
			 tx.commit();
		} catch (Exception e) {
			if(tx!=null){
				tx.rollback();
			}
		}finally{
			HibernateUtil.closeSessoin(session);
		}
		return p;
	}

	@Override
	public void updatePerson(Person person) {
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.update(person);
			tx.commit();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
		}finally{
			HibernateUtil.closeSessoin(session);
		}
	}

}

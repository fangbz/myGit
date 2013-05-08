package bz.hibernate.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import bz.hibernate.bean.People;

public class HibernateTest {

	private static SessionFactory sessionFactory;
	
	static{
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	
	public static void main(String[] args) throws Exception {
		//delAllPeople();
		savePeople();
	}
	
	public static void delAllPeople(){
		Session session = sessionFactory.openSession();
		Transaction tx =null;
		try {
			tx = session.beginTransaction();
			
			Query query = session.createQuery("from People");
			//Iterator it = query.list().iterator();
			
			Iterator<People> it2 = query.iterate();
			
			while(it2.hasNext()){
				//session.delete(it2.next());
				System.out.println(it2.next().getId());
				System.out.println(it2.next().getName());
			}
			
			tx.commit();
			
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public static void load(){
		
		Session session = sessionFactory.openSession();
		Transaction tx =null;
		try {
			tx = session.beginTransaction();
			
			People p =(People) session.load(People.class, 1l);
			System.out.println(p.getName());
			
			p.setName("zhansan");
			
			tx.commit();
			
			}catch(Exception e){
				if(tx!=null){
					tx.rollback();
				}
				e.printStackTrace();
			}finally{
				session.close();
			}
	}
	
	public static void queryPerson(){
		Session session = sessionFactory.openSession();
		Transaction tx =null;
		try {
			tx = session.beginTransaction();
			
			Query query =session.createQuery("from People order by name");
			query.setFirstResult(2).setMaxResults(1);
			List list = query.list();
			
			for(Iterator<People> iter = list.iterator();iter.hasNext();){
				 People pp = iter.next();
				 System.out.println(pp.getName());
				 System.out.println(pp.getPassword());
				 System.out.println(pp.getTelphone());
				 System.out.println(pp.getGender());
				 System.out.println(pp.getBirthday());
				 System.out.println(pp.getMarryTime());
				 System.out.println(pp.isGraduation());
				 
				 byte[] buffer = pp.getFile();
				 OutputStream os = new FileOutputStream("c:\\"+pp.getId()+".txt");
				 os.write(buffer);
				 os.close();
				 System.out.println("--------------------------------------------------");
				 
			}
			
		} catch (Exception e) {
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		
	}
	
	public static void savePeople() throws Exception{
		People p = new People();
		p.setName("Ð¡·½");
		p.setPassword("1234");
		p.setGender('M');
		p.setBirthday(new java.sql.Date(new java.util.Date().getTime()));
		p.setGraduation(false);
		p.setTelphone(110);
		p.setMarryTime(new Timestamp(System.currentTimeMillis()));
		
		InputStream is = new FileInputStream("C:\\T.txt");
		int length = is.available();
		byte[] buffer = new byte[length];
		is.read(buffer);
		
		p.setFile(buffer);
		
		Session session = sessionFactory.openSession();
		Transaction tx =null;
		
		try{
			tx = session.beginTransaction();
			Long id = (Long)session.save(p);
			System.out.println(id);
			tx.commit();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
}

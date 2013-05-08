package bz.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
		private static SessionFactory  sessionFactory;
		
		static
		{
				Configuration configuration = new Configuration().configure();
				sessionFactory = configuration.buildSessionFactory();
		}
		
		public static Session openSession(){
			
			Session session = sessionFactory.openSession();
			
			return session;
		}
		
		public static void closeSessoin(Session session){
				if(session!=null){
						session.close();
				}
		}
}

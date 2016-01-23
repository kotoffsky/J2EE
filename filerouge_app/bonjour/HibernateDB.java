package bonjour;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.InvalidMappingException;
import org.hibernate.HibernateException;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.logging.Logger;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;

public class HibernateDB {
	protected SessionFactory sessionFactory;
	
	// Handling Hibernate sessions ===================================================

    @SuppressWarnings("deprecation")
	protected void initialize () throws InvalidMappingException {
        StandardServiceRegistry registry = null;
		try {
//	            registry = new StandardServiceRegistryBuilder().configure().build();
//	            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			sessionFactory=new Configuration().configure().buildSessionFactory();
	            
		}
		catch (Throwable e) {
	        System.out.println("Erreur lors de l'initialisation de la session factory: "+e);
	        StandardServiceRegistryBuilder.destroy(registry);
	        try {
				throw e;
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
    }

    protected void close () throws HibernateException {
        if (this.sessionFactory!=null) {
            this.sessionFactory.close();
        }
    }

}

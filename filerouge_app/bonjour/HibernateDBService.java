package bonjour;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import service.Service;
import service.type;

public class HibernateDBService extends HibernateDB{
	public List<Service> getAllServices () {
		Transaction transaction=null;
		Session session = null;
	    try {
	    	session=sessionFactory.openSession();
		    transaction=session.beginTransaction();
		    Query query=session.createQuery("from Service");
		    List<Service> allServices=(List<Service>)query.list();
		    transaction.commit();
		    return allServices;
		} catch (Throwable e) {
		    if (transaction!=null) {
		        transaction.rollback();
		    }
		    if (session != null) {
				session.close();
			}
		    throw e;
		} 
	}
	
    public void ajoutService(Service service) {
    	Transaction transaction=null;
		Session session = null;
	    try {
	    	session=sessionFactory.openSession();
		    transaction=session.beginTransaction();
		    session.save(service);
		    transaction.commit();
		} catch (Throwable e) {
		    if (transaction!=null) {
		        transaction.rollback();
		    }
		    if (session != null) {
				session.close();
			}
		    throw e;
		} 
    }
	
}

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
import service.ServiceTypeEnum;

public class HibernateDBUserService extends HibernateDB{
	public HibernateDBUserService() {
		this.initialize();
	}
	public List<Service> getServicesDemande(String personID) {
		Transaction transaction=null;
		Session session = null;
		System.out.println("getServicesDemande");
	    try {
	    	session=sessionFactory.openSession();
		    transaction=session.beginTransaction();
		    String hql = "select s FROM Service s, AssServicePerson P WHERE P.personID = '"+personID+"' and s.id = P.service and P.typeService='demande'";
			Query query = session.createQuery(hql);
		    List<Service> allServices=(List<Service>)query.list();
		    System.out.println("result of allservices "+allServices);
		    transaction.commit();
		    return allServices;
		} catch (Throwable e) {
		    if (transaction!=null) {
		        transaction.rollback();
		    }
		    if (session != null) {
				session.close();
			}
		    try {
				throw e;
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
	    return null;
	}
	
	public void attacherPersonAService(AssServicePerson association) {
    	Transaction transaction=null;
		Session session = null;
	    try {
	    	session=sessionFactory.openSession();
		    transaction=session.beginTransaction();
		    session.save(association);
		    transaction.commit();
		} catch (Throwable e) {
		    if (transaction!=null) {
		        transaction.rollback();
		    }
		    if (session != null) {
				session.close();
			}
		    try {
				throw e;
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
    }
	
	public void retiterPersonDeService(AssServicePerson association) {
    	Transaction transaction=null;
		Session session = null;
	    try {
	    	session=sessionFactory.openSession();
		    transaction=session.beginTransaction();
//		    session.delete(association);
		    String hql = "delete FROM AssServicePerson P WHERE P.personID = '"+association.getPersonID()+"' and P.typeService='"+association.getTypeService()+"' and P.service='"+association.getService().getId()+"'";
			Query query = session.createQuery(hql);
			int result = query.executeUpdate();
			 
			if (result > 0) {
			    System.out.println("Expensive products was removed");
			}
		    transaction.commit();
		} catch (Throwable e) {
		    if (transaction!=null) {
		        transaction.rollback();
		    }
		    if (session != null) {
				session.close();
			}
		    try {
				throw e;
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
    }
}

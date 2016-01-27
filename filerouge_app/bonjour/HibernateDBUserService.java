package bonjour;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
	
	/**
	 * Select all services attached to user that he asks
	 * @param personID
	 * @return List of objects -> [0] - Service, [1] - AssServicePerson
	 */
	public List<Object[]> getServicesDemande(String personID) {
		Transaction transaction=null;
		Session session = null;
		System.out.println("getServicesDemande");
	    try {
	    	
	    	session=sessionFactory.openSession();
		    transaction=session.beginTransaction();
		    
		    String hql1 = "FROM Service s, AssServicePerson P WHERE P.personID = '"+personID+"' and s.id = P.service and P.typeService='demande' order by P.dateDeLimite ASC";
			Query query1 = session.createQuery(hql1);
			List<Object[]> rows = query1.list();
		    for (Object[] row : rows) {
		    	//compare and delete associations that passed limit date
		    	Long currentUnixTime = System.currentTimeMillis() / 1000L;
		    	System.out.println("current unix time "+currentUnixTime);
		    	if (((AssServicePerson)row[1]).getDateDeLimite() < currentUnixTime) {
		    		this.retiterPersonDeService((AssServicePerson)row[1]); //remove from DB
		    		rows.remove(rows.indexOf(row));//remove from output list
		    		System.out.println("remove this association");
		    	}
		    	
		    	System.out.println("demande join result "+ ((Service)row[0]).getTitre()+" "+((AssServicePerson)row[1]).getDateDeLimite());
		    	Date date = new Date();
		    	date.setTime(((AssServicePerson)row[1]).getDateDeLimite() * 1000L);
		    	DateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:MM:ss.SSS");
		    	  format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
		    	  String dateDeLimite = format.format(date);
		    	System.out.println("date de limit est "+dateDeLimite);
			}
		    
		    return rows;
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
	
	/**
	 * Select all services attached to user that he gives
	 * @param personID
	 * @return List of objects -> [0] - Service, [1] - AssServicePerson
	 */
	public List<Object[]> getServicesOffre(String personID) {
		Transaction transaction=null;
		Session session = null;
		System.out.println("getServicesDemande");
	    try {
	    	
	    	session=sessionFactory.openSession();
		    transaction=session.beginTransaction();
		    
		    String hql1 = "FROM Service s, AssServicePerson P WHERE P.personID = '"+personID+"' and s.id = P.service and P.typeService='offre' order by P.dateDeLimite ASC";
			Query query1 = session.createQuery(hql1);
			List<Object[]> rows = query1.list();
		    for (Object[] row : rows) {
		    	//compare and delete associations that passed limit date
		    	Long currentUnixTime = System.currentTimeMillis() / 1000L;
		    	System.out.println("current unix time "+currentUnixTime);
		    	if (((AssServicePerson)row[1]).getDateDeLimite() < currentUnixTime) {
		    		this.retiterPersonDeService((AssServicePerson)row[1]); //remove from DB
		    		rows.remove(rows.indexOf(row));//remove from output list
		    		System.out.println("remove this association");
		    	}
		    	
		    	System.out.println("offre join result "+ ((Service)row[0]).getTitre()+" "+((AssServicePerson)row[1]).getDateDeLimite());
		    	Date date = new Date();
		    	date.setTime(((AssServicePerson)row[1]).getDateDeLimite() * 1000L);
		    	DateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:MM:ss.SSS");
		    	  format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
		    	  String dateDeLimite = format.format(date);
		    	System.out.println("date de limit est "+dateDeLimite);
			}
		    
		    return rows;
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

package bonjour;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import service.Service;
import service.type;

public class ServiceAjoutServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HibernateDBService hibernateDBServiceInstance = new HibernateDBService();
		
		try {
			hibernateDBServiceInstance.initialize();
			Service service = new Service("titre", "description", "categorie", type.DEMANDE, new Date());
			hibernateDBServiceInstance.ajoutService(service);
		} catch (Throwable e) {
			System.out.println("Error catch initialize");
			response.sendRedirect("vues/errorPage.jsp");
			return;
		}
	}

}

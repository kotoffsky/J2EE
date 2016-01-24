package bonjour;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import service.Service;
import service.ServiceTypeEnum;

public class ServiceAjoutServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HibernateDBService hibernateDBServiceInstance = new HibernateDBService();
		
		String serviceName = request.getParameter("serviceName");
		String serviceDescription = request.getParameter("serviceDescription");
		String message = "";
		
		if(serviceName == "" || serviceDescription == "") {
			message = "Indiquez nom et description de service";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ajouterService");
	        requestDispatcher.forward(request, response);
		}
		
		try {
			hibernateDBServiceInstance.initialize();
			Service service = new Service(serviceName, serviceDescription, "categorie", new Date());
			hibernateDBServiceInstance.ajoutService(service);
		} catch (Throwable e) {
			System.out.println("Error catch initialize");
			response.sendRedirect("vues/errorPage.jsp");
			return;
		}
		hibernateDBServiceInstance.close();
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/services");
        requestDispatcher.forward(request, response);
	}

}

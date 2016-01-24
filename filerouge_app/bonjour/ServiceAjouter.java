package bonjour;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;

import persons.Person;
import persons.SQLPersonsDB;
import service.Service;

public class ServiceAjouter extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String serviceName = "";
    	String serviceDescription = "";
        try {
        	serviceName=request.getParameter("serviceName");
        	serviceDescription=request.getParameter("serviceDescription");
        	String message = "";
            if(serviceName.length() == 0 || serviceDescription.length() == 0) {
    			RequestDispatcher requestDispatcher = request.getRequestDispatcher("vues/serviceAjouterFormulaire.jsp");
    			request.setAttribute("title", "Ajouter nouveau service");
    			message = "Indiquez nom et description de service ;" +serviceName;
    			request.setAttribute("message", "<p class=\"error_msg\">"+message+"</p>");
    	        requestDispatcher.forward(request, response);
    	        return;
    		}
            

        	HibernateDBService hibernateDBServiceInstance = new HibernateDBService();
			Service service = new Service(serviceName, serviceDescription, "categorie", new Date());
			hibernateDBServiceInstance.ajoutService(service);
			hibernateDBServiceInstance.close();
			System.out.println("Ajout d'un service");
    		
			String contextPath = request.getContextPath();
	        response.sendRedirect(contextPath+"/services"); 
        } catch (Exception e) {
        	e.printStackTrace();
            return;
        }

    }

}

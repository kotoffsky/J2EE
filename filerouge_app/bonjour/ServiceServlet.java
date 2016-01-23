package bonjour;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persons.Person;
import persons.SQLPersonsDB;
import service.Service;
import service.ServiceTypeEnum;

public class ServiceServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String connected = "";
		HttpSession s = request.getSession(true);
		if(s!=null){
			Object resp =  s.getAttribute("connected");
			if(resp!=null){
				connected = (String) resp;
				
			}
		}
		
		if(!connected.equals("")){
			HibernateDBService hibernateDBServiceInstance = new HibernateDBService();
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("vues/servicesList.jsp");
			request.setAttribute("title", "Liste de services");
			request.setAttribute("services", hibernateDBServiceInstance.getAllServices());
			hibernateDBServiceInstance.close();
	        requestDispatcher.forward(request, response);
		} else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			request.setAttribute("message", "Idendifiez-vous");
			requestDispatcher.forward(request, response);
		}
	}
}

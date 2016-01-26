package bonjour;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.type.TimestampType;

import service.Service;

public class ServiceAjouterAssociation extends HttpServlet {

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
			HibernateDBUserService hibernateDBUserServiceInstance = new HibernateDBUserService();
			HibernateDBService hibernateDBServiceInstance = new HibernateDBService();
	
			//decouper la ligne en params
			String[] pathParts = request.getPathInfo().split("/");
			Service service = hibernateDBServiceInstance.getService(Long.parseLong(pathParts[2]));
			
			AssServicePerson association = new AssServicePerson();
			association.setPersonID(connected);
			association.setTypeService(pathParts[1]);
			association.setService(service);
			association.setDateDeLimite(Long.parseLong(pathParts[3]));
			//enregister l'association
			hibernateDBUserServiceInstance.attacherPersonAService(association);
			
			//mise à jour de la session, liste de services de l'utilisateur courant
			AuthentificationServlet.miseAJourUserSession(request, connected);
			
			//redirection vers la page de services
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/services");
	        requestDispatcher.forward(request, response);
		}
	}
}

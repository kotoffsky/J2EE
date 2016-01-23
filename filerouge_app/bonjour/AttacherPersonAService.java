package bonjour;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.Service;

public class AttacherPersonAService extends HttpServlet {

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
	
			AssServicePerson association = new AssServicePerson();
			association.setPersonID(connected);
		//	Service service = new Service("titre", "description", "categorie", type.DEMANDE, new Date());
		//	hibernateDBServiceInstance.ajoutService(service);
			
			HibernateDBService hibernateDBServiceInstance = new HibernateDBService();
			hibernateDBServiceInstance.initialize();
			
			//decouper la ligne en params
			String[] pathParts = request.getPathInfo().split("/");
			Service service = hibernateDBServiceInstance.getService(Long.parseLong(pathParts[2]));
			association.setService(service);
			association.setTypeService(pathParts[1]);
			//enregister l'association
			hibernateDBUserServiceInstance.attacherPersonAService(association);
		}
	}
}

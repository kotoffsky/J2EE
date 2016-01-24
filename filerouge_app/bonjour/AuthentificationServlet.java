package bonjour;

import java.io.IOException;
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

/**
 * A Servlet which adds a product to the database and displays a confirmation or error message on
 * the "table of contents" page.
 * @author Bruno Zanuttini, Universit&eacute; de Caen Basse-Normandie, France.
 * @since January, 2014
 */
public class AuthentificationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String userEmail = "";
    	String userPassword = "";
        try {
        	userEmail=request.getParameter("email");
            userPassword=request.getParameter("password");
        } catch (Exception e) {
            this.terminate(request,response,"Impossible vérifier sans données ("+e+"), merci de resaisir les données.");
            return;
        }
        
        // Get DB connection
        SQLPersonsDB db = null;
		try {
			db = new PersonsDBHandler().getDb();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Check person in DB
		String val = "default";
		Boolean check = false;
		try {
//			Collection<String> emailList = db.retrieveAllEmails();
//		        request.setAttribute("userName", emailList);
			check = db.isValid(userEmail, userPassword);
			if (check) {
		        request.setAttribute("title", "Resultat d'authentication");
		        request.setAttribute("output", "Bonjour "+userEmail);
		        // Recupere la session
		        Person p = db.retrieve(userEmail);
		        request.getSession(true).setAttribute("connected", userEmail);
		        request.getSession(true).setAttribute("name", p.getName());
		        request.getSession(true).setAttribute("firstName", p.getFirstName());
		        
		        HibernateDBService hibernateDBServiceInstance = new HibernateDBService();
				HibernateDBUserService hibernateDBUserServiceInstance = new HibernateDBUserService();
				List<Service> userServicesDemande = hibernateDBUserServiceInstance.getServicesDemande(p.getEmail());
				request.getSession(true).setAttribute("userServicesDemande", userServicesDemande);
				List<Service> userServicesOffre = hibernateDBUserServiceInstance.getServicesOffre(p.getEmail());
				request.getSession(true).setAttribute("userServicesOffre", userServicesOffre);
		        
			} else {
		        request.setAttribute("title", "Resultat d'authentication failed");
		        request.setAttribute("output", "Email "+userEmail+" et/ou mot de passe sont incorrectes");
			}
		} catch (Exception e1) {
			request.setAttribute("output", e1);
		}
		
        
		this.terminate(request,response,"Nous avons bien pris en compte le nouveau produit, merci.");
//		requestDispatcher.forward(request, response);

        // Insert product into DB
//        Product product=new Product(name,pricePerKg,weight);
//        try {
//            new ProductsDBHandler().getDb().create(product);
//        } catch (Exception e) {
//            this.terminate(request,response,"Erreur d'insertion dans la base ("+e+").");
//            return;
//        }

        // Everything went well
//        request.setAttribute("test", val);
//        
//        this.terminate(request,response,"Nous avons bien pris en compte le nouveau produit, merci.");

    }

    /**
     * Terminates the response of this servlet by displaying table of contents and a message.
     * @param request The request for this call
     * @param response The response for this call
     * @param message The message to be forwarded to table of contents
     */
    protected void terminate (HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
    	RequestDispatcher requestDispatcher = request.getRequestDispatcher("vues/identificationResult.jsp");
    	requestDispatcher.forward(request, response);
//        response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/vues/identificationResult.jsp?message="+message));
    }

//    @Override
//    public void destroy () {
//        try {
//            ProductsDBHandler.close();
//        } catch (SQLException e) {
//            this.log("Erreur lors de la cl&ocirc;ture de la connexion SQL ("+e+").");
//       }
//    }

}

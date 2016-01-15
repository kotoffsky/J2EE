package bonjour;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import persons.*;

/**
 * A Servlet which reads a list of strings and prints it sorted.
 * @author Bruno Zanuttini, Universit&eacute; de Caen Basse-Normandie, France.
 * @since January, 2012
 */
public class BonjourSignup extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    	
        this.log("Une exécution de la servlet...");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out=null;
        try {
            out=resp.getWriter();
        } catch (IOException e) {
            out.close();
            throw e;
        }
        
        // Get DB connection
        SQLPersonsDB db = null;
		try {
			db = new PersonsDBHandler().getDb();
		} catch (Exception e) {
			e.printStackTrace();
		}

        String name=req.getParameter("name");
        String firstName=req.getParameter("firstName");
        String email=req.getParameter("email");
        String password=req.getParameter("password");

        Person p = new Person(firstName, name, email);
        String userName = "Inconnue";
        Person user=null;
        try {
			db.create(p, password);
	        if(db.exists(email)){
	            user = db.retrieve(email);
	            userName = user.getName();
	        }
	        
	        RequestDispatcher requestDispatcher = req.getRequestDispatcher("vues/identificationResult.jsp");
	        req.setAttribute("title", "Resultat d'inscription");
	        req.setAttribute("userName", db.exists(email));
	        requestDispatcher.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}

        
        

        
    }

}

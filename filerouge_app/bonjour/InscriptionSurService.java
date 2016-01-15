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
 * Insciption servlet
 * @author Mykyta Kharaim, Amani Lahbib
 * @since January, 2016
 */
public class InscriptionSurService extends HttpServlet {
    public static PersonDBStub stub = new PersonDBStub();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("vues/identificationResult.jsp");
        req.setAttribute("title", "Resultat de l'autentification");
//        req.setAttribute("userName", userName);
        requestDispatcher.forward(req, resp);

    }

}

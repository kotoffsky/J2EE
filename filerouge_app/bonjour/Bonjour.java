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
 * @author Lahbib Amani, Kharaim Mykyta
 * @since January, 2015
 */
public class Bonjour extends HttpServlet {
    public static PersonDBStub stub = new PersonDBStub();

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

        // Person p1 = new Person("Nick", "Kharaim", "nick.kharaim@gmail.com");
        // Person p2 = new Person("Emiliano", "Castillo", "Emiliano.Castillo@gmail.com");
        // stub.create(p1, "123456");
        // stub.create(p2, "123456");
        out.println(stub.retrieveAll());

        String email=req.getParameter("email");


        String userName = "Inconnue";
        if(stub.exists(email)){
            Person user = stub.retrieve(email);
            userName = user.getName();
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("vues/identificationResult.jsp");
        req.setAttribute("title", "Resultat de l'autentification");
        req.setAttribute("userName", userName);
        requestDispatcher.forward(req, resp);

    }

}

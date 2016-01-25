package bonjour;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import persons.*;
import service.Service;

/**
 * A Servlet of user deconnection
 * @author Nick Kharaim, Amani Lahbib, Universit&eacute; de Caen Basse-Normandie, France.
 * @since January, 2016
 */
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        
    	try {
    		req.getSession().invalidate();
        	String contextPath = req.getContextPath();
        	resp.sendRedirect(contextPath+"/home"); 
        } catch (Exception e) {
        	e.printStackTrace();
            return;
        }
    }

}

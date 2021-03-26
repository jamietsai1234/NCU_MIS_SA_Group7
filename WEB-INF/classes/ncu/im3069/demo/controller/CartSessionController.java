package ncu.im3069.demo.controller;

import java.io.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;
import ncu.im3069.demo.app.MovieSessionHelper;
import ncu.im3069.demo.app.SessionHelper;
import ncu.im3069.tools.JsonReader;

@WebServlet("/api/cartsession.do")

public class CartSessionController extends HttpServlet {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    private SessionHelper ssh =  SessionHelper.getHelper();
    private MovieSessionHelper msh =  MovieSessionHelper.getHelper();

public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
 
        JsonReader jsr = new JsonReader(request);

        String id = jsr.getParameter("id");
        
        if (id.isEmpty()) {
            JSONObject query = ssh.getAll();
          
            JSONObject resp = new JSONObject();
            resp.put("status", "200");
            resp.put("message", "取得全部場次");
            resp.put("response", query);
        
            jsr.response(resp, response);
        }
        else {
        	JSONObject query = msh.getByIdSession(id);
          
            JSONObject resp = new JSONObject();
            resp.put("status", "200");
            resp.put("message", "取得該場次");
            resp.put("response", query);
    
            jsr.response(resp, response);
        }
    }
}
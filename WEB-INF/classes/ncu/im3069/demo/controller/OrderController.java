package ncu.im3069.demo.controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;
import ncu.im3069.demo.app.Order;
import ncu.im3069.demo.app.OrderHelper;
import ncu.im3069.tools.JsonReader;

import javax.servlet.annotation.WebServlet;

@WebServlet("/api/order.do")
public class OrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private OrderHelper oh =  OrderHelper.getHelper();

    public OrderController() {
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        JsonReader jsr = new JsonReader(request);

        String id = jsr.getParameter("id");
        
        JSONObject resp = new JSONObject();

        if (!id.isEmpty()) {
          JSONObject query = oh.getById(id);
          resp.put("status", "200");
          resp.put("message", "單筆訂單資料取得成功");
          resp.put("response", query);
        }
        else {
          JSONObject query = oh.getAll();
          resp.put("status", "200");
          resp.put("message", "所有訂單資料取得成功");
          resp.put("response", query);
        }
        jsr.response(resp, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();

        String member_name = jso.getString("member_name");
        String member_email = jso.getString("member_email");
        String picker_name = jso.getString("picker_name");
        String picker_phone = jso.getString("picker_phone");
        String payment_method = jso.getString("payment_method");
        boolean state = jso.getBoolean("state"); 
   
        Order od = new Order(member_name, member_email, picker_name,picker_phone, payment_method, state);

        JSONObject data = oh.create(od);
       
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "訂單新增成功！");
        resp.put("response", data);
       
        jsr.response(resp, response);
	}
	
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            JsonReader jsr = new JsonReader(request);
            JSONObject jso = jsr.getObject();
            
            int id = jso.getInt("id");
            
            JSONObject query = oh.deleteByID(id);
            
            JSONObject resp = new JSONObject();
            resp.put("status", "200");
            resp.put("message", "訂單刪除成功！");
            resp.put("response", query);

            jsr.response(resp, response);
        }
}
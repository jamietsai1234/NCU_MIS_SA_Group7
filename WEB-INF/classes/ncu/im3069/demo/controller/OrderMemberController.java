package ncu.im3069.demo.controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;
import ncu.im3069.demo.app.OrderHelper;
import ncu.im3069.tools.JsonReader;

import javax.servlet.annotation.WebServlet;

@WebServlet("/api/ordermember.do")
public class OrderMemberController extends HttpServlet {

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /** oh，OrderHelper 之物件與 order 相關之資料庫方法（Sigleton） */
	private OrderHelper oh =  OrderHelper.getHelper();

    public OrderMemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /** 透過 JsonReader 類別將 Request 之 JSON 格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);

        /** 取出經解析到 JsonReader 之 Request 參數 */
        String email = jsr.getParameter("email");

        /** 新建一個 JSONObject 用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();

        JSONObject query = oh.getMemberAll(email);
        resp.put("status", "200");
        resp.put("message", "訂單資料取得成功");
        resp.put("response", query);      

        /** 透過 JsonReader 物件回傳到前端（以 JSONObject 方式） */
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
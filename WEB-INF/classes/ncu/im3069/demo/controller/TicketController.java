package ncu.im3069.demo.controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;
import ncu.im3069.demo.app.Ticket;
import ncu.im3069.demo.app.TicketHelper;
import ncu.im3069.tools.JsonReader;
import javax.servlet.annotation.WebServlet;

@WebServlet("/api/ticket.do")
public class TicketController extends HttpServlet {

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** oh，TicketHelper 之物件與 ticket 相關之資料庫方法（Sigleton） */
	private TicketHelper th =  TicketHelper.getHelper();

    public TicketController() {
        super();
    }

    /**
     * 處理 Http Method 請求 GET 方法（新增資料）
     *
     * @param request Servlet 請求之 HttpServletRequest 之 Request 物件（前端到後端）
     * @param response Servlet 回傳之 HttpServletResponse 之 Response 物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /** 透過 JsonReader 類別將 Request 之 JSON 格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);

        /** 取出經解析到 JsonReader 之 Request 參數 */
        String id = jsr.getParameter("id");

        /** 新建一個 JSONObject 用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();

        /** 判斷該字串是否存在，若存在代表要取回個別票券之資料，否則代表要取回全部資料庫內票券之資料 */
        if (!id.isEmpty()) {
          /** 透過 ticketHelper 物件的 getBySessionId() 方法自資料庫取回該筆票券之資料，回傳之資料為 JSONObject 物件 */
          JSONObject query = th.getBySessionId(id);
          resp.put("status", "200");
          resp.put("message", "單筆票券資料取得成功");
          resp.put("response", query);
        }
        else {
          /** 透過 ticketHelper 物件之 getAll() 方法取回所有票券之資料，回傳之資料為 JSONObject 物件 */
          JSONObject query = th.getAll();
          resp.put("status", "200");
          resp.put("message", "所有票券資料取得成功");
          resp.put("response", query);
        }
        /** 透過 JsonReader 物件回傳到前端（以 JSONObject 方式） */
        jsr.response(resp, response);
	}

    /**
     * 處理 Http Method 請求 POST 方法（新增資料）
     *
     * @param request Servlet 請求之 HttpServletRequest 之 Request 物件（前端到後端）
     * @param response Servlet 回傳之 HttpServletResponse 之 Response 物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    /** 透過 JsonReader 類別將 Request 之 JSON 格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();

        /** 取出經解析到 JSONObject 之 Request 參數 */
        String ticket_type = jso.getString("ticket_type");
        int ticket_price = jso.getInt("ticket_price");
        int quantity = jso.getInt("quantity");
        int order_id = jso.getInt("order_id");
        String seat_id = jso.getString("seat_id");
        int session_id = jso.getInt("session_id");
        
        /** 建立一個新的票券物件 */
        Ticket td = new Ticket(ticket_type, ticket_price, quantity, order_id, seat_id, session_id);
        
        /** 透過 ticketHelper 物件的 create() 方法新建一筆票券至資料庫 */
        JSONObject data = th.create(td);

        /** 新建一個 JSONObject 用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "票券新增成功！");
        resp.put("response", data);

        /** 透過 JsonReader 物件回傳到前端（以 JSONObject 方式） */
        jsr.response(resp, response);
	}
}
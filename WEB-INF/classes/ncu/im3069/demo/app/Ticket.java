package ncu.im3069.demo.app;

import org.json.JSONObject;

public class Ticket {

    private int ticket_id;

    private int order_id;
    
    private int session_id;   
    
    private String seat_id;
    
    private String ticket_type;
    
    private int ticket_price;

    private int quantity;

    public Ticket(String ticket_type,int ticket_price,int quantity, int order_id,String seat_id,int session_id ) {
    	  
    	  this.ticket_type = ticket_type;
    	  this.ticket_price = ticket_price;
    	  this.quantity = quantity;
    	  this.order_id = order_id;
    	  this.seat_id = seat_id;
    	  this.session_id = session_id;
    	  
    }
    
	public Ticket(int order_id, int ticket_id, int ticket_price, int session_id, String seat_id, String ticket_type, int quantity) {
		
		this.order_id = order_id;
		this.ticket_id = ticket_id;
        this.ticket_price = ticket_price;
        this.session_id = session_id;
        this.seat_id = seat_id;
        this.ticket_type = ticket_type;
        this.quantity = quantity;

    }
  
	public Ticket(int ticket_id, String ticket_type,int ticket_price,int quantity, int order_id,String seat_id,int session_id ) {
		this.order_id = order_id;
		this.ticket_id = ticket_id;
        this.ticket_price = ticket_price;
        this.session_id = session_id;
        this.seat_id = seat_id;
        this.ticket_type = ticket_type;
        this.quantity = quantity;

    }
    
	public Ticket( Ticket tk, int quantity) {
		this.quantity = quantity;	
	}

    public void setId(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getId() {
        return this.ticket_id;
    }

	public int getTicketPrice() {
		return this.ticket_price;
	}
	
    public String getTicketType() {
        return this.ticket_type;
    }
    
	public int getSessionId() {
		return this.session_id;
	}
	
    public String getSeatId() {
        return this.seat_id;
    }
    
    public int getQuantity() {
        return this.quantity;
    }
   
    public int getOrderId() {
    	return this.order_id;
    }

    /**
     * 取得票券細項資料
     *
     * @return JSONObject 回傳票券細項資料
     */
    public JSONObject getData() {
        JSONObject data = new JSONObject();
        data.put("ticket_id", getId());
        data.put("order_id", getOrderId());
        data.put("session_id", getSessionId());
        data.put("ticket_price", getTicketPrice());
        data.put("ticket_type", getTicketType());
        data.put("seat_id", getSeatId());
        data.put("quantity", getQuantity());

        return data;
    }

    public JSONObject getTickerAllInfo() {
        JSONObject jso = new JSONObject();
        jso.put("ticket1_info", getData());
       
        return jso;
    }
    
}

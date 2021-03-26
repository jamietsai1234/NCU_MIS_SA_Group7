package ncu.im3069.demo.app;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

import org.json.*;

public class Order {

    private int order_id;
   
    private String member_name;
    
    private String member_email;
    
    private String picker_name;

    private String picker_phone;
    
    private boolean state;
       
    private String payment_method;
    
    private ArrayList<Ticket> list = new ArrayList<Ticket>();

    private Timestamp order_create;
    
    private Timestamp order_modify;

    private TicketHelper th = TicketHelper.getHelper();

    public Order(String member_name, String member_email, String picker_name, String picker_phone, String payment_method, boolean state) {
        this.member_name = member_name;
        this.member_email = member_email;
        this.picker_name = picker_name;
        this.picker_phone = picker_phone;
        this.payment_method = payment_method;   
        this.state = state; 
        this.order_create = Timestamp.valueOf(LocalDateTime.now());
        this.order_modify = Timestamp.valueOf(LocalDateTime.now());
    }

    public Order(int order_id, String member_name, String member_email, String picker_name, String picker_phone, String payment_method, boolean state, Timestamp order_create, Timestamp order_modify) {
        this.order_id = order_id;
        this.member_name = member_name;
        this.member_email = member_email;
        this.picker_name = picker_name;
        this.picker_phone = picker_phone;
        this.payment_method = payment_method;
        this.state = state;
        this.order_create = order_create;
        this.order_modify = order_modify;
        getTicketFromDB();
    }

    public void addTicket( Ticket tk, int quantity) {
        this.list.add(new Ticket(tk, quantity));
    }
	
    public void setId(int order_id) {
        this.order_id = order_id;
    }

    public int getId() {
        return this.order_id;
    }

    public String getMemberName() {
        return this.member_name;
    }

    public String getPickerName() {
        return this.picker_name;
    }
    
    public String getPickerPhone() {
        return this.picker_phone;
    }
    
    public String getPaymentMethod() {
        return this.payment_method;
    }
    
    public boolean getState() {
    	return this.state;
    }
    
    public String getStringState() {
    	getState();
    	if(state = true) {return "未付款";}
    	else {return "已付款";}
    }
    
    public String getMemberEmail() {
        return this.member_email;
    }

    public Timestamp getOrderCreateTime() {
        return this.order_create;
    }

    public Timestamp getOrderModifyTime() {
        return this.order_modify;
    }

    public ArrayList<Ticket> getTicket() {
        return this.list;
    }
     
    private void getTicketFromDB() {
        ArrayList<Ticket> data = th.getTicketByOrderId(this.order_id);
        this.list = data;
    }
   
    public JSONObject getOrderData() {
        JSONObject jso = new JSONObject();
        jso.put("order_id", getId());
        jso.put("member_name", getMemberName());
        jso.put("member_email", getMemberEmail());
        jso.put("order_create", getOrderCreateTime());
        jso.put("order_modify", getOrderModifyTime());
        jso.put("picker_name", getPickerName());
        jso.put("picker_phone", getPickerPhone());
        jso.put("state", getStringState());
        jso.put("payment_method", getPaymentMethod());
        return jso;
    }

    public JSONArray getTicketData() {
        JSONArray result = new JSONArray();
        for(int i=0 ; i < this.list.size() ; i++) {
            result.put(this.list.get(i).getData());
        }
        return result;
    }

    public JSONObject getOrderAllInfo() {
        JSONObject jso = new JSONObject();
        jso.put("order_info", getOrderData());
        jso.put("ticket_info", getTicketData());

        return jso;
    }

}

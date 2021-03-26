package ncu.im3069.demo.app;

import java.sql.*;
import java.util.*;
import org.json.*;
import ncu.im3069.demo.util.DBMgr;

public class TicketHelper {
    
    private TicketHelper() {}
    
    private static TicketHelper th;
    private Connection conn = null;
    private PreparedStatement pres = null;

    
    public static TicketHelper getHelper() {
        if(th == null) th = new TicketHelper();
        
        return th;
    }
    
    public JSONObject create(Ticket ticket) {
        String exexcute_sql = "";
        
        try {
            
            conn = DBMgr.getConnection();            
            String sql = "INSERT INTO `missa_group7`.`tickets`(`ticket_type`, `ticket_price`, `quantity`, `ticket_order_id`,  `ticket_seat_id`, `ticket_session_id`)"
                    + " VALUES(?, ?, ?, ?, ?, ?)";
            
            String ticket_type = ticket.getTicketType();
            int ticket_price = ticket.getTicketPrice();
            int quantity = ticket.getQuantity();
            int ticket_order_id = ticket.getOrderId();
            String ticket_seat_id = ticket.getSeatId();
            int ticket_session_id = ticket.getSessionId();
            
            pres = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pres.setString(1, ticket_type);
            pres.setInt(2, ticket_price);
            pres.setInt(3, quantity);
            pres.setInt(4, ticket_order_id);
            pres.setString(5, ticket_seat_id);
            pres.setInt(6, ticket_session_id);
           
            pres.executeUpdate();
            
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBMgr.close(pres, conn);
        }

        JSONObject response = new JSONObject();

        return response;
    }
    
    public JSONObject getAll() {
        
    	Ticket t = null;
        
        JSONArray jsa = new JSONArray();
        
        String exexcute_sql = "";
        
        long start_time = System.nanoTime();
        
        int row = 0;
        
        ResultSet rs = null;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "SELECT * FROM `missa_group7`.`tickets`";
            
            pres = conn.prepareStatement(sql);
            
            rs = pres.executeQuery();

            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            while(rs.next()) {
                
                row += 1;
                
                int ticket_id = rs.getInt("ticket_id");
                String ticket_type = rs.getString("ticket_type");
                int ticket_price = rs.getInt("ticket_price");
                int quantity = rs.getInt("quantity");
                int ticket_order_id = rs.getInt("ticket_order_id");
                String ticket_seat_id = rs.getString("ticket_seat_id");
                int ticket_session_id = rs.getInt("ticket_session_id");
                
                t = new Ticket(ticket_id,ticket_type,ticket_price, quantity,ticket_order_id,ticket_seat_id,ticket_session_id);
                
                jsa.put(t.getData());
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBMgr.close(rs, pres, conn);
        }
        
        long end_time = System.nanoTime();
        long duration = (end_time - start_time);
        
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }
    
    public ArrayList<Ticket> getTicketByOrderId(int order_id) {
        ArrayList<Ticket> result = new ArrayList<Ticket>();
        
        String exexcute_sql = "";
        ResultSet rs = null;
        Ticket tk;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "SELECT * FROM `missa_group7`.`tickets` WHERE `tickets`.`ticket_order_id` = ? ";
            
            pres = conn.prepareStatement(sql);
            pres.setInt(1, order_id);
            
            rs = pres.executeQuery();
            
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            while(rs.next()) {
      
                int ticket_id = rs.getInt("ticket_id");
                int ticket_price = rs.getInt("ticket_price");
                int session_id = rs.getInt("ticket_session_id");
                String seat_id = rs.getString("ticket_seat_id");
                String ticket_type = rs.getString("ticket_type");
                int quantity = rs.getInt("quantity");
                
                tk = new Ticket(order_id, ticket_id, ticket_price, session_id, seat_id, ticket_type, quantity);
                
                result.add(tk);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBMgr.close(pres, conn);
        }
        return result;
    }
    
    public JSONObject getBySessionId(String id) {
    	 
        String exexcute_sql = "";
        long start_time = System.nanoTime();
        
        int row = 0;
        ResultSet rs = null;
        Ticket tk = null;
        JSONArray jsa = new JSONArray();
        try {
            
            conn = DBMgr.getConnection();
         
            String sql = "SELECT * FROM `missa_group7`.`tickets` WHERE `tickets`.`ticket_session_id` = ? ";
            
            pres = conn.prepareStatement(sql);
            pres.setString(1, id);
       
            rs = pres.executeQuery();

            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
          
            while(rs.next()) {
            
            	int order_id = rs.getInt("ticket_order_id");
            	int ticket_id = rs.getInt("ticket_id");
                int session_id = rs.getInt("ticket_session_id");
                String seat_id = rs.getString("ticket_seat_id");
                int ticket_price = rs.getInt("ticket_price");
                String ticket_type = rs.getString("ticket_type");
                int quantity = rs.getInt("quantity");
        
                tk = new Ticket(order_id, ticket_id, ticket_price, session_id, seat_id, ticket_type, quantity);
                
                jsa.put(tk.getTickerAllInfo());
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBMgr.close(pres, conn);
        }

        long end_time = System.nanoTime();
        long duration = (end_time - start_time);
        
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }
    
    
    public Ticket getById(String id) {
 
        String exexcute_sql = "";
        ResultSet rs = null;
        Ticket tk = null;
     
        try {
            
            conn = DBMgr.getConnection();
         
            String sql = "SELECT * FROM `missa_group7`.`tickets` WHERE `tickets`.`ticket_id` = ? ";
        
            pres = conn.prepareStatement(sql);
            pres.setString(1, id);
       
            rs = pres.executeQuery();

            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
          
            while(rs.next()) {
            
            	int order_id = rs.getInt("ticket_order_id");
            	int ticket_id = rs.getInt("ticket_id");
                int session_id = rs.getInt("ticket_session_id");
                String seat_id = rs.getString("ticket_seat_id");
                int ticket_price = rs.getInt("ticket_price");
                String ticket_type = rs.getString("ticket_type");
                int quantity = rs.getInt("quantity");
        
                tk = new Ticket(order_id, ticket_id, ticket_price, session_id, seat_id, ticket_type, quantity);
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBMgr.close(pres, conn);
        }
        return tk;
    }
    
    public JSONObject deleteByOrderID(int order_id) {

        String exexcute_sql = "";
        long start_time = System.nanoTime();
        int row = 0;
        ResultSet rs = null;
        
        try {
           
            conn = DBMgr.getConnection();

            String sql = "DELETE FROM `missa_group7`.`tickets` WHERE `tickets`.`ticket_order_id` = ? ";
        
            pres = conn.prepareStatement(sql);
            pres.setInt(1, order_id);
       
            row = pres.executeUpdate();
    
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
        } catch (SQLException e) {
         
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
           
            e.printStackTrace();
        } finally {
           
            DBMgr.close(rs, pres, conn);
        }
      
        long end_time = System.nanoTime();
        long duration = (end_time - start_time);
        
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);

        return response;
    }
}
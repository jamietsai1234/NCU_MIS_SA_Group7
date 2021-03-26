package ncu.im3069.demo.app;

import java.sql.*;
import org.json.*;
import ncu.im3069.demo.util.DBMgr;

public class OrderHelper {
    
    private static OrderHelper oh;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private TicketHelper th =  TicketHelper.getHelper();
    
    private OrderHelper() {
    }
    
    public static OrderHelper getHelper() {
        if(oh == null) oh = new OrderHelper();
        
        return oh;
    }
    
    public JSONObject create(Order order) {
        
        String exexcute_sql = "";
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "INSERT INTO `missa_group7`.`orders`(`member_name`, `member_email`, `picker_name`, `picker_phone`,  `order_create`, `order_modify`, `state`, `payment_method`)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            
            String member_name = order.getMemberName();
            String member_email = order.getMemberEmail();
            String picker_name = order.getPickerName();
            String picker_phone = order.getPickerPhone();
            String payment_method = order.getPaymentMethod();
            boolean state = order.getState();
            Timestamp order_create = order.getOrderCreateTime();
            Timestamp order_modify = order.getOrderModifyTime();
            
            pres = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pres.setString(1, member_name);
            pres.setString(2, member_email);
            pres.setString(3, picker_name);
            pres.setString(4, picker_phone);
            pres.setTimestamp(5, order_create);
            pres.setTimestamp(6, order_modify);
            pres.setBoolean(7, state);
            pres.setString(8, payment_method);
            
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
    
    public JSONObject getMaxID( ) {
        JSONObject data = new JSONObject();
        Order o = null;
   
        String exexcute_sql = "";
        long start_time = System.nanoTime();
        int row = 0;
        ResultSet rs = null;
        
        try {
            conn = DBMgr.getConnection();
     
            String sql ="SELECT * FROM `missa_group7`.`orders` ORDER BY `order_id` DESC LIMIT 0 , 1";
            pres = conn.prepareStatement(sql);

            rs = pres.executeQuery();

            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            while(rs.next()) {
                row += 1;
                
                int order_id = rs.getInt("order_id");
               String member_name = rs.getString("member_name");
               String member_email = rs.getString("member_email");
               String picker_name = rs.getString("picker_name");
               String picker_phone = rs.getString("picker_phone");
               String payment_method = rs.getString("payment_method");
               boolean state = rs.getBoolean("state");
               Timestamp order_create = rs.getTimestamp("order_create");
               Timestamp order_modify = rs.getTimestamp("order_modify");

              o = new Order(order_id, member_name, member_email, picker_name, picker_phone, payment_method, state, order_create, order_modify);
              data = o.getOrderAllInfo();
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
        response.put("data", data);

        return response;
    }  
    
    public JSONObject getAll() {
        Order o = null;
        JSONArray jsa = new JSONArray();

        String exexcute_sql = "";

        long start_time = System.nanoTime();

        int row = 0;

        ResultSet rs = null;
        
        try {

            conn = DBMgr.getConnection();

            String sql = "SELECT * FROM `missa_group7`.`orders`";

            pres = conn.prepareStatement(sql);

            rs = pres.executeQuery();

            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            while(rs.next()) {
                row += 1;
                
                int id = rs.getInt("order_id");
                String member_name = rs.getString("member_name");
                String member_email = rs.getString("member_email");
                String picker_name = rs.getString("picker_name");
                String picker_phone = rs.getString("picker_phone");
                String payment_method = rs.getString("payment_method");
                boolean state = rs.getBoolean("state");
                Timestamp order_create = rs.getTimestamp("order_create");
                Timestamp order_modify = rs.getTimestamp("order_modify");

                o = new Order(id, member_name, member_email, picker_name, picker_phone, payment_method, state, order_create, order_modify);
 
                jsa.put(o.getOrderAllInfo());
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
    
    public JSONObject getMemberAll(String email) {
        Order o = null;
        JSONArray jsa = new JSONArray();

        String exexcute_sql = "";

        long start_time = System.nanoTime();

        int row = 0;

        ResultSet rs = null;
        
        try {

            conn = DBMgr.getConnection();

            String sql = "SELECT * FROM `missa_group7`.`orders` where `orders`.`member_email` = ?";

            pres = conn.prepareStatement(sql);
            pres.setString(1, email);
            rs = pres.executeQuery();

            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            while(rs.next()) {
                row += 1;
                
                int id = rs.getInt("order_id");
                String member_name = rs.getString("member_name");
                String member_email = rs.getString("member_email");
                String picker_name = rs.getString("picker_name");
                String picker_phone = rs.getString("picker_phone");
                String payment_method = rs.getString("payment_method");
                boolean state = rs.getBoolean("state");
                Timestamp order_create = rs.getTimestamp("order_create");
                Timestamp order_modify = rs.getTimestamp("order_modify");
                
                o = new Order(id, member_name, member_email, picker_name, picker_phone, payment_method, state, order_create, order_modify);
                jsa.put(o.getOrderAllInfo());
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
    
    public JSONObject getById(String order_id) {
        JSONObject data = new JSONObject();
        Order o = null;
        
        String exexcute_sql = "";
        
        long start_time = System.nanoTime();
  
        int row = 0;
       
        ResultSet rs = null;
        
        try {
           
            conn = DBMgr.getConnection();
            
            String sql = "SELECT * FROM `missa_group7`.`orders` WHERE `orders`.`order_id` = ?";
      
            pres = conn.prepareStatement(sql);
            pres.setString(1, order_id);
          
            rs = pres.executeQuery();

            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            while(rs.next()) {
   
                row += 1;
                
                int id = rs.getInt("order_id");
                String member_name = rs.getString("member_name");
                String member_email = rs.getString("member_email");
                String picker_name = rs.getString("picker_name");
                String picker_phone = rs.getString("picker_phone");
                String payment_method = rs.getString("payment_method");
                boolean state = rs.getBoolean("state");
                Timestamp order_create = rs.getTimestamp("order_create");
                Timestamp order_modify = rs.getTimestamp("order_modify");
                
                o = new Order(id, member_name, member_email, picker_name, picker_phone, payment_method, state, order_create, order_modify);

                data = o.getOrderAllInfo();
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
        response.put("data", data);

        return response;
    }
    public JSONObject deleteByID(int order_id) {

        String exexcute_sql = "";

        long start_time = System.nanoTime();

        int row = 0;

        ResultSet rs = null;
        
        try {
           
            conn = DBMgr.getConnection();

            String sql = "DELETE FROM `missa_group7`.`orders` WHERE `order_id` = ? LIMIT 1";
            
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
        
        JSONObject query = th.deleteByOrderID(order_id);
        
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("response", query);
        
        return response;
    }
}

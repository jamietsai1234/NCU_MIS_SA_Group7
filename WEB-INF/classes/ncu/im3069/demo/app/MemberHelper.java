package ncu.im3069.demo.app;

import java.sql.*;
import java.time.LocalDateTime;
import org.json.*;

//import ncu.im3069.Group13.app.Member;
import ncu.im3069.demo.util.DBMgr;

// TODO: Auto-generated Javadoc


public class MemberHelper {
    
    
    private MemberHelper() {
    }
    
    private static MemberHelper mh;
    
    private Connection conn = null;
    
    private PreparedStatement pres = null;
    
    public static MemberHelper getHelper() {
        
        if(mh == null) mh = new MemberHelper();
        
        return mh;
    }
    
    
    public JSONObject deleteByID(int id) {
        
        String exexcute_sql = "";
        
        long start_time = System.nanoTime();
        
        int row = 0;
        
        ResultSet rs = null;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "DELETE FROM `missa_group7`.`member` WHERE `member_id` = ? LIMIT 1";
            
            pres = conn.prepareStatement(sql);
            pres.setInt(1, id);
            
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
    
    
    public JSONObject getAll() {
        
        Member m = null;
        
        JSONArray jsa = new JSONArray();
        
        String exexcute_sql = "";
        
        long start_time = System.nanoTime();
        
        int row = 0;
        
        ResultSet rs = null;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "SELECT * FROM `missa_group7`.`member`";
            
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();

            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            while(rs.next()) {
                
                row += 1;
                
                int member_id = rs.getInt("member_id");
                String name = rs.getString("member_name");
                String email = rs.getString("member_email");
                String password = rs.getString("member_password");
                int login_times = rs.getInt("member_login_times");
                boolean status = rs.getBoolean("member_status");
                
                m = new Member(member_id, email, password, name, login_times, status);
                
                jsa.put(m.getData());
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
    
    
    public JSONObject getByID(String id) {
        
        Member m = null;
        
        JSONArray jsa = new JSONArray();
        
        String exexcute_sql = "";
        
        long start_time = System.nanoTime();
        
        int row = 0;
        
        ResultSet rs = null;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "SELECT * FROM `missa_group7`.`member` WHERE `member_id` = ? LIMIT 1";
            
            pres = conn.prepareStatement(sql);
            pres.setString(1, id);
            
            rs = pres.executeQuery();

            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            while(rs.next()) {
                
                row += 1;
                
                int member_id = rs.getInt("member_id");
                String name = rs.getString("member_name");
                String email = rs.getString("member_email");
                String password = rs.getString("member_password");
                int login_times = rs.getInt("member_login_times");
                boolean status = rs.getBoolean("member_status");
                
                m = new Member(member_id, email, password, name, login_times, status);
                
                jsa.put(m.getData());
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
    
    public JSONObject getByLoginID(String id) {
        
        Member m = null;
        
        JSONArray jsa = new JSONArray();
        
        String exexcute_sql = "";
        
        long start_time = System.nanoTime();
        
        int row = 0;
        
        ResultSet rs = null;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "SELECT * FROM `missa_group7`.`member` WHERE `member_id` = ? LIMIT 1";
            
            pres = conn.prepareStatement(sql);
            pres.setString(1, id);
            
            rs = pres.executeQuery();
            
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            while(rs.next()) {
                
                row += 1;
                
                int member_id = rs.getInt("member_id");
                String email = rs.getString("member_email");
                String password = rs.getString("member_password");
                m = new Member(member_id, email, password);
                jsa.put(m.getData());
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
    
    public JSONObject getByEmail(String login_email) {
        
        Member m = null;
        
        JSONArray jsa = new JSONArray();
        
        String exexcute_sql = "";
        
        long start_time = System.nanoTime();
        
        int row = 0;
        
        ResultSet rs = null;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "SELECT * FROM `missa_group7`.`member` WHERE `member_email` = ? LIMIT 1";
            
            pres = conn.prepareStatement(sql);
            pres.setString(1, login_email);
            
            rs = pres.executeQuery();
            
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            while(rs.next()) {
                
                row += 1;
                
                int member_id = rs.getInt("member_id");
                String name = rs.getString("member_name");
                String email = rs.getString("member_email");
                String password = rs.getString("member_password");
                Boolean status = rs.getBoolean("member_status");
                
                m = new Member(member_id, name, email, password, status);
                
                jsa.put(m.getData());
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
    
    public JSONObject getLoginTimesStatus(Member m) {
        
        JSONObject jso = new JSONObject();
        
        ResultSet rs = null;

        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "SELECT * FROM `missa_group7`.`member` WHERE `member_id` = ? LIMIT 1";
            
            pres = conn.prepareStatement(sql);
            pres.setInt(1, m.getID());
            
            rs = pres.executeQuery();
            
            while(rs.next()) {
                
                int login_times = rs.getInt("member_login_times");
                boolean status = rs.getBoolean("member_status");
                
                jso.put("member_login_times", login_times);
                jso.put("member_status", status);
            }
            
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBMgr.close(rs, pres, conn);
        }
        return jso;
    }
    
    public boolean checkDuplicate(Member m){
        
        int row = -1;
        ResultSet rs = null;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "SELECT count(*) FROM `missa_group7`.`member` WHERE `member_email` = ?";
            
            String email = m.getEmail();
            
            pres = conn.prepareStatement(sql);
            pres.setString(1, email);
            
            rs = pres.executeQuery();

            rs.next();
            row = rs.getInt("count(*)");
            System.out.print(row);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBMgr.close(rs, pres, conn);
        }
        return (row == 0) ? false : true;
    }
    
    public JSONObject create(Member m) {
        
        String exexcute_sql = "";
        
        long start_time = System.nanoTime();
        
        int row = 0;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "INSERT INTO `missa_group7`.`member`(`member_name`, `member_email`, `member_password`, `member_modified`, `member_created`, `member_login_times`, `member_status`)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?)";
            
            String name = m.getName();
            String email = m.getEmail();
            String password = m.getPassword();
            int login_times = m.getLoginTimes();
            boolean status = m.getStatus();
            
            pres = conn.prepareStatement(sql);
            pres.setString(1, name);
            pres.setString(2, email);
            pres.setString(3, password);
            pres.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            pres.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            pres.setInt(6, login_times);
            pres.setBoolean(7, status);
            
            row = pres.executeUpdate();
            
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);

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
        response.put("time", duration);
        response.put("row", row);

        return response;
    }
    
    public JSONObject update(Member m) {
        
        JSONArray jsa = new JSONArray();
        
        String exexcute_sql = "";
        
        long start_time = System.nanoTime();
        
        int row = 0;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "Update `missa_group7`.`member` SET `member_name` = ? ,`member_password` = ? , `member_modified` = ? WHERE `member_email` = ?";
            
            String name = m.getName();
            String email = m.getEmail();
            String password = m.getPassword();
            
            pres = conn.prepareStatement(sql);
            pres.setString(1, name);
            pres.setString(2, password);
            pres.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pres.setString(4, email);
            
            row = pres.executeUpdate();
            
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);

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
    
    public void updateLoginTimes(Member m) {
        
        int new_times = m.getLoginTimes();
        
        String exexcute_sql = "";
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "Update `missa_group7`.`member` SET `member_login_times` = ? WHERE `member_id` = ?";
            
            int id = m.getID();
            
            pres = conn.prepareStatement(sql);
            pres.setInt(1, new_times);
            pres.setInt(2, id);
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
    }
    
    public void updateStatus(Member m, Boolean status) {      
        
        String exexcute_sql = "";
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "Update `missa_group7`.`member` SET `member_status` = ? WHERE `member_id` = ?";
            
            int id = m.getID();
            
            pres = conn.prepareStatement(sql);
            pres.setBoolean(1, status);
            pres.setInt(2, id);
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
    }
    
    public boolean checkLogin(Member m){
        
        int row = -1;
        ResultSet rs = null;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "SELECT count(*) FROM `missa_group7`.`member` WHERE `member_email` = ? AND `member_password` = ? AND `member_status` = 1";
            String email = m.getEmail();
            String password = m.getPassword();
             
            pres = conn.prepareStatement(sql);
            pres.setString(1, email);
            pres.setString(2, password);
            
            rs = pres.executeQuery();
            rs.next();
            row = rs.getInt("count(*)");
            System.out.print(row);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBMgr.close(rs, pres, conn);
        }
        return (row == 1) ? true : false;
    }
    
    public JSONObject getByEmailCart(String login_email) {
        
        Member m = null;
        JSONArray jsa = new JSONArray();
        String exexcute_sql = "";
        long start_time = System.nanoTime();
        int row = 0;
        
        ResultSet rs = null;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "SELECT * FROM `missa_group7`.`member` WHERE member_email = ? LIMIT 1";
            
            pres = conn.prepareStatement(sql);
            pres.setString(1, login_email);
            rs = pres.executeQuery();
            
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            while(rs.next()) {
                
                row += 1;
                
                int member_id = rs.getInt("member_id");
                String name = rs.getString("member_name");
                String email = rs.getString("member_email");
                String password = rs.getString("member_password");
                Boolean status = rs.getBoolean("member_status");
                
                m = new Member(member_id, name, email, password, status);
                
                jsa.put(m.getMemberAllInfo());
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
}

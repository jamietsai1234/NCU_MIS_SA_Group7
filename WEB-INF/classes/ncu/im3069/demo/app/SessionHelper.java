package ncu.im3069.demo.app;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.*;

import ncu.im3069.demo.util.DBMgr;
//import ncu.im3069.demo.app.Movie;

public class SessionHelper {
    private SessionHelper() {
        
    }
    
    private static SessionHelper ssh;
    private Connection conn = null;
    private PreparedStatement pres = null;
    
    public static SessionHelper getHelper() {
        
        if(ssh == null) ssh = new SessionHelper();
        
        return ssh;
    }
    
    public JSONObject getAll() {
        
    	MovieSession p = null;
        
        JSONArray jsa = new JSONArray();
        
        String exexcute_sql = "";
        
        long start_time = System.nanoTime();
        
        int row = 0;
        
        ResultSet rs = null;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "SELECT * FROM `missa_group7`.`session`";
            
            
            pres = conn.prepareStatement(sql);
            
            rs = pres.executeQuery();

            
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            
            while(rs.next()) {
                
                row += 1;
                
                
                int session_id = rs.getInt("session_id");
                int movie_id = rs.getInt("movie_id");
                String datetime = rs.getString("datetime");
                int lobby = rs.getInt("lobby");
                
                
                
                p = new MovieSession(session_id,movie_id, datetime, lobby);
                
                jsa.put(p.getData());
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
    	//JSONObject data = new JSONObject();
        
        MovieSession m = null;
        
        JSONArray jsa = new JSONArray();
        
        String exexcute_sql = "";
        
        long start_time = System.nanoTime();
        
        int row = 0;
        
        ResultSet rs = null;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "SELECT * FROM `missa_group7`.`session` WHERE `session_id` = ? LIMIT 1";
            
            
            pres = conn.prepareStatement(sql);
            pres.setString(1, id);
            
            rs = pres.executeQuery();

            
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            
            
            while(rs.next()) {
                
                row += 1;
                
                
                int session_id = rs.getInt("session_id");
                int movie_id = rs.getInt("movie_id");
                String datetime = rs.getString("datetime");
                int lobby = rs.getInt("lobby");
                
                
                m = new MovieSession(session_id,movie_id,datetime,lobby);
                
                //data = m.getMovieAllInfo();
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
    
    
   


    public boolean checkDuplicate(Movie m){
        
        int row = -1;
        
        ResultSet rs = null;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "SELECT count(*) FROM `missa_group7`.`movie` WHERE `movie_name` = ?";
            
            
            String name = m.getName();
            
            
            pres = conn.prepareStatement(sql);
            pres.setString(1, name);
            
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
    
    
    public JSONObject create(MovieSession ms) {
        
        String exexcute_sql = "";
        
        long start_time = System.nanoTime();
        
        int row = 0;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "INSERT INTO `missa_group7`.`session`( `datetime`, `lobby`,`movie_id`)"
                    + " VALUES(?, ?, ?)";
            
            
            //int session_id = ms.getSessionId();
            int movie_id = ms.getMovieId();
            String datetime = ms.getDatetime();
            int lobby = ms.getLobby();
            
            
            pres = conn.prepareStatement(sql);
            //pres.setInt(1, session_id);
            
            pres.setString(1, datetime);
            pres.setInt(2, lobby);
            pres.setInt(3, movie_id);
            //pres.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            //pres.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            //pres.setInt(6, login_times);
            //pres.setString(7, status);
            
            
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
    
    
    public JSONObject update(MovieSession ms) {
        
        JSONArray jsa = new JSONArray();
        
        String exexcute_sql = "";
        
        long start_time = System.nanoTime();
        
        int row = 0;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "Update `missa_group7`.`session` SET  `datetime` = ? , `lobby` = ? ,`movie_id` = ? WHERE `session_id` = ?";
            
            int session_id = ms.getSessionId();
            int movie_id = ms.getMovieId();
            String datetime = ms.getDatetime();       
            int lobby = ms.getLobby();
            
            pres = conn.prepareStatement(sql);
            //pres.setInt(1, session_id);
           
            pres.setString(1, datetime);
            pres.setInt(2, lobby);
            pres.setInt(3, movie_id);
            pres.setInt(4, session_id);
            
            
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
    
    
    public JSONObject deleteByID(int id) {
        
        String exexcute_sql = "";
        
        long start_time = System.nanoTime();
        
        int row = 0;
        
        ResultSet rs = null;
        
        try {
            
            conn = DBMgr.getConnection();
            
            
            String sql = "DELETE FROM `missa_group7`.`session` WHERE `session_id` = ? LIMIT 1";
            
            
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
    
    public JSONObject deleteByMovieID(int id) {

        String exexcute_sql = "";

        long start_time = System.nanoTime();

        int row = 0;

        ResultSet rs = null;
        
        try {
           
            conn = DBMgr.getConnection();
            

            String sql = "DELETE FROM `missa_group7`.`session` WHERE `session`.`movie_id` = ? ";
            
        
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
    
}



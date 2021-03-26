package ncu.im3069.demo.app;

import java.sql.*;
import java.util.*;
import org.json.*;
import ncu.im3069.demo.util.DBMgr;

public class MovieSessionHelper {
   
    private MovieSessionHelper() {}    
    private static MovieSessionHelper msh;
    private Connection conn = null;
    private PreparedStatement pres = null;
        
    public static MovieSessionHelper getHelper() {
        
        if(msh == null) msh = new MovieSessionHelper();       
        return msh;
    }
    
    public ArrayList<MovieSession> getSessionByMovieId(int movie_id) {
        ArrayList<MovieSession> result = new ArrayList<MovieSession>();      
        String exexcute_sql = "";
        ResultSet rs = null;
        MovieSession ms;
        
        try {           
            conn = DBMgr.getConnection();
            
            String sql = "SELECT * FROM `missa_group7`.`session` WHERE `session`.`movie_id` = ? ";
            
            pres = conn.prepareStatement(sql);
            pres.setInt(1, movie_id);
                        
            rs = pres.executeQuery();
                       
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            while(rs.next()) {
                
            	int session_id = rs.getInt("session_id");
                String datetime = rs.getString("datetime");
                int lobby = rs.getInt("lobby");
               
                ms = new MovieSession(movie_id, session_id, datetime, lobby);
                
                result.add(ms);
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
   
    public MovieSession getById(String id) { 
        String exexcute_sql = "";
        ResultSet rs = null;
        MovieSession ms = null;
     
        try {          
            conn = DBMgr.getConnection();
         
            String sql = "SELECT * FROM `missa_group7`.`session` WHERE `session`.`movie_id` = ? ";
                    
            pres = conn.prepareStatement(sql);
            pres.setString(1, id);
       
            rs = pres.executeQuery();
  
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
          
            while(rs.next()) {            
            	int session_id = rs.getInt("session_id");
            	int movie_id = rs.getInt("movie_id");
                String datetime = rs.getString("datetime");
                int lobby = rs.getInt("lobby");
                ms = new MovieSession(session_id, movie_id, datetime, lobby);
            }
        } catch (SQLException e) {           
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {      
            e.printStackTrace();
        } finally { 
            DBMgr.close(pres, conn);
        }
        return ms;
    }
    
    public JSONObject getByIdSession(String id) {
        JSONObject data = new JSONObject();
        MovieSession s = null;
   
        String exexcute_sql = "";
        long start_time = System.nanoTime();
        int row = 0;
        ResultSet rs = null;
        
        try {
            conn = DBMgr.getConnection();
     
            String sql = "SELECT * FROM `missa_group7`.`session` WHERE session_id = ?";

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
            
                s = new MovieSession(session_id, movie_id, datetime, lobby);
             
                data = s.getSessionInfo();
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
}
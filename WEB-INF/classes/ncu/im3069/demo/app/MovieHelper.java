package ncu.im3069.demo.app;

import java.sql.*;
import org.json.*;
import ncu.im3069.demo.util.DBMgr;

public class MovieHelper {
    private MovieHelper() {
        
    }
    private SessionHelper ssh =  SessionHelper.getHelper();
    private static MovieHelper mh;
    private Connection conn = null;
    private PreparedStatement pres = null;
    
    public static MovieHelper getHelper() {
        
        if(mh == null) mh = new MovieHelper();       
        return mh;
    }
    
    public JSONObject getAll() {
        
    	Movie p = null;
        JSONArray jsa = new JSONArray();
        String exexcute_sql = "";
        long start_time = System.nanoTime();
        int row = 0;
        
        ResultSet rs = null;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "SELECT * FROM `missa_group7`.`movie`";
            
            pres = conn.prepareStatement(sql);
            
            rs = pres.executeQuery();

            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            while(rs.next()) {
                
                row += 1;
                
                int movie_id = rs.getInt("movie_id");
                String name = rs.getString("movie_name");
                String intro = rs.getString("movie_intro");
                String length = rs.getString("movie_length");
                String image = rs.getString("movie_image");
                
                p = new Movie(movie_id, name, intro, length,image);
                
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
        
        Movie m = null;
        JSONArray jsa = new JSONArray();
        String exexcute_sql = "";
        long start_time = System.nanoTime();
        int row = 0;
        
        ResultSet rs = null;
        
        try {
            conn = DBMgr.getConnection();
            
            String sql = "SELECT * FROM `missa_group7`.`movie` WHERE `movie_id` = ? LIMIT 1";
            
            pres = conn.prepareStatement(sql);
            pres.setString(1, id);
            
            rs = pres.executeQuery();
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            while(rs.next()) {
                
                row += 1;
                int movie_id = rs.getInt("movie_id");
                String name = rs.getString("movie_name");
                String intro = rs.getString("movie_intro");
                String length = rs.getString("movie_length");
                String image = rs.getString("movie_image");
                
                m = new Movie(movie_id, name,intro, length,image);
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
    public Movie getByMID(String id) {
        
        Movie m = null;
        JSONArray jsa = new JSONArray();
        String exexcute_sql = "";
        
        long start_time = System.nanoTime();
        
        int row = 0;
        
        ResultSet rs = null;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "SELECT * FROM `missa_group7`.`movie` WHERE `movie_id` = ? LIMIT 1";
            
            pres = conn.prepareStatement(sql);
            pres.setString(1, id);
            rs = pres.executeQuery();
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            while(rs.next()) {
                
                row += 1;
                int movie_id = rs.getInt("movie_id");
                String name = rs.getString("movie_name");
                String intro = rs.getString("movie_intro");
                String length = rs.getString("movie_length");
                String image = rs.getString("movie_image");
                
                m = new Movie(movie_id, name,intro, length,image);
                
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

        return m;
    }
    
    public JSONObject getByIdMDetail(String id) {
        JSONObject data = new JSONObject();
        Movie p = null;
        String exexcute_sql = "";
        long start_time = System.nanoTime();
        int row = 0;
        ResultSet rs = null;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "SELECT * FROM `missa_group7`.`movie` WHERE `movie`.`movie_id` = ?";
            
            pres = conn.prepareStatement(sql);
            pres.setString(1, id);
            rs = pres.executeQuery();
            
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            while(rs.next()) {
                
                row += 1;
                int movie_id = rs.getInt("movie_id");
                String name = rs.getString("movie_name");
                String intro = rs.getString("movie_intro");
                String length = rs.getString("movie_length");
                String image = rs.getString("movie_image");
                
                p = new Movie(movie_id, name, intro, length,image);
                data = p.getMovieAllInfo();
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
    
    
    public JSONObject create(Movie m) {
        
        String exexcute_sql = "";
        long start_time = System.nanoTime();
        int row = 0;
        
        try {
            
            conn = DBMgr.getConnection();
            String sql = "INSERT INTO `missa_group7`.`movie`(`movie_name`, `movie_intro`, `movie_length`, `movie_image`)"
                    + " VALUES(?, ?, ?, ?)";
            
            String name = m.getName();
            String intro = m.getIntro();
            String length = m.getLength();
            String image = m.getImage();
            
            pres = conn.prepareStatement(sql);
            pres.setString(1, name);
            pres.setString(2, intro);
            pres.setString(3, length);
            pres.setString(4, image);
            
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
    
    
    public JSONObject update(Movie m) {
        
        JSONArray jsa = new JSONArray();
        String exexcute_sql = "";
        long start_time = System.nanoTime();
        int row = 0;
        
        try {
            
            conn = DBMgr.getConnection();
            
            String sql = "Update `missa_group7`.`movie` SET `movie_name` = ? ,`movie_intro` = ? , `movie_length` = ? , `movie_image` = ?WHERE `movie_id` = ?";
            
            String name = m.getName();
            String intro = m.getIntro();
            String length = m.getLength();
            String image = m.getImage();
            int id = m.getID();
            
            pres = conn.prepareStatement(sql);
            pres.setString(1, name);
            pres.setString(2, intro);
            pres.setString(3, length);
            pres.setString(4, image);
            pres.setInt(5, id);
            
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
            
            String sql = "DELETE FROM `missa_group7`.`movie` WHERE `movie_id` = ? LIMIT 1";
            
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
        
        JSONObject query = ssh.deleteByMovieID(id);
        
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("response", query);
        
        return response;
    }
    
}
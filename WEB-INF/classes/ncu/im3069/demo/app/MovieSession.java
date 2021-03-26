package ncu.im3069.demo.app;

import org.json.JSONObject;

public class MovieSession {

    private int session_id;
    private int movie_id;
    private String datetime;   
    private int lobby;
    private SessionHelper ssh =  SessionHelper.getHelper();
    
	public MovieSession(int session_id, int movie_id,String datetime, int lobby) {
		this.session_id = session_id;
		this.movie_id = movie_id;
        this.datetime = datetime;
        this.lobby = lobby;      
    }
  
	public MovieSession(int movie_id, String datetime, int lobby) {
		this.movie_id = movie_id;
        this.datetime = datetime;
        this.lobby = lobby;      
    }	
	
    public void setId(int session_id) {
        this.session_id = session_id;
    }
    public int getSessionId() {
        return this.session_id;
    }
    public int getMovieId() {
		return this.movie_id;
	}
    public String getDatetime() {
        return this.datetime;
    }  
	public int getLobby() {
		return this.lobby;
	}
	
    public JSONObject getData() {
    	JSONObject data = new JSONObject();
    	data.put("session_id", getSessionId());
    	data.put("movie_id", getMovieId());
    	data.put("datetime", getDatetime());
    	data.put("lobby", getLobby());    	
    	return data;
    }
    
	public JSONObject getSessionInfo() {
        JSONObject jso = new JSONObject();
        jso.put("cartsesion_info", getData());
        return jso;
    }

    public JSONObject update() {
        JSONObject data = new JSONObject();
        if(this.session_id != 0) {
            data =ssh.update(this);
        }
        
        return data;
    }
}
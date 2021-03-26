package ncu.im3069.demo.app;

import java.util.ArrayList;
import java.util.Calendar;

import org.json.*;

public class Movie {
	
    private int id;
    private String name;
    private String intro;
    private String length;
    private String image;
    private ArrayList<MovieSession> list = new ArrayList<MovieSession>();    
    private MovieSessionHelper msh = MovieSessionHelper.getHelper();
    private MovieHelper mh =  MovieHelper.getHelper();

	public Movie(int id) {
		this.id = id;
	}

	public Movie(String name, String intro, String image) {
		this.name = name;
		this.intro = intro;
		this.image = image;
	}

	public Movie(int id, String name, String intro,String length,String image) {
		this.id = id;
		this.name = name;
		this.intro = intro;
		this.image = image;
		this.length = length;
		getSessionFromDB();
	}
	
	public Movie(String name, String intro,String length,String image) {
		this.name = name;
		this.intro = intro;
		this.length = length;
		this.image = image;
		update();
	}

	public void getSessionFromDB() {
		ArrayList<MovieSession> data = msh.getSessionByMovieId(this.id);
        this.list = data;
	}
	
	public int getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getImage() {
		return this.image;
	}

	public String getIntro() {
		return this.intro;
	}
	
	public String getLength() {
		return this.length;
	}

	public JSONObject getData() {
        JSONObject jso = new JSONObject();
        jso.put("id", getID());
        jso.put("name", getName());
        jso.put("intro", getIntro());
        jso.put("length", getLength());
        jso.put("image", getImage());

        return jso;
    }
	
    public JSONArray getSessionData() {
        JSONArray result = new JSONArray();
        for(int i=0 ; i < this.list.size() ; i++) {
            result.put(this.list.get(i).getData());
        }
        return result;
    }
    
	public JSONObject getMovieAllInfo() {
        JSONObject jso = new JSONObject();
        jso.put("movie_info", getData());
        jso.put("session_info", getSessionData());

        return jso;
    }

    public JSONObject update() {
        JSONObject data = new JSONObject();
        if(this.id != 0) {
        	data = mh.update(this);
        }        
        return data;
    }
}

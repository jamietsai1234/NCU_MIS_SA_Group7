package ncu.im3069.demo.app;

import org.json.*;
import java.util.Calendar;

public class Member {
    
    private int id;
    private String email;
    private String name;
    private String password;
    private int login_times;
    private boolean status;
    private MemberHelper mh =  MemberHelper.getHelper();

    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        update();
    }

    public Member(int id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        getLoginTimesStatus();
        calcAccName();
    }
    
    public Member(int id, String email, String password, String name, int login_times, boolean status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.login_times = login_times;
        this.status = status;
    }
    
    public Member(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
    
    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public Member(int id, String name,String email, String password,  boolean status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.status = status;
    }
    
    public int getID() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }
    
    public int getLoginTimes() {
        return this.login_times;
    }

    public boolean getStatus() {
        return this.status;
    }
    
    public JSONObject update() {
        
        JSONObject data = new JSONObject();
        Calendar calendar = Calendar.getInstance();
        this.login_times = calendar.get(Calendar.MINUTE);
        calcAccName();

        if(this.id != 0) {
            mh.updateLoginTimes(this);
            data = mh.update(this);
        }
        
        return data;
    }
    
    public JSONObject getData() {
        JSONObject jso = new JSONObject();
        jso.put("id", getID());
        jso.put("name", getName());
        jso.put("email", getEmail());
        jso.put("password", getPassword());
        jso.put("login_times", getLoginTimes());
        jso.put("status", getStatus());
        
        return jso;
    }
   
    private void getLoginTimesStatus() {
        JSONObject data = mh.getLoginTimesStatus(this);
        this.login_times = data.getInt("member_login_times");
        this.status = data.getBoolean("member_status");
    }
    
    public JSONObject getMemberAllInfo() {
        JSONObject jso = new JSONObject();
        jso.put("member_info", getData());
       

        return jso;
    }

    private void calcAccName() {
    	//boolean curr_status = (this.login_times % 2 == 0) ? true:false;//"偶數會員" : "奇數會員";
    	boolean curr_status = true;
        this.status = curr_status;
        if(this.id != 0) mh.updateStatus(this, curr_status);
    }
}
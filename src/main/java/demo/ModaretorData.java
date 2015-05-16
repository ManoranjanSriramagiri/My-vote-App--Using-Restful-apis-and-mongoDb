package demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.TimeZone;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ModaretorData 
{
	
	private int id;
	private static int counter=100000;
	private String name;
	private String email;
	private String password;
	private String created_at;

	public ModaretorData returnModaretorData()
	{
		
		return this;
	}
	
	ModaretorData(){};

	public int getId() {
		return id;
	}
	public void setId() {
		counter++;
		this.id = counter;
	}
	public void setcounter(int counter) {
		this.counter = 100000+counter;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at() {
		this.created_at =  getTimeNow();
    }
	public void resetCounter()
	{
		
		counter--;
	}

     public ModaretorData(@JsonProperty("id")int id, @JsonProperty("name")String name,
                     @JsonProperty("email")String email, @JsonProperty("password")String password, @JsonProperty("created_at")String created_at) {
        this.setId();
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setCreated_at();
    }


    private String  getTimeNow() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'dd:HH:mm.sss'Z'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
        return nowAsISO;
    }

}

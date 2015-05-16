package demo;

import java.util.TimeZone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;



public class PollData 
{
	protected static Integer counter=100000;
	private String id;
    private String question;
    private String started_at;
    private String expired_at;
    private String[] choice;
   
       
    
    PollData()
    {
    	
    }


	public PollData PollData(String question,String started_at,String expired_at)
    {
    	
    	
    	
    	return this;
    }
    
	public String getId() {
		return id;
	}
	public void setId() {
		
		this.id = converter(counter);
		counter++;
	}
	public void setCounter(Integer count) {
			
		counter =count+100000 ;
			counter++;
		}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getStarted_at() {
		return started_at;
	}
	public void setStarted_at() {
		this.started_at = getTimeNow() ;
	}
	public String getExpired_at() {
		return expired_at;
	}
	public void setExpired_at( ) {
		this.expired_at =getTimeNow() ;
	}
	public String[] getChoice() {
		return choice;
	}
	public void setChoice(String[] choice) {
		this.choice = choice;
	}
	
    
	public String converter(int idInInt){
		String idInStr="";
		while(idInInt>0)
		{
			int temp =0;
			temp=idInInt%36;
			if(temp<=9)
			{
				idInStr=temp+idInStr;
			}
			else
			{
				char tempC=(char)(55+temp);
				idInStr=tempC+idInStr;
			}
			
			idInInt=idInInt/36;
		}
		return idInStr;
	}


    private String  getTimeNow() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'dd:HH:mm.sss'Z'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
       return nowAsISO;
    }

    public PollData( @JsonProperty("question")String question,
                     @JsonProperty("choices")String[] choices) {
     // String[] choices=choice;
        this.setQuestion(question);
        this.setStarted_at();
        this.setChoice(choices);
        this.setExpired_at();
        this.setId();
    }

    public void resetCounter()
    {

        counter--;
    }

}

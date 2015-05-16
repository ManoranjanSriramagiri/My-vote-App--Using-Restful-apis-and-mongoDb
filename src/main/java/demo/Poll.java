package demo;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
//@RequestMapping( consumes = {"application/json","application/xml"}, produces = {"application/json","application/xml"})
public class Poll 
{
		
	 @RequestMapping(value = "api/v1/moderators/{id}/polls",method = {RequestMethod.POST//,RequestMethod.GET
     }

	 )
	 @ResponseBody
     @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	 public Object createPoll (HttpServletRequest request,@PathVariable int id,@Valid @RequestBody PollData pollData) 
	 {
		 
		 PollDataAccess pda =new PollDataAccess();
		 //pda.InsertPoll(pollData, id);
		 return  pda.InsertPoll(pollData, id);				
	 }
	 
	
	@RequestMapping(value = "api/v1/polls/{p_id}", method ={ RequestMethod.GET})
    public Object viewWithOutReult(@PathVariable("p_id") String p_id) {

		return new PollDataAccess().ViewWithOUTResults( p_id);
   }	
	
	@RequestMapping(value = "api/v1/moderators/{mod_id}/polls/{p_id}", method ={RequestMethod.PUT,RequestMethod.GET})
	public Object ViewWithResults(@PathVariable("p_id") String p_id,@PathVariable("mod_id") String mod_id)
     {		
			return new PollDataAccess().ViewWithResults( p_id);
	} 
	
	
	@RequestMapping(value = "api/v1/moderators/{id}/polls",method ={RequestMethod.GET})
	public List<Document> listAllPolls(@PathVariable("id") int id) {
	       
	   return new PollDataAccess().ListAllPolls(id);
	}
    @RequestMapping(value = "api/v1/moderators/{moderator_id}/polls/{poll_id}",method ={RequestMethod.DELETE})
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("poll_id") String poll_id) {
    	new PollDataAccess().DeletePoll(poll_id);
        return ;
    }
    

    @RequestMapping(value = "api/v1/polls/{poll_id}", method ={RequestMethod.PUT})
   @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public void VoteAPoll(@PathVariable("poll_id") String poll_id
            ,@RequestParam(value="choice", defaultValue="-1") int choice)
    {

       PollDataAccess p= new PollDataAccess();
       p.VoteAPoll(poll_id, choice);
    }

    


}




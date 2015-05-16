package demo;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.bson.Document;
//import org.neo4j.cypher.internal.compiler.v2_1.perty.impl.quoteString;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
//@RequestMapping( consumes = {"application/json","application/xml"}, produces = {"application/json","application/xml"})
public class Modaretor
{

    
    @RequestMapping(value = "/api/v1/moderators", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createModerator(@RequestBody @Valid ModaretorData moderator)
    {
    	ModeratorsDataAccess mda =new ModeratorsDataAccess();
	if((moderator.getEmail()!=null)&&(moderator.getPassword()!=null)&&(moderator.getName()!=null))

	{
		Object list= mda.InsertModerator(moderator);
      	return new ResponseEntity<Object>( list, HttpStatus.CREATED);
      	  
	}
	else
	{
		return new ResponseEntity(HttpStatus.BAD_REQUEST);

	}

    }


    @RequestMapping(value = "api/v1/moderators/{m_id}", method= RequestMethod.PUT)
    public  ResponseEntity<Object> updateModerator( @PathVariable("m_id") int m_id,
                                         @RequestBody @Valid ModaretorData moderator)
    {
        moderator.resetCounter();


	if((moderator.getEmail()!=null)||(moderator.getPassword()!=null)||(moderator.getName()!=null))

	{
			Object obj=new ModeratorsDataAccess().updateModerator(m_id, moderator.getName(), moderator.getEmail(), moderator.getPassword());
           
           
        return new ResponseEntity<Object>( obj, HttpStatus.CREATED);
	}
	else
	{		
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}	


    }

    @RequestMapping(value = "api/v1/moderators/{m_id}", method = {RequestMethod.GET})
	//   @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public Object getModerator( @PathVariable("m_id") int m_id)
    {	
               return new ModeratorsDataAccess().GetModerator(m_id);
    }

	
	}
	
	
	
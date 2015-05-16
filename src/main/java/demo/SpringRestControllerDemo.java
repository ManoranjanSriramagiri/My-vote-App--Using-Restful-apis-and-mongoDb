package demo;



	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.bind.annotation.ResponseStatus;
	import org.springframework.web.bind.annotation.RestController;


public class SpringRestControllerDemo {
	    @Autowired UserDetails userDetails;
	    @RequestMapping(value="/springcontent",
	            method=RequestMethod.GET,produces={"application/xml", "application/json"})
	    @ResponseStatus(HttpStatus.OK)
	    public UserDetails getUser() {
	        UserDetails userDetails = new UserDetails();
	        userDetails.setUserName("Krishna");
	        userDetails.setEmailId("krishna@gmail.com");
	        return userDetails;
	    }
	 
	    @RequestMapping(value="/springcontent", method=RequestMethod.GET)
	    @ResponseStatus(HttpStatus.OK)
	    public String getUserHtml() {
	        //Test HTML view
	        return "example";
	    }
	}

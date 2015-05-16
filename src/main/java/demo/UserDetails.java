package demo;

import javax.xml.bind.annotation.XmlAttribute;
	import javax.xml.bind.annotation.XmlRootElement;
	@XmlRootElement
	public class UserDetails {
	    private String userName;
	    private String emailId;
	 
	    @XmlAttribute
	    public String getUserName() {
	        return userName;
	    }
	    public void setUserName(String userName) {
	        this.userName = userName;
	    }
	 
	    @XmlAttribute
	    public String getEmailId() {
	        return emailId;
	    }
	    public void setEmailId(String emailId) {
	        this.emailId = emailId;
	    }
	}

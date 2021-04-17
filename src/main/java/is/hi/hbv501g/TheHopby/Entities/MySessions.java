package is.hi.hbv501g.TheHopby.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "sessions_users")
public class MySessions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String users_user_name;
	
	private long session_id;
	
    public String getName() {
    	return users_user_name;
    }
    
    public void setName(String name) {
    	users_user_name = name;
    }
    public long getSession() {
    	return session_id;
    }
    
    public void setSessionId(Long id) {
    	session_id = id;
    }
}

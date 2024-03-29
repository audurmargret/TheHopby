package is.hi.hbv501g.TheHopby.Entities;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    private String userName;

    private String password;
    
    private String name;


    public User(){
    }

    public User(String userName, String password, String name) {
        this.userName = userName;
        this.password = password;
        this.name = name;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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


}

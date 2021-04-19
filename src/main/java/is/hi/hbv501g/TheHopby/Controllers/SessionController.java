package is.hi.hbv501g.TheHopby.Controllers;


import is.hi.hbv501g.TheHopby.Entities.Hobby;
import is.hi.hbv501g.TheHopby.Entities.Session;
import is.hi.hbv501g.TheHopby.Entities.User;
import is.hi.hbv501g.TheHopby.Services.HopbyService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class SessionController {

    private HopbyService hopbyService;

    @Autowired
    public SessionController(HopbyService hService) {
        hopbyService = hService;
    }


    @RequestMapping(value = "/hobby/addSession", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public Session addHobby(String title, String date, int time, int slots, int hobbyId, String description, String location, String username) {

    	String stringTime = Integer.toString(time);
    	if(stringTime.length() == 3) {
    		String zero = "0";
    		stringTime = zero.concat(stringTime);
    	}
    	stringTime = stringTime.substring(0,2).concat(":").concat(stringTime.substring(2,4));
    	LocalDate localDate = LocalDate.parse(date);
    	LocalTime localTime = LocalTime.parse(stringTime);
    	
    	Session newSession = new Session(title, location, localDate, localTime, slots, hobbyId, description, username);
    	hopbyService.save(newSession);
    	joinSessionREST(newSession.getId(), username);
    	
    	return newSession;
    }

    @RequestMapping(value = "/openSession/{id}", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public Session openSession(@PathVariable("id") long id) {

        Session session = hopbyService.findSessionById(id);
        return session;
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces="application/json;charset=UTF-8")
    public String deleteSession(@PathVariable("id") long id) {

        hopbyService.delete(hopbyService.findSessionById(id));
        return "SessionOverview";
    }
        
    
    @RequestMapping(value = "/joinSession/{id}/{username}", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public Session joinSessionREST(@PathVariable("id") long id, @PathVariable("username") String username) {

    	User user = hopbyService.findByUserName(username);
        hopbyService.joinSession(id, user);

        Session session = hopbyService.findSessionById(id);
        return session;
    }

    @RequestMapping(value= "/leaveSession/{id}/{username}", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public Session leaveSession(@PathVariable("id") long id, @PathVariable("username") String username) {
    	
        hopbyService.leaveSession(id, hopbyService.findByUserName(username));

        Session session = hopbyService.findSessionById(id);
        return session;


    }

    public boolean userInSession(User user, Session session){
        for (int i=0; i<session.getUsers().size();i++){
            if(session.getUsers().get(i).getUserName().equals(user.getUserName())){
                System.out.println("skila true");
                return true;
            }
        }
        return false;
    }

}

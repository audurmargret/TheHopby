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

    @RequestMapping(value="/hobby/addSession", method = RequestMethod.GET)
    public String addSessionForm(Model model, HttpSession hSession){
        User loggedInUser = (User) hSession.getAttribute("LoggedInUser");
        if(loggedInUser == null ){
            return "redirect:/";
        }
        model.addAttribute("sessions", new Session());


        return "AddSession";
    }


    @RequestMapping(value = "/hobby/addSession", method = RequestMethod.POST)
    public Session addHobby(String title, String date, int time, int slots, int hobbyId, String description, String location) {

    	String stringTime = Integer.toString(time);
    	stringTime = stringTime.substring(0,2).concat(":").concat(stringTime.substring(2,4));
    	LocalDate localDate = LocalDate.parse(date);
    	LocalTime localTime = LocalTime.parse(stringTime);
    	
    	Session newSession = new Session(title, location, localDate, localTime, slots, hobbyId, description);
    	hopbyService.save(newSession);
    	return newSession;
    }

    @RequestMapping(value = "/openSession/{id}", method = RequestMethod.GET)
    public Session openSession(@PathVariable("id") long id, HttpSession hSession, Model model) {

        Session session = hopbyService.findSessionById(id);
        return session;
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteSession(@PathVariable("id") long id, HttpSession hSession, Model model) {
        /*User loggedInUser = (User) hSession.getAttribute("LoggedInUser");
        if(loggedInUser == null ){
            return "redirect:/";
        }
*/
        hopbyService.delete(hopbyService.findSessionById(id));
        model.addAttribute("sessions", hopbyService.findAllSession());
        return "SessionOverview";
    }
    
    @RequestMapping(value="/deleteAll/{id}", method = RequestMethod.DELETE)
    public String deleteAllSession(@PathVariable("id") long id) {
    	for (int i =  0; i<id; i++) {
    		hopbyService.delete(hopbyService.findSessionById(id+1));
    	}
    	return "Success";
    }
    
    
    @RequestMapping(value = "/joinSession/{id}/{username}", method = RequestMethod.GET)
    public Session joinSession(@PathVariable("id") long id, @PathVariable("username") String username, HttpSession hSession, Model model) {

    	User user = hopbyService.findByUserName(username);
        hopbyService.joinSession(id, user);

        Session session = hopbyService.findSessionById(id);
        return session;
    }

    @RequestMapping(value= "/leaveSession/{id}/{username}", method = RequestMethod.GET)
    public Session leaveSession(@PathVariable("id") long id, @PathVariable("username") String username, HttpSession hSession, Model model) {
    	
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

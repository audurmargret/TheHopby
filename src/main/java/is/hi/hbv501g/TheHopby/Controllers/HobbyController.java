package is.hi.hbv501g.TheHopby.Controllers;

import is.hi.hbv501g.TheHopby.Entities.Hobby;
import is.hi.hbv501g.TheHopby.Entities.Session;
import is.hi.hbv501g.TheHopby.Entities.User;
import is.hi.hbv501g.TheHopby.Services.HopbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
public class HobbyController {

    private HopbyService hopbyService;


    @Autowired
    public HobbyController(HopbyService hService) {
        hopbyService = hService;
    }

    @RequestMapping("/")
    public String index(Model model) {

        return "Index";
    }

    @RequestMapping("/home")
    public List<Hobby> home(Model model, HttpSession hSession){
        return hopbyService.findAllHobby();
    }


    @RequestMapping(value = "/hobby/{id}", method = RequestMethod.GET)
    public List<Session> goToSessions(@PathVariable("id") long id, HttpSession hSession, Model model) {
        return hopbyService.findSessionByHobby(id);

    }

    
    @RequestMapping(value="/hobby/all", method = RequestMethod.GET)
    public List<Session> goToAllSessions(Model model, HttpSession hSession){
        return hopbyService.findAllSession();
    }
    
    @RequestMapping(value="/mySessions/{username}", method = RequestMethod.GET)
    public List<Session> getMySessions(@PathVariable("username") String username) {
    	return hopbyService.findMySessions(username);
    }


}

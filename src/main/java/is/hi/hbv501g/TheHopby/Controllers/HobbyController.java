package is.hi.hbv501g.TheHopby.Controllers;

import is.hi.hbv501g.TheHopby.Entities.Hobby;
import is.hi.hbv501g.TheHopby.Entities.Session;
import is.hi.hbv501g.TheHopby.Entities.User;
import is.hi.hbv501g.TheHopby.Services.HopbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class HobbyController {

    private boolean first = true;
    private HopbyService hopbyService;


    @Autowired
    public HobbyController(HopbyService hService) {
        hopbyService = hService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        if(first) {
           //start();
            /*hopbyService.delete(hopbyService.findSessionById(4));
            hopbyService.delete(hopbyService.findSessionById(8));
            hopbyService.delete(hopbyService.findSessionById(2));
            hopbyService.delete(hopbyService.findSessionById(7));
            hopbyService.delete(hopbyService.findSessionById(5));
            hopbyService.delete(hopbyService.findByUserName("audur"));
            hopbyService.delete(hopbyService.findByUserName("katla"));
            hopbyService.delete(hopbyService.findByUserName("addi"));
            hopbyService.delete(hopbyService.findByUserName("joi"));*/

        }
        return "Index";
    }

    @RequestMapping("/home")
    public String home(Model model, HttpSession hSession){
        User loggedInUser = (User) hSession.getAttribute("LoggedInUser");
        if(loggedInUser == null ){
            return "redirect:/";
        }
        model.addAttribute("hobby", hopbyService.findAllHobby());
        return "HobbyOverview";
    }


    @RequestMapping(value = "/hobby/{id}", method = RequestMethod.GET)
    public String goToSessions(@PathVariable("id") long id, HttpSession hSession, Model model) {
        //System.out.println("HERNA ER EH PRENT " + hopbyService.findSessionByHobby(id).get(0).getTitle());
        User loggedInUser = (User) hSession.getAttribute("LoggedInUser");
        if(loggedInUser == null ){
            return "redirect:/";
        }
        model.addAttribute("sessions", hopbyService.findSessionByHobby(id));
        return "SessionOverview";

    }

    @RequestMapping(value="/hobby/all", method = RequestMethod.GET)
    public String goToAllSessions(Model model, HttpSession hSession){
        User loggedInUser = (User) hSession.getAttribute("LoggedInUser");
        if(loggedInUser == null ){
            return "redirect:/";
        }
        model.addAttribute("sessions", hopbyService.findAllSession());
        return "SessionOverview";
    }

    @RequestMapping("/login")
    public String LoginPage() {
        return "LoginPage";
    }


    private void start() {
        Hobby football = new Hobby("Football", 1);
        Hobby basketball = new Hobby("Basketball", 2);
        Hobby hiking = new Hobby("Hiking", 3);
        hopbyService.save(football);
        hopbyService.save(basketball);
        hopbyService.save(hiking);
        User u1 = new User("audur", "123", "Audur Margret");
        User u2 = new User("katla", "321", "Katla Rún");
        User u3 = new User("addi", "987", "Arnþór");
        User u4 = new User("joi", "789", "Jóhann");
        hopbyService.save(u1);
        hopbyService.save(u2);
        hopbyService.save(u3);
        hopbyService.save(u4);
        Session s1 = new Session("Football boyyys", "Seljaskóli", LocalDate.parse("2020-12-24"), LocalTime.parse("18:00"), 16, 1, "Jólabolti jeij gaman");
        Session s2 = new Session("Bumbubolti", "Breiðholtsskóli", LocalDate.parse("2020-12-01"), LocalTime.parse("21:30"), 20, 2, "Yeee");
        Session s3 = new Session("Georg og félagar", "Ölduselsskóli", LocalDate.parse("2020-11-30"), LocalTime.parse("21:00"), 8, 1, "Vinsælir alls staðar");
        Session s4 = new Session("Pink Ladies", "Hólabrekkuskóli", LocalDate.parse("2020-11-28"), LocalTime.parse("17:00"), 10, 2, "Bara stuð");
        Session s5 = new Session("Fjallageitur", "Esjan", LocalDate.parse("2020-12-31"), LocalTime.parse("14:00"), 12, 3, "Röltum Esjuna á Gamlársdag");
        hopbyService.save(s1);
        hopbyService.save(s2);
        hopbyService.save(s3);
        hopbyService.save(s4);
        hopbyService.save(s5);


        first = false;
    }
}

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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
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
    public String addHobby(@Valid @ModelAttribute("sessions") Session session, BindingResult result, @RequestParam String action, HttpSession hSession, Model model) {
        User loggedInUser = (User) hSession.getAttribute("LoggedInUser");
        if(loggedInUser == null ){
            return "redirect:/";
        }
        if(action.equals("Cancel")){
            return "redirect:/hobby/all";
        }
        if (result.hasErrors()) {
            if(!hopbyService.validTime(session.getDate(), session.getTime())){
                System.out.println("ADD SESSION DAGS I FORTIÐ");
                model.addAttribute("validDate","notValid");
            }
            return "AddSession";
        }
        if(action.equals("Add Session")){
            if(!hopbyService.validTime(session.getDate(), session.getTime())){
                System.out.println("ADD SESSION DAGS I FORTIÐ");
                model.addAttribute("validDate","notValid");
                return "AddSession";
            }
            session.setSlotsAvailable(session.getSlots());
            hopbyService.save(session);
        }
        return "redirect:/joinSession/" + session.getId();
    }

    @RequestMapping(value = "/openSession/{id}", method = RequestMethod.GET)
    public String openSession(@PathVariable("id") long id, HttpSession hSession, Model model) {
        User loggedInUser = (User) hSession.getAttribute("LoggedInUser");
        if(loggedInUser == null ){
            return "redirect:/";
        }
        Session session = hopbyService.findSessionById(id);
        model.addAttribute("sessions", session);
        boolean joined = false;

        // Leyfir fyrsta user að delete sessioni
        if(!session.getUsers().isEmpty() && session.getUsers().get(0).getUserName().equals(loggedInUser.getUserName())){
            System.out.println("hér");
            model.addAttribute("host", "first");
            joined = true;
        }
        else {
            model.addAttribute("host", "");
        }

        // Takki birtist sem join eða leave
        if(joined || userInSession(loggedInUser, session)){
            model.addAttribute("joined", "joined");
        }
        else if(session.getSlotsAvailable()<1){
            model.addAttribute("joined", "full");
        }
        else{
            model.addAttribute("joined", "notJoined");
        }
        return "ViewSession";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteSession(@PathVariable("id") long id, HttpSession hSession, Model model) {
        User loggedInUser = (User) hSession.getAttribute("LoggedInUser");
        if(loggedInUser == null ){
            return "redirect:/";
        }

        hopbyService.delete(hopbyService.findSessionById(id));
        model.addAttribute("sessions", hopbyService.findAllSession());
        return "SessionOverview";
    }

    @RequestMapping(value = "/joinSession/{id}", method = RequestMethod.GET)
    public String joinSession(@PathVariable("id") long id,HttpSession hSession, Model model) {
        User loggedInUser = (User) hSession.getAttribute("LoggedInUser");
        if(loggedInUser == null ){
            return "redirect:/";
        }
        hopbyService.joinSession(id, loggedInUser);
        model.addAttribute("sessions", hopbyService.findSessionById(id));
        model.addAttribute("joined", "leave");

        return "redirect:/openSession/" + id;
    }

    @RequestMapping(value= "/leaveSession/{id}", method = RequestMethod.GET)
    public String leaveSession(@PathVariable("id") long id, HttpSession hSession, Model model) {
        User loggedInUser = (User) hSession.getAttribute("LoggedInUser");
        if(loggedInUser == null){
            return "redirect:/";
        }

        hopbyService.leaveSession(id, hopbyService.findByUserName(loggedInUser.getUserName()));
        model.addAttribute("sessions", hopbyService.findSessionById(id));
        model.addAttribute("joined", "joined");
        return "redirect:/openSession/" + id;


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

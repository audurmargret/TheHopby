package is.hi.hbv501g.TheHopby.Controllers;


import is.hi.hbv501g.TheHopby.Entities.Hobby;
import is.hi.hbv501g.TheHopby.Entities.Session;
import is.hi.hbv501g.TheHopby.Entities.User;
import is.hi.hbv501g.TheHopby.Services.HopbyService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String addHobby(@Valid @ModelAttribute("sessions") Session session, @RequestParam String action, BindingResult result, HttpSession hSession, Model model) {
        User loggedInUser = (User) hSession.getAttribute("LoggedInUser");
        if(loggedInUser == null ){
            return "redirect:/";
        }
        if (result.hasErrors()) {
            System.out.println("halló");
            System.out.println(result.getFieldError());
            return "AddSession";
        }
        if(action.equals("Cancel")){
            System.out.println("CANCEL");
        }
        if(action.equals("Add Session")){
            System.out.println("ACTION " + action);
            session.setSlotsAvailable(session.getSlots());

            hopbyService.save(session);
            model.addAttribute("sessions", hopbyService.findAllSession());
        }
        return "redirect:/hobby/" + session.getHobbyId();
    }


    @RequestMapping(value = "/openSession/{id}", method = RequestMethod.GET)
    public String openSession(@PathVariable("id") long id, HttpSession hSession, Model model) {
        User loggedInUser = (User) hSession.getAttribute("LoggedInUser");
        if(loggedInUser == null ){
            return "redirect:/";
        }
        Session session = hopbyService.findSessionById(id);
        model.addAttribute("sessions", session);

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

        User user = (User) hSession.getAttribute("LoggedInUser");
        hopbyService.joinSession(id, user);
        model.addAttribute("sessions", hopbyService.findAllSession());

        return "SessionOverview";
    }


}

package is.hi.hbv501g.TheHopby.Controllers;

import is.hi.hbv501g.TheHopby.Entities.Hobby;
import is.hi.hbv501g.TheHopby.Entities.Session;
import is.hi.hbv501g.TheHopby.Services.HopbyService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class SessionController {

    private HopbyService hopbyService;

    @Autowired
    public SessionController(HopbyService hService) {
        hopbyService = hService;
    }

    @RequestMapping(value="/hobby/addSession", method = RequestMethod.GET)
    public String addSessionForm(Model model){
        model.addAttribute("sessions", new Session());
        return "AddSession";
    }

    @RequestMapping(value = "/hobby/addSession", method = RequestMethod.POST)
    public String addHobby(@Valid Session session, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("halló");
            return "AddSession";
        }
        hopbyService.save(session);
        System.out.println(session);
        System.out.println(session.getTitle() + session.getDescription() + session.getLocation() + session.getHobbyId());
        System.out.println(hopbyService.findSessionByHobby(1));
        model.addAttribute("sessions", hopbyService.findSessionByHobby(1));
        return "SessionOverview";
    }


}

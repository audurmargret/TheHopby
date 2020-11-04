package is.hi.hbv501g.TheHopby.Controllers;


import is.hi.hbv501g.TheHopby.Entities.Hobby;
import is.hi.hbv501g.TheHopby.Entities.Session;
import is.hi.hbv501g.TheHopby.Services.HopbyService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    // Laga til að fara til baka á rétta síðu!
    @RequestMapping(value = "/hobby/addSession", method = RequestMethod.POST)
    public String addHobby(@Valid @ModelAttribute("sessions") Session session, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("halló");
            System.out.println(result.getFieldError());
            return "AddSession";
        }
        hopbyService.save(session);
        model.addAttribute("sessions", hopbyService.findAllSession());
        return "SessionOverview";
    }

    // Laga til að fara til baka á rétta síðu!!
    @RequestMapping(value = "/openSession/{id}", method = RequestMethod.GET)
    public String deleteSession(@PathVariable("id") long id, Model model) {
        System.out.println("session: " + hopbyService.findSessionById(id));
        Session session = hopbyService.findSessionById(id);
        model.addAttribute("sessions", session);

        //Hobby hobby = hopbyService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid hobby id"));
        //hopbyService.delete(hobby);
        //model.addAttribute("hobby", hopbyService.findAllHobby());
        return "SessionOverview";
    }


}

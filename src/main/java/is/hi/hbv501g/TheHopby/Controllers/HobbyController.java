package is.hi.hbv501g.TheHopby.Controllers;

import is.hi.hbv501g.TheHopby.Entities.Hobby;
import is.hi.hbv501g.TheHopby.Entities.Session;
import is.hi.hbv501g.TheHopby.Services.HopbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

@Controller
public class HobbyController<s1> {

    private boolean fyrsta = true;
    private HopbyService hopbyService;

    @Autowired
    public HobbyController(HopbyService hService) {
        hopbyService = hService;
    }

    @RequestMapping("/")
    public String Home(Model model) {
        if(fyrsta) {
            upphaf();
        }
        model.addAttribute("hobby", hopbyService.findAllHobby());
        return "Velkomin";
    }

    @RequestMapping(value = "/addhobby", method = RequestMethod.POST)
    public String addHobby(@Valid Hobby hobby, BindingResult result, Model model) {
        System.out.println(hobby.getName());
        if (result.hasErrors()) {
            return "add-hobby";
        }
        hopbyService.save(hobby);
        model.addAttribute("hobby", hopbyService.findAllHobby());
        return "Velkomin";
    }

    @RequestMapping(value = "/hobby/{id}", method = RequestMethod.GET)
    public String goToSessions(@PathVariable("id") long id, Model model) {
        System.out.println("HERNA ER EH PRENT " + hopbyService.findSessionByHobby(id).get(0).getTitle());
        model.addAttribute("sessions", hopbyService.findSessionByHobby(id));
        return "SessionOverview";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteHobby(@PathVariable("id") long id, Model model) {
        Hobby hobby = hopbyService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid hobby id"));
        hopbyService.delete(hobby);
        model.addAttribute("hobby", hopbyService.findAllHobby());
        return "Velkomin";
    }

    @RequestMapping("/login")
    public String LoginPage() {
        return "LoginPage";
    }


    private void upphaf() {
        Hobby football = new Hobby("Football", 1);
        Hobby basketball = new Hobby("Basketball", 2);
        Hobby hiking = new Hobby("Hiking", 3);
        hopbyService.save(football);
        hopbyService.save(basketball);
        hopbyService.save(hiking);
        Session s1 = new Session("Football boyyys", "Seljaskóli", LocalDate.parse("2020-12-24"), LocalTime.parse("18:00"), 10, 1, "Jólabolti jeij gaman");
        Session s2 = new Session("Bumbubolti", "Breiðholtsskóli", LocalDate.parse("2020-11-23"), LocalTime.parse("21:30"), 10, 2, "Yeee");
        Session s3 = new Session("Georg og félagar", "Ölduselsskóli", LocalDate.parse("2020-11-12"), LocalTime.parse("21:00"), 10, 1, "Vinsælir alls staðar");
        Session s4 = new Session("Pink Ladies", "Hólabrekkuskóli", LocalDate.parse("2020-11-28"), LocalTime.parse("17:00"), 10, 2, "Bara stuð");
        Session s5 = new Session("Fjallageitur", "Esjan", LocalDate.parse("2020-12-31"), LocalTime.parse("14:00"), 10, 3, "Röltum Esjuna á Gamlársdag");
        hopbyService.save(s1);
        hopbyService.save(s2);
        hopbyService.save(s3);
        hopbyService.save(s4);
        hopbyService.save(s5);
        fyrsta = false;
    }
}

package is.hi.hbv501g.TheHopby.Controllers;

import is.hi.hbv501g.TheHopby.Services.HopbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HobbyController {

    private HopbyService hopbyService;

    @Autowired
    public HobbyController(HopbyService hService){
        hopbyService = hService;
    }

    @RequestMapping("/")
    public String Home(Model model) {
        return "Velkomin";
    }


    @RequestMapping("/login")
    public String LoginPage(){
        return "LoginPage";
    }

}

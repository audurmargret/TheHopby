package is.hi.hbv501g.TheHopby.Controllers;

import is.hi.hbv501g.TheHopby.Entities.User;
import is.hi.hbv501g.TheHopby.Services.HopbyService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    private HopbyService hopbyService;

    @Autowired
    public UserController(HopbyService hService) {
        hopbyService = hService;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(User user){
        return "LoginPage";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(@Valid User user, BindingResult result, Model model, HttpSession hSession){
        if(result.hasErrors()){
            return "LoginPage";
        }
        model.addAttribute("hobby",hopbyService.findAllHobby());
        User exists = hopbyService.login(user);

        if(exists != null){
            hSession.setAttribute("LoggedInUser", user);
            System.out.println("LOGIN SESSION: " + hSession.getAttribute("LoggedInUser"));

            return "HobbyOverview";
        }

        return "LoginPage";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpSession hSession){
        if(hSession == null) {
            return "redirect:/";
        }
        hSession.removeAttribute("LoggedInUser");
        return "redirect:/";

    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupGET(User user) {
        return "SignupPage";
    }


    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public String signupPOST(@Valid User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "SignupPage";
        }
        User exist = hopbyService.findByUserName(user.getUserName());
        if(exist == null) {
            hopbyService.save(user);
        }

        model.addAttribute("hobby", hopbyService.findAllHobby());
        return "LoginPage";
    }

    @RequestMapping(value= "/users", method = RequestMethod.GET)
    public String usersGET(Model model){
        model.addAttribute("users", hopbyService.findAll());
        return "Users";
    }

    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    public String loggedinGET(HttpSession session, Model model){
        model.addAttribute("movies",hopbyService.findAll());
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        System.out.println("LOGGED IN: " + sessionUser);
        if(sessionUser  != null){
            model.addAttribute("loggedinuser", sessionUser);
            return "loggedInUser";
        }
        return "redirect:/";
    }
}




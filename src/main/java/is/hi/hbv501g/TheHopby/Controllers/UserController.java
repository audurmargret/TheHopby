package is.hi.hbv501g.TheHopby.Controllers;

import is.hi.hbv501g.TheHopby.Entities.User;
import is.hi.hbv501g.TheHopby.Services.HopbyService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
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
    public User loginPOST(@Valid User user, BindingResult result, Model model, HttpSession hSession){
        /*if(result.hasErrors()){
            return result.getAllErrors().toString();
        }*/
        model.addAttribute("hobby",hopbyService.findAllHobby());
        User exists = hopbyService.login(user);
        if(exists != null) {
        	System.out.print(exists.getUserName());
        	return exists;        	
        }
        
        return null;
/*
        if(exists != null){
            hSession.setAttribute("LoggedInUser", user);
            System.out.println("LOGIN SESSION: " + hSession.getAttribute("LoggedInUser"));
            model.addAttribute("error","");
            return "HobbyOverview";
        }
        model.addAttribute("error", "wrong");
        return "LoginPage";*/
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
    public User signupPOST(String name, String userName, String password){
    	User user = new User(userName, password);
        hopbyService.save(user);

        return user;
    }

    @RequestMapping(value= "/users", method = RequestMethod.GET)
    public List<User> usersGET(Model model){
        model.addAttribute("users", hopbyService.findAll());
        
        return hopbyService.findAll();
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




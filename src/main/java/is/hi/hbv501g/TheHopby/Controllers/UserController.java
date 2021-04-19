package is.hi.hbv501g.TheHopby.Controllers;

import is.hi.hbv501g.TheHopby.Entities.User;
import is.hi.hbv501g.TheHopby.Services.HopbyService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
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



    @RequestMapping(value = "/deleteUser/{username}", method = RequestMethod.DELETE, produces="application/json;charset=UTF-8")
    public String deleteSession(@PathVariable("username") String username, HttpSession hSession, Model model) {
        
        hopbyService.delete(hopbyService.findByUserName(username));
        return "Success";
    }
    

    @RequestMapping(value="/signup", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public User signupPOST(String name, String userName, String password){
    	User user = new User(userName, password, name);
        hopbyService.save(user);
        return user;
    }

    @RequestMapping(value= "/users", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public List<User> usersGET(Model model){        
        return hopbyService.findAll();
    }

}




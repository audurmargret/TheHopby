package is.hi.hbv501g.TheHopby.Controllers;

import is.hi.hbv501g.TheHopby.Entities.Hobby;
import is.hi.hbv501g.TheHopby.Services.HopbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class HobbyController {

    private HopbyService hopbyService;

    @Autowired
    public HobbyController(HopbyService hService){
        hopbyService = hService;
    }

    @RequestMapping("/")
    public String Home(Model model) {
        Hobby football = new Hobby("Football");
        Hobby basketball = new Hobby("Basketball");
        Hobby hiking = new Hobby("Hiking");
        hopbyService.save(football);
        hopbyService.save(basketball);
        hopbyService.save(hiking);
        model.addAttribute("hobby", hopbyService.findAllHobby());
        return "Velkomin";
    }

    @RequestMapping(value="/addhobby", method= RequestMethod.POST)
    public String addHobby(@Valid Hobby hobby, BindingResult result, Model model) {
        System.out.println(hobby.getName());
        if(result.hasErrors()){
            return "add-hobby";
        }
        hopbyService.save(hobby);
        model.addAttribute("hobby", hopbyService.findAllHobby());
        return "Velkomin";
    }

    @RequestMapping(value="/addhobby", method = RequestMethod.GET)
    public String addHobbyForm(Model model){
        model.addAttribute("hobby", new Hobby());
        return "add-hobby";
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteHobby(@PathVariable("id") long id, Model model) {
        Hobby hobby = hopbyService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid hobby id"));
        hopbyService.delete(hobby);
        model.addAttribute("hobby", hopbyService.findAllHobby());
        return "Velkomin";
    }

    @RequestMapping("/login")
    public String LoginPage(){
        return "LoginPage";
    }

}

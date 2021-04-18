package assignment.controller;

import assignment.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Maria
 */

@Controller
@RequestMapping("/")
public class HomeController {
    
    @Autowired
    private HomeService hs;
    

      @RequestMapping
    public String getWelcomeMessage(Model model){
        String message = hs.getWelcomeMessage();
        model.addAttribute("message", message);
        return "home";
    }
    
}

package assignment.controller;

import assignment.entity.Trainer;
import assignment.service.TrainerService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Maria
 */
@Controller
@RequestMapping("/trainer")
public class TrainerController {

    @Autowired
    private TrainerService ts;

    @RequestMapping
    public ModelAndView showTrainer(ModelAndView myModelAndView) {
        List<Trainer> trainers = ts.getAllTrainers();
        myModelAndView.addObject("listOfTrainers", trainers);
        myModelAndView.setViewName("trainerList");
        return myModelAndView;
    }
   
    @GetMapping("/create")
    public String showTrainerForm() {
        return "trainerForm";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("editTrainer") Trainer t,
            BindingResult result,
            RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "trainerForm";
        }
        ts.updateTrainer(t);
        String minima = "Trainer created successfully!";
        attributes.addFlashAttribute("message", minima);
        return "redirect:/trainer";
    }

    @GetMapping("/update/{idTrainer}")
    public String showFormUpdate(@PathVariable("idTrainer") int id, Model model) {
        Trainer t = ts.getTrainerById(id);
        model.addAttribute("editTrainer", t);
        return "trainerForm";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("editTrainer") Trainer t,
            BindingResult result,
            RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "trainerForm";
        }
        ts.updateTrainer(t);
        String minima = "Trainer updated successfully!";
        attributes.addFlashAttribute("message", minima);
        return "redirect:/trainer";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id, RedirectAttributes attributes) {
        ts.deleteTrainerById(id);
        String minima = "Trainer deleted successfully!";
        attributes.addFlashAttribute("message", minima);
        return "redirect:/trainer";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationException(RedirectAttributes attributes) {
        String minima = "Please enter 1 to 25 characters.";
        attributes.addFlashAttribute("message", minima);
        return "redirect:/trainer";
    }

}

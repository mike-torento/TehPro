package webLogic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class test {

    @RequestMapping(value = "/r")
    public ModelAndView testhtml(){

        return new ModelAndView("/resources/registration.html");
    }

    @RequestMapping(value = "/")
    public String lal(){

        return "redirect:/resources/index.html";
    }


    @RequestMapping(value = "/auth")
    public  ModelAndView testtt(){
        return new ModelAndView("/resources/authentification.html");
    }


}

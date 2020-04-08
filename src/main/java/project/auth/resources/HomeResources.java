package project.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping
public class HomeResources {

    @RequestMapping("/")
    public String home(){
        return "Hello World!";
    }

    @RequestMapping("/users")
    public String user() {
        return "Elo";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "Witam";
    }

    @RequestMapping("/login")
    public String login() {
        return "Login";
    }


}
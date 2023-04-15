package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home() {
    	
    	System.out.println("HomeController");
    	return "home";
    }
    
//	private final WelcomeClient welcomeClient;
//	
//    @GetMapping("/")
//    public String home() {
//      
//    	String welcome = welcomeClient.getWelcome();
//    	return "<h1>" + welcome + "</h1>";
//    }

  
}
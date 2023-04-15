package tacos;


import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;


@HttpExchange("http://localhost:9090")
public interface WelcomeClient {

	@GetExchange("/")
	String getWelcome();
	
	
}
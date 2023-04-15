package tacos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

	private final RestTemplate restTemplate;
	
	public Controller(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	@GetMapping("/test")
	public Object getApi() {
		
		String url = "http://localhost:8086/api/ingredients";
		return restTemplate.getForObject(url, Object.class);
	}
}

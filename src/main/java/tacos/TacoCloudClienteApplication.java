package tacos;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class TacoCloudClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudClienteApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public CommandLineRunner fetchIngredients() {
		return args -> {
			log.info("----------------------- GET -------------------------");
			log.info("GETTING INGREDIENT BY ID");

//			-----FORMA 1
			
			Ingredient ing = restTemplate().getForObject("http://localhost:8086/api/ingredients/{id}", Ingredient.class, "CHED");
			log.info("Ingredient FORMA 1:  " + ing);

//			----- FORMA 2
			
			Map<String, String> urlVariables = new HashMap<>();
			urlVariables.put("id", "CHED");
			Ingredient ing2 = restTemplate().getForObject("http://localhost:8086/api/ingredients/{id}",Ingredient.class, urlVariables);	
			log.info("Ingredient FORMA 2:  " + ing2);
			
//			-----FORMA 3
			
			Map<String, String> urlVariables2 = new HashMap<>();
			urlVariables2.put("id", "CHED");
			URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8086/api/ingredients/{id}").build(urlVariables2);
			Ingredient ing3 = restTemplate().getForObject(uri, Ingredient.class);
			log.info("Ingredient FORMA 3:  " + ing3);
			
//			-----FORMA 4
			
			ResponseEntity<Ingredient> responseEntity =
					restTemplate().getForEntity("http://localhost:8086/api/ingredients/{id}",
					Ingredient.class, "CHED");
					log.info("\tFetched time: " + responseEntity.getHeaders().getDate());
			Ingredient ing4 = responseEntity.getBody();
			log.info("Ingredient FORMA 4:  " + ing4);
			log.info("");
					
//			-----
			
			LinkedHashMap<String, Ingredient> ingredients = new LinkedHashMap<String,Ingredient>();
			String url = "http://localhost:8086/api/ingredients";
			ingredients = (LinkedHashMap<String, Ingredient>) restTemplate().getForObject(url, LinkedHashMap.class);
			log.info("All ingredients:" + ingredients);
			
//			log.info("----------------------- PUT -------------------------");
//			
//			Ingredient oIng = new Ingredient("FLTO","Alcachofas", Type.WRAP); 
//			restTemplate().put("http://localhost:8086/api/ingredients/{id}", oIng, oIng.getId());
//
//			Ingredient ing = restTemplate().getForObject("http://localhost:8086/api/ingredients/{id}", Ingredient.class, "FLTO");
//			log.info("Ingrediente modificado:  " + ing + "\n");
//			
//			log.info("----------------------- DELETE -------------------------");
//			
//			restTemplate().delete("http://localhost:8086/api/ingredients/{id}","FLTO");
//			
//			LinkedHashMap<String, Ingredient> ingredients = new LinkedHashMap<String,Ingredient>();
//			String url = "http://localhost:8086/api/ingredients";
//			ingredients = (LinkedHashMap<String, Ingredient>) restTemplate().getForObject(url, LinkedHashMap.class);
//			log.info("All ingredients:" + ingredients);

			
//			log.info("----------------------- POST -------------------------");
//			
////			-----FORMA 1
//			
//			Ingredient oIng = new Ingredient("ALCF","Alcachofas", Type.WRAP);
//			Ingredient nuevoIng = restTemplate().postForObject("http://localhost:8086/api/ingredients", oIng, Ingredient.class);
//			log.info("nuevoIng:" + nuevoIng);
//			
//			LinkedHashMap<String, Ingredient> ingredients = new LinkedHashMap<String,Ingredient>();
//			String url = "http://localhost:8086/api/ingredients";
//			ingredients = (LinkedHashMap<String, Ingredient>) restTemplate().getForObject(url, LinkedHashMap.class);
//			log.info("All ingredients:" + ingredients);
//			
////			-----FORMA 2			
//			
//			Ingredient oIng2 = new Ingredient("ALCF","Alcachofas", Type.WRAP);
//			URI uri = restTemplate().postForLocation("http://localhost:8086/api/ingredients",oIng2);
//			log.info("URI: " + uri + "\n");
//			
////			-----FORMA 3						
//			
//			Ingredient oIng3 = new Ingredient("ALCF","Alcachofas", Type.WRAP);
//			ResponseEntity<Ingredient> responseEntity = restTemplate().postForEntity("http://localhost:8086/api/ingredients", oIng3, Ingredient.class);
//			log.info("New resource created at " + responseEntity.getHeaders().getLocation());
//			Ingredient ing = responseEntity.getBody();
//			log.info("Ingredient: " + ing + "\n");
			
//			log.info("----------------------- TRAVERSON -------------------------");
			
//			-----Ejemplo 1
//			
//			Traverson traverson = new Traverson(URI.create("http://localhost:8086/api"), MediaTypes.HAL_JSON);
//			
//			ParameterizedTypeReference<CollectionModel<Ingredient>> ingredientType = new ParameterizedTypeReference<CollectionModel<Ingredient>>() {};
//			CollectionModel<Ingredient> ingredientRes = traverson.follow("ingredients").toObject(ingredientType);
//			Collection<Ingredient> ingredients = ingredientRes.getContent();
//			log.info("Ingredientes: " + ingredients + "\n");

			
//			Ejemplo 2
			
//			Traverson traverson = new Traverson(URI.create("http://localhost:8086/api"), MediaTypes.HAL_JSON);
			
//			Ingredient oIng3 = new Ingredient("ACLG","Acelgas", Type.WRAP);
//			String ingredientsUrl = traverson.follow("ingredients").asLink().getHref();
//			restTemplate().postForObject(ingredientsUrl, oIng3,	Ingredient.class);
					
		};
	}
}

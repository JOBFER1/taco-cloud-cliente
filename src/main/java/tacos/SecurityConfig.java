package tacos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
//@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		System.out.println("SecurityFilterChain 3");
		http
			 .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
			 .oauth2Login(oauth2Login -> oauth2Login.loginPage("/oauth2/authorization/taco-admin-client"))
			 .oauth2Client(Customizer.withDefaults());
		 
		 return http.build();
	}
	
	@Bean
	@RequestScope
	public IngredientService ingredientService(OAuth2AuthorizedClientService clientService) {
		
		System.out.println("clientService: " + clientService);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String accessToken = null;
		
		if (authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
			
			OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
			String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
			
			System.out.println("clientRegistrationId: " + clientRegistrationId);
			
			if (clientRegistrationId.equals("taco-admin-client")) {
				OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(clientRegistrationId, oauthToken.getName());
				accessToken = client.getAccessToken().getTokenValue();
				System.out.println("accessToken: " + accessToken);
			}
		}
		return new RestIngredientService(accessToken);
	}
}

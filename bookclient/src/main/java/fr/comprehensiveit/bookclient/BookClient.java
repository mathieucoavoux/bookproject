package fr.comprehensiveit.bookclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class BookClient {
	
	public String testUrl = "http://localhost:8080/bookprovider/api/book/searchByTitle?title=cambodia";

	public String getBook() {
		String authString = "test:test";
		String authStringEnc = new String(Base64.encodeBase64(authString.getBytes()));
		//Passage des de l'authentification dans l'entête 
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + authStringEnc);
		//Le client informe le serveur qu'il peut recevoir du JSON
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		RestTemplate restTemplate = new RestTemplate();
		//Envoie de l'header au serveur
		HttpEntity<String> request = new HttpEntity<String>(headers);
		//Prise de la réponse 
		ResponseEntity<?> response = restTemplate.exchange(testUrl, HttpMethod.GET, request, List.class);
		//Cast le contenu de la réponse en string	 
		String result = (String) response.getBody();
		return result;
		
	}
	
	
	public String getBookJersey() {
		//Création d'un nouveau client Jersey
		Client client = Client.create();
		//Ajout d'un timeout à 10 000ms
		client.setReadTimeout(10000);
		//Ajout de l'utilisateur et mot de passe
		client.addFilter(new HTTPBasicAuthFilter("test","test"));
		//Création de la resource web
		WebResource webResource = client.resource(testUrl);
		//Spécification au serveur que le client est compatible json
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		//Vérification si la réponse est égal à 200
		if(response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}
		//Prise du contenu de la réponse
		String output = response.getEntity(String.class);
		return output;
	}
}

package rallymate.restclient.webclient;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

public class WebClientEx {
    public static void main(String[] args) {
        Long id = 1L;
        String str = "hello";

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ResponseEntity<Map> response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/example/{id}")
                        .queryParam("str", str)
                        .build(id))
                .retrieve()
                .toEntity(Map.class)
                .block();

        System.out.println("response = " + response);
        System.out.println("body = " + response.getBody());
    }
}
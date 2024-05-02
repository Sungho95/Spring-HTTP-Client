package rallymate.restclient.restclient;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.Map;

public class RestClientEx {
    public static void main(String[] args) {
        Long id = 1L;
        String str = "hello";

        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ResponseEntity<Map> response = restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/example/{id}")
                        .queryParam("str", str)
                        .build(id))
                .retrieve()
                .toEntity(Map.class);

        System.out.println("response = " + response);
        System.out.println("body = " + response.getBody());
    }
}

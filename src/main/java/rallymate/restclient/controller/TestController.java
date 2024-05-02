package rallymate.restclient.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/example/{id}")
    public Map<String, Object> exampleMethod(
            @PathVariable("id") Long id,
            @RequestParam("str") String str
    ) {
        System.out.println("id = " + id);
        System.out.println("str = " + str);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("str", str);

//        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request!");
//        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error!");

        return result;
    }

    @GetMapping("/test")
    public void test() {
        Long id = 1L;
        String str = "hello";

        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new RestClientException("Client error: " + response.getStatusCode());
                })
                .defaultStatusHandler(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new RestClientException("Server error: " + response.getStatusCode());
                })
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

package rallymate.restclient.resttemplate;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

public class RestTemplateEx {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Long id = 1L;
        String str = "hello";
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/example/{id}")
                .queryParam("str", str)
                .buildAndExpand(id)
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        String body = "";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(uri, HttpMethod.GET, entity, Map.class);
        System.out.println("response = " + response);
        System.out.println("body = " + response.getBody());
    }

//    public static void main(String[] args) {
//        String url = "https://open.er-api.com/v6/latest";
//        RestTemplate restTemplate = new RestTemplate();
//
//        String result = restTemplate.getForObject(url, String.class);
//        System.out.println("result = " + result);
//
//        Map<String, Map<String, Double>> resultMap = restTemplate.getForObject(url, Map.class);
//        System.out.println("KRW = " + resultMap.get("rates").get("KRW"));
//
//        WebClient webClient = WebClient.create("https://open.er-api.com");
//        Map<String, Map<String, Double>> resultMap2 = webClient.get()
//                .uri("/v6/latest")
//                .retrieve()
//                .bodyToMono(Map.class)
//                .block();
//
//        System.out.println("resultMap2 = " + resultMap2);
//    }
}

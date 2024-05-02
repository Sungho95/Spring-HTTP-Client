package rallymate.restclient.resttemplate;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rallymate.restclient.product.Product;

import java.util.List;

@Service
public class RestTemplateEx2 {
    private final String baseUrl = "http://localhost:8080";
    private final RestTemplate restTemplate;

    public RestTemplateEx2() {
        restTemplate = new RestTemplate();
    }

    public Product saveNewProduct(Product product) {
        return restTemplate.postForObject(baseUrl + "/products", product, Product.class);
    }

    public List<Product> getAllProducts() {
        ParameterizedTypeReference<List<Product>> typeRef = new ParameterizedTypeReference<>() {};
        return restTemplate.exchange(baseUrl + "/products", HttpMethod.GET, null, typeRef).getBody();
    }

    public Product getProduct(int id) {
        return restTemplate.getForObject(baseUrl + "/products/find/{id}", Product.class, id);
    }

    public Product updateProduct(int id, Product product) {
        restTemplate.put(baseUrl + "/products/{id}", product, id);
        return getProduct(id);
    }

    public String deleteProduct(int id) {
        restTemplate.delete(baseUrl + "/products/{id}", id);
        return "Product removed : " + id;
    }
}

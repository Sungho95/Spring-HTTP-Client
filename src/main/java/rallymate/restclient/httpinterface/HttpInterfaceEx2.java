package rallymate.restclient.httpinterface;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Map;

@Configuration
public class HttpInterfaceEx2 {
    public static void main(String[] args) {
        Long id = 1L;
        String str = "hello";

        RestClient client = RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build();

        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder()
                .exchangeAdapter(RestClientAdapter.create(client))
                .build();

        HttpInterfaceEx httpInterfaceEx = httpServiceProxyFactory.createClient(HttpInterfaceEx.class);
        Map example = httpInterfaceEx.getExample(id, str);
        System.out.println("example = " + example);

    }

    @Bean
    public HttpInterfaceEx getExample() {
        RestClient client = RestClient.create("http://localhost:8080");
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder()
                .exchangeAdapter(RestClientAdapter.create(client))
                .build();

        return httpServiceProxyFactory.createClient(HttpInterfaceEx.class);
    }
}

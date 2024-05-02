package rallymate.restclient.httpinterface;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Map;

@HttpExchange
public interface HttpInterfaceEx {

    @GetExchange("/example/{id}")
    Map getExample(@PathVariable Long id, @RequestParam("str") String str);
}

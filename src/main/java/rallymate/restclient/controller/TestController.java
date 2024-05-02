package rallymate.restclient.controller;

import org.springframework.web.bind.annotation.*;

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

        return result;
    }
}

package org.adscale.scooby.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@ComponentScan(basePackages = "org.adscale.scooby.database")
public class WebController {
    
    @RequestMapping("/")
    public String index() {
        return "Hello World";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET, params = {"account_id", "account_name"})
    public String getAccount() throws IOException {
        return "Not Yet Implemented";
    }

}

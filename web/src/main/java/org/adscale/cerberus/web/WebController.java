package org.adscale.cerberus.web;

import org.adscale.cerberus.creator.AccountCreator;
import org.adscale.cerberus.creator.WebsiteCreator;
import org.adscale.cerberus.model.Account;
import org.adscale.cerberus.model.Website;
import org.adscale.market.vocabulary.identity.AccountId;
import org.adscale.market.vocabulary.identity.WebsiteId;
import org.hibernate.JDBCException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@ComponentScan(basePackages = "org.adscale.cerberus.creator")
public class WebController {

    @Resource
    AccountCreator accountCreator;

    @Resource
    WebsiteCreator websiteCreator;

    
    @RequestMapping("/")
    public String index() {
        return "Hello World";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseEntity createAccount(@RequestBody String postPayload) {
        try {
            AccountId account = accountCreator.createAccount(postPayload);
            return new ResponseEntity<String>("Account successfully created. Id: " + account.stringValue(), HttpStatus.CREATED);
        } catch (JDBCException e) {
            return new ResponseEntity<String>(e.getSQLException().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getAccountJson() throws IOException {
        return Account.getDefaultJson();
    }

    @RequestMapping(value = "/website", method = RequestMethod.POST)
    public ResponseEntity createWebsite(@RequestBody String postPayload) {
        try {
            WebsiteId website = websiteCreator.createWebsite(postPayload);
            return new ResponseEntity<String>("Website successfully created. Id: " + website.stringValue(), HttpStatus.CREATED);
        } catch (JDBCException e) {
            return new ResponseEntity<String>(e.getSQLException().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/website", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getWebsiteJson() throws IOException {
        return Website.getDefaultJson();
    }

}

package com.sbm.keycloack.demo;

import java.security.Principal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.keycloak.KeycloakSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class RestAPIController {
    private static final Logger log = LoggerFactory.getLogger(RestAPIController.class);
    private static final String TAG = "HelloWorldController";
    private static final String template = "Hello, %s! Your id is: %s";
    private final AtomicLong counter = new AtomicLong();


    private final KeycloakSecurityContext securityContext;
    private final CustomerRepository customerRepository;

    public RestAPIController(KeycloakSecurityContext securityContext, CustomerRepository customerRepository) {
        this.securityContext = securityContext;
        this.customerRepository = customerRepository;
    }
    @RequestMapping("/hello-world")
    public @ResponseBody Greeting sayHello(@RequestParam(value="name", required=false, defaultValue="Mohamed ELHOSNI") String name, Principal principal) {
        log.info("Name::"+name);
        log.info("AccessToken: " + securityContext.getTokenString());
        log.info("User: {} / {}", securityContext.getToken().getPreferredUsername(), securityContext.getToken().getName());
        log.info("Principal: {}", principal.getName());
        return new Greeting(counter.incrementAndGet(), String.format(template, name, Optional.ofNullable(securityContext.getTokenString()).orElse("000000000000000")) );
    }

    @RequestMapping("/customers")
    public String customers(Principal principal) {
        log.info("AccessToken: " + securityContext.getTokenString());
        log.info("User: {} / {}", securityContext.getToken().getPreferredUsername(), securityContext.getToken().getName());
        log.info("Principal: {}", principal.getName());

        return customerRepository.findAll().toString();

    }
}

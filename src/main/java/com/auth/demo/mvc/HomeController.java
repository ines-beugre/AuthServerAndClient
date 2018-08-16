package com.auth.demo.mvc;

import com.auth.demo.security.TokenAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@SuppressWarnings("unused")
@Controller
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value(value = "${mode}")
    private String mode;


    @RequestMapping(value = "/portal/home", method = RequestMethod.GET)
    protected String home(final Map<String, Object> model, final Principal principal, TokenAuthentication token) {
        logger.info("Home page");
        if (principal == null) {
            System.out.println("connection en cours");
            return "redirect:/logout";
        }
        System.out.println("token: " + token);
        model.put("userId", principal);
        model.put("reactJsUrl", mode.equalsIgnoreCase("dev") ? "http://localhost:3000/static/js/bundle.js" : "/react/static/js/main.js"); // TODO: fix mode prod
        model.put("reactCssUrl", mode.equalsIgnoreCase("dev") ? "http://localhost:3000/static/css/bundle.css" : "/react/static/css/main.css"); // TODO: fix mode prod
        System.out.println("connecté!!!!");
        return "home";
    }
}
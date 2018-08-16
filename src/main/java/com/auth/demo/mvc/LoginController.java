package com.auth.demo.mvc;


import com.auth.demo.security.AppConfig;
import com.auth0.AuthenticationController;
import com.auth0.AuthorizeUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@SuppressWarnings("unused")
@Controller
public class LoginController {

    @Autowired
    private AuthenticationController controller;
    @Autowired
    private AppConfig appConfig;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    protected String login(final HttpServletRequest req) {
        logger.debug("Performing login");
        String redirectUri = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/callback";

        AuthorizeUrl authorizeUrl1 = controller.buildAuthorizeUrl(req, redirectUri);
           String authorizeUrl = authorizeUrl1
                .withAudience(String.format("https://%s/userinfo", appConfig.getDomain()))
                .withScope("openid profile email")
                .build();
        return "redirect:" + authorizeUrl;
    }

}

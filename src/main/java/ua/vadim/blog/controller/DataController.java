package ua.vadim.blog.controller;

import org.keycloak.representations.AccessToken;
import org.keycloak.representations.idm.authorization.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.vadim.blog.service.TokenManager;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private TokenManager tokenManager;

    @CrossOrigin
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public String getUsername () {
        AccessToken token = tokenManager.getAccessToken();
        return token.getPreferredUsername();
    }

}

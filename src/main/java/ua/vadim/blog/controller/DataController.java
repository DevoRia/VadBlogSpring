package ua.vadim.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.vadim.blog.service.RequestManager;
import ua.vadim.blog.service.TokenManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private TokenManager tokenManager;

    @CrossOrigin
    @RequestMapping(value = "/logout")
    public String toLogout (){
        try {
            HttpServletRequest request = new RequestManager().getServletRequest();
            request.logout();
        } catch (ServletException e) {
            System.out.println(String.valueOf(e).concat(" It's been exception"));
        }
        return "redirect:/";
    }

    @CrossOrigin
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public String getUsername () {
        return tokenManager.getUsername();
    }

}

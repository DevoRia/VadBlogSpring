package ua.vadim.blog.controller;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.vadim.blog.entity.Blog;

import java.util.List;

public interface ControllerBehavior {

    String addBlog(String title, String text);
    String updateBlog(String title, String text);
    String removeBlog(String title);
    List<Blog> getAllBlogs();
}

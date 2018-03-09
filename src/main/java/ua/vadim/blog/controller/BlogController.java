package ua.vadim.blog.controller;

import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import ua.vadim.blog.entity.Blog;
import ua.vadim.blog.service.BlogService;
import ua.vadim.blog.service.TokenManager;

import java.util.Date;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/server")
public class BlogController implements ControllerBehavior{

    private BlogService blogService;
    private TokenManager tokenManager;

    @Autowired
    public BlogController(BlogService blogService, TokenManager tokenManager) {
        this.blogService = blogService;
        this.tokenManager = tokenManager;
    }



    @CrossOrigin //Access-Control-Allow-Origin дозволяє стороннім ресурсам брати данні
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    @Override
    public List<Blog> getAllBlogs (){
        AccessToken accessToken = tokenManager.getAccessToken();
        String user = accessToken.getPreferredUsername();
        System.out.println(user);
        return blogService.getAllBlogs();
    }

    @CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Override
    public String addBlog(@ModelAttribute(value = "title")String title,
                          @ModelAttribute(value = "author") String author,
                          @ModelAttribute(value = "text") String text) {

        Blog blog = new Blog(title, author, text, new Date(), false);//lombock сам створить конструктор
        this.blogService.addBlog(blog);

        return "redirect:/";
    }

    @CrossOrigin
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @Override
    public String updateBlog(@ModelAttribute(value = "title") String title,
                             @ModelAttribute(value = "author") String author,
                             @ModelAttribute(value = "text") String text) {
        Blog oldBlog = blogService.getBlogByTitle(title);
        Blog blog = new Blog(title, author, text, oldBlog.getDate(), false);//lombock сам створить конструктор
        this.blogService.updateBlog(blog);
        return "redirect:/";
    }

    @CrossOrigin
    @RequestMapping(value = "/remove/{id}")
    public String removeBlog(@PathVariable("id") String title){
        this.blogService.removeBlog(title);
        return "redirect:/";
    }

}

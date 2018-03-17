package ua.vadim.blog.controller;

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

    private final String SUCCESS = "Success";
    private final String FAILED = "Something was going wrong";

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
        return this.blogService.getAllBlogs();
    }

    @CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Override
    public String addBlog(@ModelAttribute(value = "title")String title,
                          @ModelAttribute(value = "text") String text) {
        String author = tokenManager.getUsername();
        Blog blog = new Blog(title, author, text, new Date(), false);//lombock сам створить конструктор
        this.blogService.addBlog(blog);
        return SUCCESS;
    }

    @CrossOrigin
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @Override
    public String updateBlog(@ModelAttribute(value = "title") String title,
                             @ModelAttribute(value = "text") String text) {
        String author = tokenManager.getUsername();
        Blog oldBlog = blogService.getBlogByTitle(title);

        if (author.equals(oldBlog.getAuthor()) || tokenManager.isAdmin()) {
            Blog blog = new Blog(title, oldBlog.getAuthor(), text, oldBlog.getDate(), false);//lombock сам створить конструктор
            this.blogService.updateBlog(blog);
            return SUCCESS;
        }
        return FAILED;
    }

    @CrossOrigin
    @RequestMapping(value = "/remove/{id}")
    public String removeBlog(@PathVariable("id") String title){
        String author = tokenManager.getUsername();
        Blog oldBlog = blogService.getBlogByTitle(title);

        if (author.equals(oldBlog.getAuthor()) || tokenManager.isAdmin()) {
            this.blogService.removeBlog(title);
            return SUCCESS;
        }
        return FAILED;
    }

}

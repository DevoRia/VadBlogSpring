package ua.vadim.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import ua.vadim.blog.entity.Blog;
import ua.vadim.blog.service.BlogService;

import java.util.Date;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/server")
public class BlogController implements ControllerBehavior{

    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @CrossOrigin //Access-Control-Allow-Origin дозволяє стороннім ресурсам брати данні
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    @Override
    public List<Blog> getAllBlogs (){
        return blogService.getAllBlogs();
    }

    @CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Override
    public String addBlog(@ModelAttribute("title")String title,
                          @ModelAttribute("author") String author,
                          @ModelAttribute("text") String text) {

        Blog blog = new Blog(title, author, text, new Date(), false);//lombock сам створить конструктор
        this.blogService.addBlog(blog);

        return "redirect:/";
    }

    @CrossOrigin
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @Override
    public String updateBlog(@ModelAttribute("title") String title,
                             @ModelAttribute("author") String author,
                             @ModelAttribute("text") String text) {
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

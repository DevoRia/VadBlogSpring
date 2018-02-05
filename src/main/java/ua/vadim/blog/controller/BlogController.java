package ua.vadim.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.vadim.blog.entity.Blog;
import ua.vadim.blog.service.BlogService;

import java.util.Date;
import java.util.List;

@RestController
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

        Blog blog = new Blog(null, title, author, text, new Date(), false);
        this.blogService.addBlog(blog);

        return "redirect:/";
    }

    @CrossOrigin
    @Override
    public String updateBlog(Blog blog) {
        //TODO NEED TO BE WRITTEN
        return null;
    }

    @CrossOrigin
    @RequestMapping(value = "/remove/{id}")
    public String removeBlog(@PathVariable("id") long id){
        this.blogService.removeBlog(id);
        return "redirect:/";
    }

}

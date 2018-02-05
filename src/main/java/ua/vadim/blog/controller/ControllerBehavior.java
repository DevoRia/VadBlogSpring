package ua.vadim.blog.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.vadim.blog.entity.Blog;

import java.util.List;

public interface ControllerBehavior {

    String addBlog(String title, String author, String text);
    String updateBlog(Blog blog);
    String removeBlog(long id);
    List<Blog> getAllBlogs();
}

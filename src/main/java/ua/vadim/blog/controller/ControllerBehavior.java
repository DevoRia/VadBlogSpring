package ua.vadim.blog.controller;

import ua.vadim.blog.entity.Blog;

import java.util.List;

public interface ControllerBehavior {

    String addBlog(Blog blog);
    String updateBlog(Blog blog);
    String removeBlog(long id);
    List<Blog> getAllBlogs();
}

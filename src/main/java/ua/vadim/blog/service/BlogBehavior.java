package ua.vadim.blog.service;

import ua.vadim.blog.entity.Blog;

import java.util.List;

public interface BlogBehavior {

    void addBlog(Blog blog);
    void updateBlog(Blog blog);
    void removeBlog(String title);
    Blog getBlogByTitle(String title);
    List<Blog> getAllBlogs();
}

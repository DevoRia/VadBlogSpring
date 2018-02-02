package ua.vadim.blog.service;

import ua.vadim.blog.entity.Blog;

import java.util.List;

public interface BlogBehavior {

    void addBlog(Blog blog);
    void updateBlog(Blog blog);
    void removeBlog(long id);
    Blog getBlogById(long id);
    List<Blog> getAllBlogs();
}

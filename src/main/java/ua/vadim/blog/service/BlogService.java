package ua.vadim.blog.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vadim.blog.dao.BlogRepository;
import ua.vadim.blog.entity.Blog;

import java.util.Date;
import java.util.List;

@Service
public class BlogService implements BlogBehavior {

    private BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Transactional
    @Override
    public void addBlog(Blog blog) {
        blogRepository.save(blog);
    }

    @Transactional
    @Override
    public void updateBlog(Blog blog) {
        blogRepository.save(blog);
    }

    @Transactional
    @Override
    public void removeBlog(long id) {
        blogRepository.delete(id);
    }

    @Transactional
    @Override
    public Blog getBlogById(long id) {
        return blogRepository.findOne(id);
    }

    @Transactional
    @Override
        public List<Blog> getAllBlogs() {
        return Lists.newArrayList(blogRepository.findAll());
    }


}

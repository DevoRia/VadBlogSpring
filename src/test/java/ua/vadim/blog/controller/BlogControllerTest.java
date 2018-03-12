package ua.vadim.blog.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.vadim.blog.entity.Blog;
import ua.vadim.blog.service.BlogService;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BlogControllerTest {

    @Mock
    BlogService service;

    @InjectMocks
    BlogController controller;

    @Test
    public void getAllBlogs() {
        List<Blog> blogs = controller.getAllBlogs();
    }
}
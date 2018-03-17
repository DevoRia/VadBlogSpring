package ua.vadim.blog.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.vadim.blog.entity.Blog;

@Repository

public interface BlogRepository extends MongoRepository<Blog, String>{
}

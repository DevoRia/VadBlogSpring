package ua.vadim.blog.dao;


//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.vadim.blog.entity.Blog;

import java.util.List;

@Repository
//public interface BlogRepository extends CrudRepository<Blog, Long>{
public interface BlogRepository extends MongoRepository<Blog, String>{
}

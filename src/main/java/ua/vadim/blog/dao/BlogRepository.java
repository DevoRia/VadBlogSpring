package ua.vadim.blog.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.vadim.blog.entity.Blog;

@Repository
public interface BlogRepository extends CrudRepository<Blog, Long>{
}

package Spike.springboot.first.repository;

import Spike.springboot.first.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}

package sclnau.main.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sclnau.main.website.entity.News;

import java.util.List;

@Repository
public interface NewsRepo extends JpaRepository<News, Long> {
    List<News> findTop3ByOrderByCreationDateDesc();

    List<News> findAllByOrderByCreationDateDesc();
}

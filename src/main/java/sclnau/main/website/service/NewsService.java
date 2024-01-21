package sclnau.main.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sclnau.main.website.entity.News;
import sclnau.main.website.repository.NewsRepo;

import java.util.List;

@Service
public class NewsService {
    private final NewsRepo newsRepo;

    @Autowired
    public NewsService(NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
    }

    public News getById(Long id){
        return newsRepo.getReferenceById(id);
    }
    public List<News> getAll(){
        return newsRepo.findAllByOrderByCreationDateDesc();
    }

    public Page<News> getAll(Pageable pageable){
        return newsRepo.findAllByOrderByCreationDateDesc(pageable);
    }

    public void save(News news){
        newsRepo.save(news);
    }

    public void remove(News news){
        newsRepo.delete(news);
    }

    public void remove(Long id){
        newsRepo.deleteById(id);
    }

    public List<News> getLatest(){
        return newsRepo.findTop3ByOrderByCreationDateDesc();
    }
}

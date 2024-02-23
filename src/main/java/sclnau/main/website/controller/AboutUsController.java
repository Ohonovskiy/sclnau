package sclnau.main.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sclnau.main.website.entity.GalleryPhoto;
import sclnau.main.website.entity.News;
import sclnau.main.website.service.GalleryPhotoService;
import sclnau.main.website.service.NewsService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/about-us")
public class AboutUsController {

    private final GalleryPhotoService galleryPhotoService;

    private final NewsService newsService;
    @Autowired
    public AboutUsController(GalleryPhotoService galleryPhotoService, NewsService newsService) {
        this.galleryPhotoService = galleryPhotoService;
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public String news(Model model, @RequestParam(defaultValue = "0") int page){

        int pageSize = 9;
        PageRequest pageRequest = PageRequest.of(page, pageSize);

        Page<News> newsPage = newsService.getAll(pageRequest);

        model.addAttribute("allNews", newsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", newsPage.getTotalPages());

        return "about-us/news";
    }

    @GetMapping("/news/{id}")
    public String newsDetails(@PathVariable("id") Long id, Model model){
        News news = newsService.getById(id);
        news.addView();
        model.addAttribute("news", news);
        newsService.save(news);
        return "about-us/news-details";
    }

    @GetMapping("/licenced-volume")
    public String licencedVolume(){
        return "about-us/licenced-volume";
    }

    @GetMapping("/public-info")
    public String publicInfo(){
        return "about-us/public-info";
    }

    @GetMapping("/international-relations")
    public String internationalRelations(){
        return "about-us/international-relations";
    }

    @GetMapping("/historical-sequence")
    public String historicalSequence(){
        return "about-us/historical-sequence";
    }

    @GetMapping("/photo-gallery")
    public String photoGallery(Model model){
        model.addAttribute("photos", galleryPhotoService.getAll());

        return "about-us/photo-gallery";
    }

}

package sclnau.main.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sclnau.main.website.entity.News;
import sclnau.main.website.service.NewsService;

@Controller
@RequestMapping("/about-us")
public class AboutUsController {

    private final NewsService newsService;
    @Autowired
    public AboutUsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public String news(Model model){
        model.addAttribute("allNews", newsService.getAll());
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

}

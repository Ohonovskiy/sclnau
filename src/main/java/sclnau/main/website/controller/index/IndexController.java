package sclnau.main.website.controller.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sclnau.main.website.service.NewsService;

@Controller
@RequestMapping("/")
public class IndexController {

    private final NewsService newsService;

    @Autowired
    public IndexController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public String indexPage(Model model){
        model.addAttribute("latestNews", newsService.getLatest());
        return "index/index";
    }
}

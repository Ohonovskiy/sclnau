package sclnau.main.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sclnau.main.website.entity.News;
import sclnau.main.website.service.GalleryPhotoService;
import sclnau.main.website.service.NewsService;

import java.io.File;
import java.util.ArrayList;
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
//
//    INDEX
//
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

    @GetMapping("/material-technical-base")
    public String materialTechBase(){
        return "about-us/material-tech-base";
    }

    @GetMapping("/monitoring")
    public String monitoring(){
        return "about-us/monitoring";
    }

    @GetMapping("/licenced-volume")
    public String licencedVolume(){
        return "about-us/licenced-volume";
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

//
//    PUBLIC INFO
//
    @GetMapping("/public-info")
    public String publicInfo(){
        return "about-us/public-info/public-info";
    }

    @GetMapping("/public-info/licences")
    public String publicInfoLicence(){
        return "about-us/public-info/licences";
    }

    @GetMapping("/public-info/certificates")
    public String publicInfoCertifications(Model model){
        List<String> imgNames = new ArrayList<>();
        String directoryPath = "src/main/resources/static/images/licences";

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null && directory.isDirectory()) {
            for (File file : files) {
                if (file.isFile()) {
                    imgNames.add(file.getName());
                }
            }
        }
        model.addAttribute("images", imgNames);
        return "about-us/public-info/certificates";
    }

    @GetMapping("/public-info/availability")
    public String publicInfoAvailability(){
        return "about-us/public-info/availability-of-the-educational-institution";
    }

    @GetMapping("/public-info/cadre")
    public String publicInfoCadre(){
        return "about-us/public-info/cadre";
    }

    @GetMapping("/public-info/activity-reports")
    public String publicActivityReports(){
        return "about-us/public-info/activity-reports";
    }

    @GetMapping("/public-info/financial-activity/paid-services")
    public String paidServices(){
        return "about-us/public-info/paid-services";
    }

    @GetMapping("/public-info/financial-activity/reportings")
    public String reportings(){
        return "about-us/public-info/reportings";
    }

    @GetMapping("/public-info/financial-activity/payment-details")
    public String paymentDetails(){
        return "about-us/public-info/payment-details";
    }

    @GetMapping("/public-info/regulations/overall")
    public String publicInfoDocuments(){
        return "about-us/public-info/overall-regulations";
    }

    @GetMapping("/public-info/regulations/educational-process")
    public String publicInfoRegEduProcess(){
        return "about-us/public-info/educational-process";
    }

/*
* LIBRARY
* */
    @GetMapping("/library/terms")
    public String libraryTerms(){
        return "about-us/library/library-terms";
    }

    @GetMapping("/library/new")
    public String libraryNew(){
        return "about-us/library/library-new";
    }

    @GetMapping("/library/periodicals")
    public String libraryPeriodicals(){
        return "about-us/library/library-periodicals";
    }

}

package sclnau.main.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sclnau.main.website.entity.GalleryPhoto;
import sclnau.main.website.entity.News;
import sclnau.main.website.entity.Photo;
import sclnau.main.website.service.GalleryPhotoService;
import sclnau.main.website.service.NewsService;
import sclnau.main.website.service.PhotoService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDate;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final NewsService newsService;
    private final PhotoService photoService;

    private final GalleryPhotoService galleryPhotoService;

    @Value("${gallery.path}")
    private String galleryPath;

    @Autowired
    public AdminController(NewsService newsService, PhotoService photoService, GalleryPhotoService galleryPhotoService) {

        this.newsService = newsService;
        this.photoService = photoService;
        this.galleryPhotoService = galleryPhotoService;
    }

    @GetMapping("/news/upload")
    public String uploadNews(){
        return "admin/upload";
    }

    @PostMapping("/news/uploadNews")
    public String uploadNewsPost(@RequestParam("caption") String caption,
                                 @RequestParam("text") String text,
                                 @RequestParam("location") String location,
                                 @RequestParam("photo") MultipartFile multipartFile,
                                 @RequestParam("date") String date) {
        News news = new News();
        news.setCaption(caption);
        news.setBodyText(text);
        news.setLocation(location);

        Photo photo = new Photo(multipartFile);
        photoService.save(photo);

        news.setPhoto(photo);
        news.setCreationDate(Timestamp.valueOf(LocalDate.parse(date).atStartOfDay()));

        newsService.save(news);

        return "redirect:/admin/news/upload";
    }

    @GetMapping("/photoGallery")
    public String photoGalleryUpload(){
        return "admin/photoGallery";
    }

    @PostMapping("/photoGallery/upload")
    public String photoGalleryUploadPost(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Path directoryPath = Paths.get(galleryPath);

        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        Path path = Paths.get(galleryPath  + file.getOriginalFilename());
        Files.write(path, bytes);

        GalleryPhoto galleryPhoto = new GalleryPhoto();
        galleryPhoto.setImagePath(path.toString().substring(path.toString().indexOf("/images")));

        galleryPhotoService.save(galleryPhoto);

        return "redirect:/admin/photoGallery";
    }
}
























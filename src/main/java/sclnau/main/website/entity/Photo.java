package sclnau.main.website.entity;

import jakarta.persistence.*;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Photo {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;

    @OneToOne
    private News news;

    @Value("${upload.path}")
    String pathToUpload;

    public Photo(MultipartFile multipartFile){

        File file = new File(pathToUpload + multipartFile.getOriginalFilename());

        this.path = file.getPath();
    }
}

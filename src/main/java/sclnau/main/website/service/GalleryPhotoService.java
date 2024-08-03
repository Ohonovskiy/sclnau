package sclnau.main.website.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GalleryPhotoService {
    @Value("${gallery.path}")
    private String galleryPath;

    public Set<File> getAll() {
        return Stream.of(Objects.requireNonNull(new File(galleryPath).listFiles()))
                .filter(file -> !file.isDirectory())
                .collect(Collectors.toSet());
    }

    public List<String> getAllNames(){
        return Stream.of(Objects.requireNonNull(new File(galleryPath).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }

    public void save(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Path directoryPath = Paths.get(galleryPath);

        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        String filename = UUID.randomUUID() + "." + file.getOriginalFilename().split("\\.")[1];
        Path path = Paths.get(galleryPath  + filename);
        Files.write(path, bytes);
    }
}

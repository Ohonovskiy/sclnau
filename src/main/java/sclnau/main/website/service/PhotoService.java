package sclnau.main.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sclnau.main.website.entity.Photo;
import sclnau.main.website.repository.PhotoRepo;

import java.util.List;

@Service
public class PhotoService {
    private final PhotoRepo photoRepo;

    @Autowired
    public PhotoService(PhotoRepo photoRepo) {
        this.photoRepo = photoRepo;
    }

    public void save(Photo photo){
        photoRepo.save(photo);
    }

    public Photo getById(Long id){
        return photoRepo.getReferenceById(id);
    }

    public void remove(Long id){
        photoRepo.deleteById(id);
    }

    public void remove(Photo photo){
        photoRepo.delete(photo);
    }

    public List<Photo> getAllForNews(Long id){
        return photoRepo.findAllByNewsId(id);
    }

    public List<Photo> getAll(){
        return photoRepo.findAll();
    }
}

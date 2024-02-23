package sclnau.main.website.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sclnau.main.website.entity.GalleryPhoto;
import sclnau.main.website.repository.GalleryPhotoRepo;

import java.util.List;

@Service
public class GalleryPhotoService {
    private final GalleryPhotoRepo galleryPhotoRepo;

    public GalleryPhotoService(GalleryPhotoRepo galleryPhotoRepo) {
        this.galleryPhotoRepo = galleryPhotoRepo;
    }

    public void save(GalleryPhoto galleryPhoto){
        galleryPhotoRepo.save(galleryPhoto);
    }

    public void remove(GalleryPhoto galleryPhoto){
        galleryPhotoRepo.delete(galleryPhoto);
    }

    public void remove(Long id){
        galleryPhotoRepo.deleteById(id);
    }

    public GalleryPhoto getById(Long id){
        return galleryPhotoRepo.getReferenceById(id);
    }

    public List<GalleryPhoto> getAll(){
        return galleryPhotoRepo.findAllByOrderByIdAsc();
    }
}

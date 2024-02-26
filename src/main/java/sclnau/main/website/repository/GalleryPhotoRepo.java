package sclnau.main.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sclnau.main.website.entity.GalleryPhoto;

import java.util.List;

@Repository
public interface GalleryPhotoRepo extends JpaRepository<GalleryPhoto, Long> {
    List<GalleryPhoto> findAllByOrderByIdDesc();
}

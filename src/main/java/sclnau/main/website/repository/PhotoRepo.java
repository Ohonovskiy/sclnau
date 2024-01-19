package sclnau.main.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sclnau.main.website.entity.Photo;

import java.util.List;

@Repository
public interface PhotoRepo extends JpaRepository<Photo, Long> {
    List<Photo> findAllByNewsId(Long id);
}

package sclnau.main.website.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String caption;
    private String bodyText;
    private Long views = 0L;
    private Timestamp creationDate = new Timestamp(System.currentTimeMillis());

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "news")
    private List<Photo> photos = new ArrayList<>();

    public void addView(){
        this.views++;
    }
}

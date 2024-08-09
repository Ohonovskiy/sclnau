package sclnau.main.website.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String caption;
    private String bodyText;
    private String shortDesc;
    private String location;
    private Long views = 0L;
    private Timestamp creationDate = new Timestamp(System.currentTimeMillis());

    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "news")
    private Photo photo;

    public void addView(){
        this.views++;
    }
}

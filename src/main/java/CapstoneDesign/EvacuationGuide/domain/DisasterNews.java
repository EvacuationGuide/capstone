package CapstoneDesign.EvacuationGuide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
public class DisasterNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disasater_news_id")
    private Long id;

    private Date postDate;
    private String title;
    private String body;


    @Builder
    public DisasterNews(Date postDate, String title, String body) {
        this.postDate = postDate;
        this.title = title;
        this.body = body;
    }

}

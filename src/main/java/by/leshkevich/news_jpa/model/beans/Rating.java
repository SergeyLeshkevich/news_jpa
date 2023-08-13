package by.leshkevich.news_jpa.model.beans;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "id_user")
    private long idUser;
    @Column(name = "id_news")
    private long idNews;
}

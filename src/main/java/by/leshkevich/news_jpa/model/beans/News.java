package by.leshkevich.news_jpa.model.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "news",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "title_unique",
                        columnNames = "title"
                )
        }
)
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_user", nullable = false)
    private long idUser;

    @Column(nullable = false)
    @NotEmpty(message="Title should not empty")
    @Size(min=2, max=50, message="Title should be between 2 and 50 characters")
    private String title;

    @Column(nullable = false, length = 5000)
    @NotEmpty(message="Text should not empty")
    @Size(min=50, max=5000, message="Text should be between 50 and 5000 characters")
    private String text;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm")
    @Column(updatable = false)
    private LocalDateTime date;

    @Column(columnDefinition = "integer default 0", name = "rating_pos")
    private int ratingPos;

    @Column(columnDefinition = "integer default 0", name = "rating_neg")
    private int ratingNeg;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "news")
    private List<NewsImage> images=new ArrayList<>();

    private long previewImageId;

    @PrePersist
    private void init(){
        date=LocalDateTime.now();
    }

    public String getTextForIndex(int lengthText) {
        StringBuilder resText = new StringBuilder();
        int length = this.text.length();
        int counter = 0;
        while (counter < length && counter < lengthText) {
            resText.append(this.text.charAt(counter++));
        }
        if (text.length() > lengthText) {
            resText.append("...");
        }
        return resText.toString();
    }

    public void addImageToNews(NewsImage image){
        image.setNews(this);
        images.add(image);
    }
}

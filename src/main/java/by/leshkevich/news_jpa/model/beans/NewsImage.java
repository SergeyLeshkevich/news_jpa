package by.leshkevich.news_jpa.model.beans;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "news_images")
@NoArgsConstructor
@AllArgsConstructor
public class NewsImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(name = "original_file_name")
    private String originalFileName;
    private long size;
    @Column(name = "content_type")
    private String contentType;
    @Column(name = "is_preview_image")
    private boolean isPreviewImage;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] bytes;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private News news;

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}

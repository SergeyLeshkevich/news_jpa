package by.leshkevich.news_jpa.model.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "user_images")
@NoArgsConstructor
@AllArgsConstructor
public class UserImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(name = "original_file_name")
    private String originalFileName;
    private long size;
    @Column(name = "content_type")
    private String contentType;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] bytes;

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}

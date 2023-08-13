package by.leshkevich.news_jpa.model.beans;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Table(name = "users")
@Entity
@Component
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotEmpty(message = "Login should not empty")
    @Size(min = 2, max = 50, message = "Login should be between 2 and 50 characters")
    private String login;

    @Column(nullable = false)
    @NotEmpty(message = "Email should not empty")
    @Email(message = "Email should be valid")
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private UserImage image;

    @Column(name = "role")
    private String role;
}

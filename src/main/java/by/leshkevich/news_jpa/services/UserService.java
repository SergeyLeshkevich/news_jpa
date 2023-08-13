package by.leshkevich.news_jpa.services;

import by.leshkevich.news_jpa.model.beans.User;
import by.leshkevich.news_jpa.model.beans.UserImage;
import by.leshkevich.news_jpa.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void registration(User user, MultipartFile multipartFile, String role) throws IOException {
        UserImage userImage = null;
        if (!multipartFile.isEmpty()) {
            userImage = toImageEntity(multipartFile);
            user.setImage(userImage);
        }
        user.setRole(role);
        userRepository.save(user);
        log.info("Saving new User. Email: {};", user.getEmail());
    }

    private UserImage toImageEntity(MultipartFile file) throws IOException {
        UserImage image = new UserImage();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());

        return image;
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email).stream().findAny();
    }

    public Optional<User> getUserByID(Long id) {
        return userRepository.findById(id).stream().findAny();
    }
}

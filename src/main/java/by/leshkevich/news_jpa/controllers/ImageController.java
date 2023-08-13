package by.leshkevich.news_jpa.controllers;

import by.leshkevich.news_jpa.model.beans.NewsImage;
import by.leshkevich.news_jpa.model.beans.UserImage;
import by.leshkevich.news_jpa.repositories.NewsImageRepository;
import by.leshkevich.news_jpa.repositories.UserImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RequiredArgsConstructor
@RestController
public class ImageController {
    private final NewsImageRepository newsImageRepository;
    private final UserImageRepository userImageRepository;

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable long id){
        NewsImage image= newsImageRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName",image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

    @GetMapping("/userImage/{id}")
    private ResponseEntity<?> getUserImageById(@PathVariable long id){
        UserImage image = userImageRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName",image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}

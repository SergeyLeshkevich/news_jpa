package by.leshkevich.news_jpa.repositories;

import by.leshkevich.news_jpa.model.beans.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}

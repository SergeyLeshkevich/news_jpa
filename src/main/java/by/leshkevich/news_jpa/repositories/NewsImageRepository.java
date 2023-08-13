package by.leshkevich.news_jpa.repositories;

import by.leshkevich.news_jpa.model.beans.NewsImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsImageRepository extends JpaRepository<NewsImage,Long> {
}

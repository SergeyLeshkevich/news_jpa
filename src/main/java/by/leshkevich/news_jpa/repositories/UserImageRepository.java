package by.leshkevich.news_jpa.repositories;

import by.leshkevich.news_jpa.model.beans.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserImageRepository extends JpaRepository<UserImage,Long> {
}

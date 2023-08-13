package by.leshkevich.news_jpa.repositories;

import by.leshkevich.news_jpa.model.beans.News;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    Optional<News> findByTitle(String title);
    @Modifying
    @Transactional
    @Query(value = "update news set news.rating_pos=(news.rating_pos + 1) " +
            "where news.id=:idNews and " +
            "(select id from ratings where ratings.id_user=:idUser and ratings.id_news=:idNews) is null", nativeQuery = true)
    int updatePosRating(@Param("idNews") long idNews, @Param("idUser") long idUser);

    @Modifying
    @Transactional
    @Query(value = "update news set news.rating_neg=(news.rating_neg + 1) " +
            "where news.id=:idNews and " +
            "(select id from ratings where ratings.id_user=:idUser and ratings.id_news=:idNews) is null", nativeQuery = true)
    int updateNegRating(@Param("idNews") long idNews, @Param("idUser") long idUser);
    @Modifying
    @Transactional
    @Query(value = "insert into ratings(id_news, id_user) values (:idNews, :idUser)", nativeQuery = true)
    void addLineRatings(@Param("idNews") long idNews, @Param("idUser") long idUser);

    @Query(value = "select " +
            "n.id, " +
            "n.date, " +
            "n.id_user, " +
            "n.preview_image_id, " +
            "n.rating_neg, " +
            "n.rating_pos, " +
            "n.text, " +
            "n.title, " +
            "i.bytes, " +
            "i.content_type, " +
            "i.is_preview_image, " +
            "i.name, i.original_file_name, " +
            "i.size " +
            "from news as n " +
            "left join news_images as i on n.id = i.news_id" +
            " where n.id=:search or n.title=:search", nativeQuery = true)
    Optional<News> findByTitleOrId(@Param("search") String search);

    @Modifying
    @Transactional
    @Query(value = "update news as n set n.id_user=:idUser, n.title=:title, n.text=:text, n.date=:date " +
            "where news.id=:idNews", nativeQuery = true)
    void updateNews(@Param("id")long id,@Param("idUser") long idUser,@Param("title") String title,
                    @Param("text")String text,@Param("date") LocalDateTime date);
}

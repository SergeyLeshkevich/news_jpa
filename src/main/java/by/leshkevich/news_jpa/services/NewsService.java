package by.leshkevich.news_jpa.services;

import by.leshkevich.news_jpa.model.beans.Image;
import by.leshkevich.news_jpa.model.beans.News;
import by.leshkevich.news_jpa.repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;

    public List<News> newsListOrderDate() {
        return newsRepository.findByOrderByDateDesc();
    }

    public List<News> newsListOrderRating(int quantityNews) {
        List<News> list = new ArrayList<>();
        List<News> listFromDB = newsRepository.findByOrderByRatingPosDesc();
        int counter = 0;

        if (listFromDB.size() < quantityNews) {
            counter = listFromDB.size();
        } else {
            counter = quantityNews;
        }

        for (int i = 0; i < counter; i++) {
            list.add(listFromDB.get(i));
        }

        return list;
    }

    public void saveNews(News news, MultipartFile[] files) throws IOException {
        Image image;
        boolean flagIsPreviewImage = true;

        for (int i = 0; i < 3; i++) {
            if (files[i].getSize() != 0) {

                image = toImageEntity(files[i]);

                if (flagIsPreviewImage) {

                    image.setPreviewImage(true);
                    flagIsPreviewImage = false;

                }
                news.addImageToNews(image);
            }

            News newsFromDB = newsRepository.save(news);
                if (!newsFromDB.getImages().isEmpty()) {
                    newsFromDB.setPreviewImageId(newsFromDB.getImages().get(0).getId());
                    newsRepository.save(newsFromDB);
                }
                log.info("Saving new News. Title: {};", news.getTitle());

        }

    }

    //доработать

    public boolean updateRating(long idNews, String action, long idUser) {
        int quantityObj;

        switch (action) {
            case "pos":
                quantityObj = newsRepository.updatePosRating(idNews, idUser);
                break;
            case "neg":
                quantityObj = newsRepository.updateNegRating(idNews, idUser);
                break;
            default:
                return false;
        }

        if (quantityObj > 0) {
            newsRepository.addLineRatings(idNews, idUser);
        }

        return quantityObj > 0;
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());

        return image;
    }

    public Optional<News> newsById(long id) {
       return newsRepository.findById(id);
    }

    public Optional<News> newsByTitleOrId(String search) {
        return newsRepository.findByTitleOrId(search);
    }

    public Optional<News> newsByTitle(String title) {
        return newsRepository.findByTitle(title);
    }
}

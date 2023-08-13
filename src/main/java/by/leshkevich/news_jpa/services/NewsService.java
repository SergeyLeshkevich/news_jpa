package by.leshkevich.news_jpa.services;

import by.leshkevich.news_jpa.model.beans.News;
import by.leshkevich.news_jpa.model.beans.NewsImage;
import by.leshkevich.news_jpa.repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;

    public List<News> newsListOrderDate() {
        List<News> list = newsRepository.findAll();
        if (list.size() > 2) {
            Collections.sort(list, Collections.reverseOrder(
                    (n1, n2) -> n1.getDate().compareTo(n2.getDate())
            ));
        }
        return list;
    }

    public List<News> newsListOrderRating(int quantityNews) {
        List<News> listFromDB = newsRepository.findAll();
        if (listFromDB.size() > 2) {
            listFromDB = listFromDB.stream().sorted((n1, n2) ->
                    n2.getRatingPos() - n1.getRatingPos()
            ).limit(quantityNews).collect(Collectors.toList());
        }

        return listFromDB;
    }

    public void saveNews(News news, MultipartFile[] files) throws IOException {
        NewsImage image;
        boolean flagIsPreviewImage = true;
        if (!news.getImages().isEmpty() && files.length > 0){
            news.getImages().clear();
            newsRepository.save(news);
        }

        for (int i = 0; i < 3; i++) {
            if (files[i].getSize() != 0) {

                image = toImageEntity(files[i]);

                if (flagIsPreviewImage) {

                    image.setPreviewImage(true);
                    flagIsPreviewImage = false;

                }
                news.addImageToNews(image);
            }
        }
        News newsFromDB = newsRepository.save(news);
        if (!newsFromDB.getImages().isEmpty()) {
            newsFromDB.setPreviewImageId(newsFromDB.getImages().get(0).getId());
            newsRepository.save(newsFromDB);
        }
        log.info("Saving new News. Title: {};", news.getTitle());

    }

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

    private NewsImage toImageEntity(MultipartFile file) throws IOException {
        NewsImage image = new NewsImage();
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

package by.leshkevich.news_jpa.util;

import by.leshkevich.news_jpa.model.beans.News;
import by.leshkevich.news_jpa.model.beans.User;
import by.leshkevich.news_jpa.repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NewsValidator implements Validator {

    private final NewsRepository newsRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return News.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        News news = (News) target;
        Optional<News> newsFromBD = newsRepository.findByTitle(news.getTitle()).stream().findAny();
        if(newsFromBD.isPresent())
            errors.rejectValue("title","", "This title is already taken");
    }
}

package by.leshkevich.news_jpa.util;

import by.leshkevich.news_jpa.model.beans.News;
import by.leshkevich.news_jpa.repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
        if(!newsRepository.findByTitle(news.getTitle()).isEmpty())
            errors.rejectValue("title","", "This title is already taken");
    }
}

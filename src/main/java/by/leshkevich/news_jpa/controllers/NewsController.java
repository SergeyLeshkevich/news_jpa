package by.leshkevich.news_jpa.controllers;

import by.leshkevich.news_jpa.model.beans.News;
import by.leshkevich.news_jpa.model.beans.User;
import by.leshkevich.news_jpa.services.NewsService;
import by.leshkevich.news_jpa.util.AuthenticationUser;
import by.leshkevich.news_jpa.util.NewsValidator;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;
    private final NewsValidator newsValidator;

    @ModelAttribute("auth_user")
    public User getUser() {
        return AuthenticationUser.getAuthenticationUser();
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("newsList", newsService.newsListOrderDate());
        model.addAttribute("topList", newsService.newsListOrderRating(3));
        return "index";
    }

    @GetMapping("/add")
    public String formToAdd(Model model) {
        model.addAttribute("news", new News());
        return "addnews";
    }

    @PostMapping("/add")
    public String createNews(@RequestParam("file") MultipartFile[] files, @ModelAttribute("news") @Valid News news,
                             BindingResult bindingResult) {
        newsValidator.validate(news, bindingResult);

        if (bindingResult.hasErrors())
            return "addnews";

        try {
            news.setIdUser(AuthenticationUser.getAuthenticationUser().getId());
            newsService.saveNews(news, files);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/news/home";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        News news = newsService.newsById(id).orElse(null);
        model.addAttribute("news", news);
        return "edit";
    }

    @PostMapping("/update")
    public String updateNews(@RequestParam("file") MultipartFile[] files,
                             @ModelAttribute("news") @Valid News news,
                             BindingResult bindingResult) {
        News newsBD = newsService.newsById(news.getId()).get();

        if (!news.getTitle().equals(newsBD.getTitle())) {
            newsValidator.validate(news, bindingResult);
        }

        if (bindingResult.hasErrors())
            return "addnews";

        news.setIdUser(newsBD.getIdUser());
        try {
            newsService.saveNews(news, files);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/news/all";
    }

    @PostMapping("/rating/update")
    public String updateRating(long idNews, String action) {

        long idUser = AuthenticationUser.getAuthenticationUser().getId();
        newsService.updateRating(idNews, action, idUser);

        return "redirect:/news/home";
    }

    @GetMapping("/all")
    public String allNews(Model model) {
        model.addAttribute("newsList", newsService.newsListOrderDate());
        return "allNews";
    }

    @GetMapping("/detail/{id}")
    public String newsById(@PathVariable long id, Model model) {
        News news = newsService.newsById(id).orElse(null);
        String message = news == null ? "Извините, новость не найдена" : null;

        model.addAttribute("message", message);
        model.addAttribute("news", news);

        return "news";
    }

    @GetMapping("/search")
    public String searchNews(Model model, String search) {
        List<News> newsList = new ArrayList<>();
        News news = newsService.newsByTitleOrId(search).orElse(null);
        String message = news == null ? "Извините новость не найдена" : null;
        if (news != null) newsList.add(news);

        model.addAttribute("message", message);
        model.addAttribute("newsList", newsList);
        System.out.println(news);

        return "allNews";
    }

}

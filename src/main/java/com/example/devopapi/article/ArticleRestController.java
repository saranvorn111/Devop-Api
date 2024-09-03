package com.example.devopapi.article;

import com.example.devopapi.web.ArticleDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleRestController {

    @GetMapping
    public List<ArticleDto> findAllArticle(){
        List<ArticleDto> articleDtos = new ArrayList<>() {{
            add(new ArticleDto(1, "abc"));
            add(new ArticleDto(2, "cde"));
        }};

        return articleDtos;

    }
}

/*
package xh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FrontendController {
    @Autowired
    private ArticleService articleService;

    @GetMappging("/")
    public String index(
            @RequestParam("page", defaultValue="1") int page, Model model) {
        model.addAttribute("articleList", articleService.list(page));
        return "index";
    }
    @GetMapping("/page/{page}")
    public String page(
            @PathVariable("page") int page,
            Model model
    ) {
        model.addAttribute("articleList", articleService.list(page));
        return "index";
    }
}*/

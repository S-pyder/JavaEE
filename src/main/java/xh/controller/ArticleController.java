package xh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xh.model.Article;
import xh.model.Category;
import xh.model.Comment;
import xh.model.User;
import xh.service.ArticleService;
import xh.service.CategoryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class ArticleController {

//    @Autowired
//    private User testUser;

    @Autowired
    private ArticleService articleService;

//    @Autowired
//    private List<Article> testArticles;

//    @Autowired
//    private List<Category> testCategories;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/")
    public String index(HttpServletRequest httpServletRequest, Model model) {//根据路径跳转到index.ftlh
        HttpSession httpSession = httpServletRequest.getSession(false);
        if (httpSession != null) {
            User user = (User) httpSession.getAttribute("user");
            if (user != null) {
                model.addAttribute("user", user);
            }
        }
        List<Article> articles = articleService.selectAll();
        model.addAttribute("articleList", articles);
        return "index";
    }


    @RequestMapping("/a/{articleId}")
    public String detail(HttpServletRequest httpServletRequest, @PathVariable("articleId") Long articleId, Model model) {//跳转到info.ftlh
        HttpSession httpSession = httpServletRequest.getSession(false);
        if (httpSession != null) {
            User user = (User) httpSession.getAttribute("user");
            if (user != null) {
                model.addAttribute("user", user);
            }
        }
        model.addAttribute("article", articleService.selectByPrimaryKey(articleId));
        return "info";
    }

    @RequestMapping("a/{articleId}/comments")
    public String addComment(@PathVariable("articleId") Long articleId, HttpServletRequest httpServletRequest, Comment comment, Model model) {//跳转到info.ftlh
//        model.addAttribute("user", testUser);
//        model.addAttribute("article", testArticles.get(0));
        HttpSession httpSession = httpServletRequest.getSession(false);
        User user = (User) httpSession.getAttribute("user");
        comment.setUserId(user.getId());
        comment.setCreatedAt(new Date());
        comment.setArticleId(articleId);
        articleService.addComment(comment);
        // 添加评论到数据库
        return "redirect:/a/" + articleId;
    }

    @RequestMapping("/writer")
    public String addArticle(Model model, HttpServletRequest httpServletRequest) {
        //TODO 从session中获取用户
        HttpSession httpSession = httpServletRequest.getSession(false);
        User user = (User) httpSession.getAttribute("user");
        List<Article> articles = articleService.queryByUserId(user.getId());
        List<Category> categories = categoryService.queryByUserId(user.getId());

        model.addAttribute("categoryList", categories);
        model.addAttribute("articleList", articles);
        model.addAttribute("activeCid", categories.get(0).getId());//选中的分类id

        return "writer";
    }

    //点击添加分类，跳转到分类页面 功能不实现
    @RequestMapping(value = "/c/add", method = RequestMethod.POST)
    public String addCategory(Model model, Category category, HttpServletRequest httpServletRequest) {
        //TODO 添加分类到数据库
        HttpSession httpSession = httpServletRequest.getSession(false);
        User user = (User) httpSession.getAttribute("user");
        category.setUserId(user.getId());
        int num = categoryService.insert(category);

        return "redirect:/writer";
    }

    /*//点击分类，跳转到分类页面 功能不实现
    @RequestMapping(value = "/writer/c/{categoryId}", method = RequestMethod.GET)
    public String modifyCategory(@PathVariable("categoryId") Long categoryId, Model model) {
        //TODO 添加分类到数据库
        model.addAttribute("categoryList", testCategories);
        model.addAttribute("articleList", testArticles);
        model.addAttribute("activeCid", 1L);
        return "writer";
    }*/

    //跳转新建文章/修改
    @RequestMapping("/writer/forward/{type}/{id}/editor")//type   ：1 新增 分类id  2 修改 文章id
    public String forwardEditor(@PathVariable("type") int type,
                                @PathVariable("id") Long id,
                                Model model) {
        if (type == 1){
            Category category = categoryService.queryByCategoryId(id);
            model.addAttribute("activeCid", id);
            model.addAttribute("category", category);
        }else if (type == 2){
            Article article = articleService.selectByPrimaryKey(id);
            model.addAttribute("article", article);

            Category category = categoryService.queryByCategoryId(article.getCategoryId());
            model.addAttribute("activeCid", category.getId());
            model.addAttribute("category", category);
        }
        model.addAttribute("type", type);
        return "editor";
    }

    //新建文章/修改   type   ：1 新增 分类id  2 修改 文章id
    @RequestMapping(value = "/writer/article/{type}/{id}", method = RequestMethod.POST)
    public String addOrModifyArticle(@PathVariable("type") int type,
                                     @PathVariable("id") Long id,
                                     Article article,
                                     HttpServletRequest httpServletRequest) {

        article.setUpdatedAt(new Date());
        if (type == 1){
            //插入
            HttpSession httpSession = httpServletRequest.getSession(false);
            User user = (User) httpSession.getAttribute("user");
            article.setUserId(user.getId());
            article.setStatus((byte) 1);
            article.setViewCount(0L);
            article.setCategoryId(id);///////////////////////
            article.setCoverImage("https://picsum.photos/id/1/400/300");
            article.setCreatedAt(new Date());
            int num = articleService.insert(article);
            Category category = categoryService.queryByCategoryId(id);
        }else if (type == 2){
            //修改
            article.setId(id);
            articleService.update(article);
        }
        //
        return "redirect:/writer";
    }

    /*//跳转到修改文字
    @RequestMapping(value = "/writer/c/{articleId}/articles/modify")
    public String forwardModifyArticle(@PathVariable("articleId") Long articleId, Article article, Model model){
        article.setId(articleId);
        model.addAttribute("activeCid", testCategories.get(0).getId());
        model.addAttribute("category", testCategories.get(0));
        return "editor";
    }*/
}

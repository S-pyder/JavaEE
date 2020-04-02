package xh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xh.interceptor.LoginInterceptor;


@Configuration
public class BlogConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())//.addPathPatterns("/")
                .excludePathPatterns("/")
                .excludePathPatterns("/index")
                .excludePathPatterns("/login")

                .excludePathPatterns("/css/**")
                .excludePathPatterns("/fonts/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/plugins/editor/**");
    }

    /*@Bean
    public User testUser(){
        User user = new User();
        user.setId(1L);
        user.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583919974415&di=afd58143b8a7817391eaf96e7eba0d08&imgtype=0&src=http%3A%2F%2Ft8.baidu.com%2Fit%2Fu%3D2247852322%2C986532796%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1280%26h%3D853");
        user.setNickname("尼古拉斯");
        user.setUsername("赵四");
        user.setPassword("123");
        return user;
    }

    @Bean
    public Comment testComment1(User testUser){
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setUserId(1L);
        comment.setUser(testUser);
        comment.setArticleId(1L);
        comment.setContent("评论信息11111111");
        comment.setCreatedAt(new Date());
        return comment;
    }

    @Bean
    public Comment testComment2(User testUser){
        Comment comment = new Comment();
        comment.setId(2L);
        comment.setUserId(2L);
        comment.setUser(testUser);
        comment.setArticleId(2L);
        comment.setContent("评论信息2");
        comment.setCreatedAt(new Date());
        return comment;
    }

    @Bean
    public Comment testComment3(User testUser){
        Comment comment = new Comment();
        comment.setId(3L);
        comment.setUserId(3L);
        comment.setUser(testUser);
        comment.setArticleId(3L);
        comment.setContent("评论信息3");
        comment.setCreatedAt(new Date());
        return comment;
    }

    @Bean
    public Article testArticle1(User testUser, List<Comment> testCommentList){
        Article article = new Article();
        article.setId(1L);
        article.setCategoryId(1L);
        article.setTitle("文章1");
        article.setContent("文章一内容");
        article.setCoverImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583919974415&di=afd58143b8a7817391eaf96e7eba0d08&imgtype=0&src=http%3A%2F%2Ft8.baidu.com%2Fit%2Fu%3D2247852322%2C986532796%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1280%26h%3D853");
        article.setViewCount(8L);
        article.setCreatedAt(new Date());
        article.setUpdatedAt(new Date());
        article.setStatus((byte) 1);
        article.setAuthor(testUser);
        article.setCommentCount(1L);
        article.setCommentList(testCommentList);
        return article;
    }

    @Bean
    public Article testArticle2(User testUser){
        Article article = new Article();
        article.setId(2L);
        article.setCategoryId(2L);
        article.setTitle("文章2");
        article.setContent("文章2内容");
        article.setCoverImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583919974414&di=05cb93de19b22c5086dce5f308117184&imgtype=0&src=http%3A%2F%2Ft7.baidu.com%2Fit%2Fu%3D3204887199%2C3790688592%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D4610%26h%3D2968");
        article.setViewCount(8L);
        article.setCreatedAt(new Date());
        article.setUpdatedAt(new Date());
        article.setStatus((byte) 1);
        article.setAuthor(testUser);
        article.setCommentCount(2L);
        return article;
    }

    @Bean
    public Article testArticle3(User testUser){
        Article article = new Article();
        article.setId(3L);
        article.setCategoryId(3L);
        article.setTitle("文章3");
        article.setContent("文章3内容");
        article.setCoverImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583919974414&di=3aa4e074d1d842833c4e4e6d0c3351ac&imgtype=0&src=http%3A%2F%2Ft9.baidu.com%2Fit%2Fu%3D3363001160%2C1163944807%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1280%26h%3D830");
        article.setViewCount(8L);
        article.setCreatedAt(new Date());
        article.setUpdatedAt(new Date());
        article.setStatus((byte) 1);
        article.setAuthor(testUser);
        article.setCommentCount(3L);
        return article;
    }

    @Bean
    public List<Comment> testComments(Comment testComment1, Comment testComment2, Comment testComment3){
        List<Comment> comments = new ArrayList<>();
        comments.add(testComment1);
        comments.add(testComment2);
        comments.add(testComment3);
        return comments;
    }

    @Bean
    public List<Article> testArticles(Article testArticle1, Article testArticle2, Article testArticle3){
        List<Article> articles = new ArrayList<>();
        articles.add(testArticle1);
        articles.add(testArticle2);
        articles.add(testArticle3);
        return articles;
    }

    @Bean
    public Category testCategory1(){
        Category category = new Category();
        category.setId(1L);
        category.setUserId(1L);
        category.setName("英语");
        return category;
    }

    @Bean
    public Category testCategory2(){
        Category category = new Category();
        category.setId(2L);
        category.setUserId(2L);
        category.setName("中文");
        return category;
    }

    @Bean
    public Category testCategory3(){
        Category category = new Category();
        category.setId(3L);
        category.setUserId(3L);
        category.setName("数学");
        return category;
    }

    @Bean
    public List<Category> testCategories(Category testCategory1, Category testCategory2, Category testCategory3){
        List<Category> categories = new ArrayList<>();
        categories.add(testCategory1);
        categories.add(testCategory2);
        categories.add(testCategory3);
        return categories;
    }*/


}

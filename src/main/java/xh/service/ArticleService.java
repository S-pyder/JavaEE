package xh.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xh.mapper.ArticleMapper;
import xh.mapper.CommentMapper;
import xh.model.Article;
import xh.model.Comment;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CommentMapper commentMapper;

    public List<Article> selectAll() {
        return articleMapper.selectAll();
    }

    public Article selectByPrimaryKey(Long articleId) {

        Article article = articleMapper.selectByPrimaryKey(articleId);
        List<Comment> comments = commentMapper.selectByArticleId(articleId);
        article.setCommentList(comments);
        article.setCommentCount((long) comments.size());

        return article;
    }

    public void addComment(Comment comment) {
        commentMapper.insert(comment);
    }

    public List<Article> queryByUserId(Long id) {
        return articleMapper.queryByUserId(id);
    }

    public int insert(Article article) {
        return articleMapper.insert(article);
    }

    public int update(Article article) {
        return articleMapper.update(article);
    }
}

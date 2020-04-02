package xh.mapper;

import org.apache.ibatis.annotations.Mapper;
import xh.model.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Article record);

    Article selectByPrimaryKey(Long id);

    List<Article> selectAll();

    int updateByPrimaryKey(Article record);

    List<Article> queryByUserId(Long id);

    int update(Article article);
}
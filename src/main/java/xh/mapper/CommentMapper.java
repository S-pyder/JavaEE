package xh.mapper;

import org.apache.ibatis.annotations.Mapper;
import xh.model.Comment;

import java.util.List;
@Mapper
public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    Comment selectByPrimaryKey(Long id);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);

    List<Comment> selectByArticleId(Long articleId);

}
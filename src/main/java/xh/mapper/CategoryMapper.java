package xh.mapper;

import org.apache.ibatis.annotations.Mapper;
import xh.model.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Category record);

    Category selectByPrimaryKey(Long id);

    List<Category> selectAll();

    int updateByPrimaryKey(Category record);

    List<Category> queryByUserId(Long id);
}
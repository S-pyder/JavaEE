package xh.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xh.mapper.CategoryMapper;
import xh.model.Category;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryByUserId(Long id) {
        return categoryMapper.queryByUserId(id);
    }

    public int insert(Category category) {
        return categoryMapper.insert(category);
    }

    public Category queryByCategoryId(Long id) {
        return categoryMapper.selectByPrimaryKey(id);
    }
}

package com.ra.service.category;

import com.ra.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<Category> fillAll();
    Category save(Category category);
    Category findCategoryById(Integer id);
    void delete( Integer id);
    Page<Category> paginate(Pageable pageable);
}

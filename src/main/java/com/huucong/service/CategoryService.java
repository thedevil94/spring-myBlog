package com.huucong.service;

import com.huucong.model.Category;

public interface CategoryService {
    Iterable<Category> finAll();

    Category findById(int id);

    void save(Category category);

    void delete(int id);
}

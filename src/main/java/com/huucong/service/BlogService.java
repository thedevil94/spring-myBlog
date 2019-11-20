package com.huucong.service;

import com.huucong.model.Blog;
import com.huucong.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    Page<Blog> finAll(Pageable pageable);

    Blog finById(int id);

    void save(Blog blog);

    void delete(int id);

    Iterable<Blog> findAllByCategory(Category category);

    Page<Blog> findAllByTitleContaining(String title, Pageable pageable);
}

package com.huucong.controller;

import com.huucong.model.Blog;
import com.huucong.model.Category;
import com.huucong.service.BlogService;
import com.huucong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class BLogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories (){
        return categoryService.finAll();
    }

    @GetMapping("/")
    public ModelAndView listBog(@RequestParam("s") Optional<String> s, @PageableDefault(size = 2) Pageable pageable){
        Page<Blog> blogs = blogService.finAll(pageable);
        if (s.isPresent()){
            blogs = blogService.findAllByTitleContaining(s.get(), pageable);
        }else {
            blogs = blogService.finAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("blogs",blogs);
        return modelAndView;
    }

    @GetMapping("/create-blog")
    public ModelAndView createFormBLog(){
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createBlog(@ModelAttribute("blog") Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        modelAndView.addObject("blog",new Blog());
        return modelAndView;
    }

    @GetMapping("/edit-blog/{id}")
    public ModelAndView editBLogForm(@PathVariable int id){
        Blog blog = blogService.finById(id);
        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editBlog(@ModelAttribute("blog") Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @GetMapping("/delete-blog/{id}")
    public String deleteBlog(@PathVariable int id){
        blogService.delete(id);
        return "redirect:/";

    }
}

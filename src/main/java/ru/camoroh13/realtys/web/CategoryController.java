package ru.camoroh13.realtys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.camoroh13.realtys.domain.Category;
import ru.camoroh13.realtys.domain.Publication;
import ru.camoroh13.realtys.service.CategoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class CategoryController extends HelperController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/admin/category")
    public String index(Map<String, Object> map, HttpServletRequest request) {
        List<Category> categoryList = categoryService.listAllCategory();
        Iterator<Category> itr = categoryList.iterator();
        map.put("path", request.getContextPath());
        map.put("parentId", 0);
        map.put("categoryList", categoryList);
        return "categories/category";
    }

    @RequestMapping(value = "/admin/category/add", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute("category") Category category,
                              BindingResult result, @RequestParam("parentId") Integer parentId) {
        if (parentId > 0) {
            Collection<Category> subcategories = new LinkedList<Category>();
            subcategories.add(category);
            categoryService.addSubcategory(parentId, subcategories);
        } else {
            categoryService.addCategory(category);
        }

        return "redirect:/admin/category";
    }

    @RequestMapping(value = "/admin/category/edit/{id}/{parentId}", method = RequestMethod.GET)
    public String editCategory(@PathVariable("id") int id, @PathVariable("parentId") int parentId, Map<String, Object> map,
                                  HttpServletRequest request) {
        Category category = categoryService.getCategory(id);
        if (category == null) {
            System.out.println("category " + id + " is NULL!!!");
        }

        List<Category> categoryList = categoryService.listAllCategory();

        map.put("path", request.getContextPath());
        map.put("categoryList", categoryList);
        map.put("category", category);
        map.put("parentId", parentId);
        return "categories/edit";
    }

    @RequestMapping(value = "/admin/category/edit/{id}", method = RequestMethod.POST)
    public String saveCategory(@PathVariable("id") Integer id, @RequestParam("parentId") Integer parentId, @ModelAttribute("category") Category category,
                               BindingResult result) {
        if (parentId > 0) {
            categoryService.addSubcategory(parentId, category);
        } else {
            categoryService.updateCategory(category);
        }
        return "redirect:/admin/category";
    }

    @RequestMapping("/admin/category/delete/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") Integer categoryId) {

        categoryService.removeCategory(categoryId);

        return "redirect:/admin/category";
    }

    @RequestMapping(value = "/category/{url}.html", method = RequestMethod.GET)
    public String byUrl(Map<String, Object> map,
                        @PathVariable("url") String url ) {
        Category category = categoryService.getByUrl(url);
        map = makePage(map, "index");
        map = makeSearchForm(map);
        map.put("publications", category != null ? category.getPublicationsList() : Collections.<Publication>emptySet());
        return "public/publicationsList";
    }

}

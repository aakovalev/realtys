package ru.camoroh13.realtys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.camoroh13.realtys.domain.Category;
import ru.camoroh13.realtys.domain.Publication;
import ru.camoroh13.realtys.domain.User;
import ru.camoroh13.realtys.service.CategoryService;
import ru.camoroh13.realtys.service.PublicationService;
import ru.camoroh13.realtys.service.UserService;

import java.util.*;

@Controller
public class PublicationController  extends HelperController {

    @Autowired
    PublicationService publicationService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @RequestMapping("/admin/publications")
    public String index(Map<String, Object> map) {
        map.put("publicationList", publicationService.listPublication());
        map.put("categoryList", categoryService.listAllCategory());
        map.put("parentId", new LinkedList());
        return "publications/publication";
    }

    @RequestMapping(value = "/admin/publications/add", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute("publication")Publication publication,
                              BindingResult result, @RequestParam("parentId") Integer[] parentId) {

        User user = userService.getUserByName(userDetailsService.toString());
        publication.setUser(user);

        publicationService.addPublication(publication);
        publication = publicationService.getByUrl(publication.getUrl());
        if (parentId.length > 0) {
            List<Category> categoryList = new LinkedList<Category>();
            for (Integer parent:parentId) {
                Category category = categoryService.getCategory(parent);
                categoryList.add(category);
            }
            publicationService.setCategoryList(publication, categoryList);
        }        

        return "redirect:/admin/publications";
    }

    @RequestMapping(value = "/admin/publications/edit/{id}", method = RequestMethod.GET)
    public String editPublication(@PathVariable("id") int id, Map<String, Object> map) {
        Publication publication = publicationService.getPublication(id);
        List<Category> categories = publication.getCategoriesList();
        List parentId = new ArrayList();
        if (categories.size() > 0) {
            for (Category category: categories) {
                parentId.add(category.getId());
            }
        }

        List<Category> categoryList = categoryService.listAllCategory();

        map.put("categoryList", categoryList);
        map.put("publication", publication);
        map.put("parentId", parentId);
        return "publications/edit";
    }

    @RequestMapping(value = "/admin/publications/edit/{id}", method = RequestMethod.POST)
    public String savePublication(@PathVariable("id") Integer id, @RequestParam("parentId") Integer[] parentId,
                                    @ModelAttribute("publication") Publication publication,
                                    BindingResult result) {
        List<Category> categoryList = new LinkedList<Category>();
        if (parentId.length > 0) {
            for (Integer parent:parentId) {
                Category category = categoryService.getCategory(parent);
                categoryList.add(category);
            }
        }

        User user = userService.getUserByName(userDetailsService.toString());
        publication.setUser(user);

        publicationService.update(publication);
        publication = publicationService.getPublication(publication.getId());
        
        publicationService.setCategoryList(publication, categoryList);
        return "redirect:/admin/publications";
    }

    @RequestMapping("/admin/publications/delete/{publicationId}")
    public String deleteUser(@PathVariable("publicationId") Integer publicationId) {

        publicationService.remove(publicationId);

         return "redirect:/admin/publications";
    }

    @RequestMapping(value = "/publication/{url}.html", method = RequestMethod.GET)
    public String byUrl(Map<String, Object> map,
                        @PathVariable("url") String url ) {
        Publication publication = publicationService.getByUrl(url);
        map = makePage(map, "index");
        map = makeSearchForm(map);
        map.put("publication", publication);
        return "public/publication";
    }

}

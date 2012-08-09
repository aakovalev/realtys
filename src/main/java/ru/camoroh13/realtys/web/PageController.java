package ru.camoroh13.realtys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.camoroh13.realtys.dao.PageDAO;
import ru.camoroh13.realtys.domain.Page;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * User: Konstantin
 * Date: 21.07.11
 * Time: 23:19
 */
@Controller
public class PageController {

    @Autowired
    PageDAO pageDAO;

    @RequestMapping("/admin/pages")
    public String index(Map<String, Object> map) {
        List<Page> pages = pageDAO.list();
        if (pages == null) {
            pages = new LinkedList<Page>();
        }
        map.put("pages", pages);
        return "pages/list";
    }

    @RequestMapping(value = "/admin/pages/add", method = RequestMethod.POST)
    public String addEstate(@ModelAttribute("estate") Page page,
                            Map<String, Object> map) {
        pageDAO.add(page);
        return "redirect:/admin/pages";
    }

    @RequestMapping(value = "/admin/pages/add", method = RequestMethod.GET)
    public String addEstate(Map<String, Object> map) {
        return "pages/add";
    }

    @RequestMapping(value = "/admin/pages/edit/{pageId}", method = RequestMethod.GET)
    public String edit( @PathVariable("pageId") Integer pageId,
                            Map<String, Object> map) {
        Page page = pageDAO.get(pageId);
        map.put("page", page);
        return "pages/edit";
    }

    @RequestMapping(value = "/admin/pages/edit/{pageId}", method = RequestMethod.POST)
    public String editPage( @PathVariable("pageId") Integer pageId,
                            @ModelAttribute("estate") Page page,
                            Map<String, Object> map) {
        page.setPageId(pageId);
        pageDAO.save(page);
        map.put("page", page);
        return "redirect:/admin/pages";
    }

    @RequestMapping(value = "/admin/pages/delete/{pageId}", method = RequestMethod.GET)
    public String delete( @PathVariable("pageId") Integer pageId,
                            Map<String, Object> map) {
        Page page = pageDAO.get(pageId);
        pageDAO.delete(page);
        return "redirect:/admin/pages";
    }

}

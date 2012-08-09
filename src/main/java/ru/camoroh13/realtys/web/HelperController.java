package ru.camoroh13.realtys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import ru.camoroh13.realtys.domain.*;
import ru.camoroh13.realtys.service.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: Konstantin
 * Date: 03.08.11
 */
@Controller
public class HelperController {

    @Autowired
    EstateCategoryService estateCategoryService;

    @Autowired
    EstateTypeService estateTypeService;

    @Autowired
    DistrictService districtService;

    @Autowired
    PageService pageService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    MessageSource messageSource;

    protected Map<String, Object> makePage(Map<String, Object> map, String url) {
        Page page = null;
        if (url != null) {
            page = pageService.get(url);
        }
        if (page != null) {
            map.put("metaTitle", page.getMetaTitle());
            map.put("metaDescription", page.getMetaDescription());
            map.put("metaKeywords", page.getMetaKeywords());
            map.put("url", page.getUrl());
            map.put("content", page.getText());
            map.put("title", page.getTitle());
        } else {
            map.put("metaTitle", getMessage("page.default.metaTitle"));
            map.put("metaDescription", getMessage("page.default.metaDescription"));
            map.put("metaKeywords", getMessage("page.default.metaKeywords"));
            map.put("url", "");
            map.put("content", "");
            map.put("title", "");
        }
        return map;
    }

    protected Map<String, Object> makeSearchForm(Map<String, Object> map) {
        List<EstateType> estateTypeList = estateTypeService.list();
        List<EstateCategory> estateCategoryList = estateCategoryService.list();
        List<District> districtList = districtService.list();
        map.put("districtList", districtList);
        map.put("estateTypeList", estateTypeList);
        map.put("estateCategoryList", estateCategoryList);
        map.put("services", getServicePublications());
        return map;
    }

    protected String getMessage(String messageKey) {
        return messageSource.getMessage(messageKey, null, null);
    }

    /**
     * Получаем список всех публикаций по услугам (url="services")
     */
    public Set<Publication> getServicePublications() {
        Category category = categoryService.getByUrl("services");
        return category != null ? category.getPublicationsList() : Collections.<Publication>emptySet();
    }
}

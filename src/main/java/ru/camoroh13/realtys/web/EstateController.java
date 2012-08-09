package ru.camoroh13.realtys.web;

import com.sun.istack.internal.NotNull;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import ru.camoroh13.realtys.domain.District;
import ru.camoroh13.realtys.domain.Estate;
import ru.camoroh13.realtys.domain.EstateCategory;
import ru.camoroh13.realtys.domain.EstateType;
import ru.camoroh13.realtys.service.EstateService;
import ru.camoroh13.realtys.service.FeedbackService;
import ru.camoroh13.realtys.service.ImageService;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * User: Konstantin
 * Date: 27.06.11
 * Time: 21:12
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class EstateController extends HelperController implements ServletContextAware {
    private static final Logger LOG = Logger.getLogger(EstateController.class.getName());
    
    private static final long MIN_FILE_SIZE = 50;
    public static final String DEFAULT_TEXT = " ";

    @Autowired
    EstateService estateService;

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    ServletContext servletContext;

    @Autowired
    ImageService imageService;

    private static final Integer LIMIT = 50;

    @RequestMapping("/admin/estate")
    public String index(Map<String, Object> map, HttpServletRequest request) {
        List<EstateType> estateTypeList = estateTypeService.list();
        List<EstateCategory> estateCategoryList = estateCategoryService.list();
        List<District> districtList = districtService.list();
        Long count = estateService.count();
        Integer start = 0;
        Integer pageId = 0;
        Map params = request.getParameterMap();
        if (params.containsKey("pageId")) {
            String page = request.getParameter("pageId").replaceAll("\\D", "");
            if (page.length() > 0) {
                pageId = new Integer(page);
                start = LIMIT * pageId;
            }
        }
        if (params.containsKey("code")) {
            String code = request.getParameter("code");
            if (code != null) {
                for (Estate estate: estateService.list()) {
                    if (code.equals(estate.getCode())) {
                        map.put("foundEstate", estate);
                    }
                }
            }
        }
        Integer desc = 0;
        String orderBy = "";
        if (params.containsKey("order") && params.containsKey("orderBy")) {
            try{
                desc = new Integer(request.getParameter("order"));
                orderBy = request.getParameter("orderBy");
                if (!"price".equals(orderBy) && !"date".equals(orderBy)
                        && !"floor".equals(orderBy) && !"square".equals(orderBy)
                        && !"rooms".equals(orderBy)) {
                    desc = 0;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        List<Estate> estateList = estateService.find(0, 0, 0, -1, 0, 0, desc, orderBy, start , LIMIT);
        map = makeSearchForm(map);
        map.put("estateList", estateList);
        map.put("districtList", districtList);
        map.put("estateTypeList", estateTypeList);
        map.put("estateCategoryList", estateCategoryList);
        map.put("limit", LIMIT);
        map.put("pageId", pageId);
        map.put("start", start);
        map.put("count", count);
        map.put("pageCount", Math.floor(count * 1.0 / LIMIT));
        map.put("orderBy", orderBy);
        map.put("order", desc);
        return "estate/estate";
    }

    @RequestMapping(value = "/admin/estate/editImages/{estateId}", method = RequestMethod.GET)
    public String editImages(Map<String, Object> map,
                             @PathVariable("estateId") Integer estateId,
                             HttpServletRequest request) {
        Estate estate = estateService.get(estateId);
        map.put("sessionId", request.getSession().getId());
        map.put("estate", estate);
        return "estate/editImages";
    }

    @RequestMapping(value = "/admin/estate/editImages/{estateId}", method = RequestMethod.POST)
    public String saveEditImages(Map<String, Object> map,
                                 HttpServletRequest request,
                                 @PathVariable("estateId") Integer estateId) {
        Estate estate = estateService.get(estateId);
        if (estate.getBenefits() == null) {
            estate.setBenefits("");
        }

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> itr = multipartRequest.getFileNames();
        while ( itr.hasNext()) {
            System.out.println("Uploaded file name : " + itr.next() );
        }
        MultipartFile file = multipartRequest.getFile("Filedata");
        try {
            if (file != null && file.getSize() > MIN_FILE_SIZE) {
                final String PATH = servletContext.getRealPath("/resources/thumbs") + "/";
                System.out.println("PATH = " + PATH);
                String fileName = new Date().getTime() + "";
                String smallFile = "s" + fileName + ".png";
                fileName = fileName + ".png";
                File newFile = new File(PATH + fileName);
                File newSmallFile = new File(PATH + smallFile);

                System.out.println("Image file info : " + newFile.getName() + " - " + newSmallFile.getName());

                OutputStream os = new FileOutputStream(newFile);
                OutputStream os2 = new FileOutputStream(newSmallFile);
                IOUtils.copy(file.getInputStream(), os);

                if (estate.getImage() == null || "".equals(estate.getImage()) || Estate.NO_IMAGE.equals(estate.getImage()) ) {
                    estate.setImage(fileName);
                } else {
                    List<ru.camoroh13.realtys.domain.Image> images = estate.getImages();
                    if (images == null) {
                        images = new LinkedList<ru.camoroh13.realtys.domain.Image>();
                    }
                    ru.camoroh13.realtys.domain.Image img = new ru.camoroh13.realtys.domain.Image();
                    img.setImage(fileName);
                    Long l = new Date().getTime();
                    img.setEstate(estate);
                    img.setImageId(Integer.parseInt( l.toString().substring(0, l.toString().length() - 3) ));
                    imageService.save(img);
                    images.add(img);
                    estate.setImages(images);
                }
                estateService.save(estate);

                BufferedInputStream is = new BufferedInputStream(new FileInputStream(newFile));
                rescale(is, os2, 125, 125, "png");

                is.close();
                os2.close();
                os.close();

                imageService.markImage(PATH + fileName, PATH + "watemark.png", 1, 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        map.put("sessionId", request.getSession().getId());
        map.put("estate", estate);
        return "estate/editImages";
    }

    @RequestMapping("/")
    public String indexPublic(Map<String, Object> map) {
        map = makePage(map, "index");
        map = makeSearchForm(map);
        List<Estate> specials = getSpecials();
        map.put("specials", specials);
        return "public/mainPage";
    }

    @RequestMapping("/page/{url}.html")
    public String page(Map<String, Object> map, @PathVariable("url") String url) {
        map = makePage(map, url);
        map = makeSearchForm(map);
        map.put("specials", getSpecials());
        map.put("complaintList", feedbackService.findAll());
        return "public/page";
    }

    private List<Estate> getSpecials() {
        List<Estate> specialsTemp = estateService.listSpecial();
        List<Estate> specials = new LinkedList<Estate>();
        for (Estate special : specialsTemp) {
            if (special.getImage() == null || "".equals(special.getImage())) {
                special.setImage(Estate.NO_IMAGE);
            }
            specials.add(special);
        }
        System.out.println("Specials size : " + specialsTemp.size() + " - " + specials.size() );
        return specials;
    }

    @RequestMapping("/estate/{id}")
    public String showEstate(Map<String, Object> map, @PathVariable("id") Integer id) {
        Estate estate = estateService.get(id);
        map = makePage(map, "estate");
        map = makeSearchForm(map);
        if (estate.getImage() == null || "".equals(estate.getImage())) {
            estate.setImage(Estate.NO_IMAGE);
        }
        map.put("estate", estate);
        return "public/estate";
    }

    @RequestMapping("/search")
    public String search(Map<String, Object> map, HttpServletRequest request) {
        map = makePage(map, "index");
        Map params = request.getParameterMap();
        Integer categoryId = 0;
        Integer start = 0;
        Integer pageId = 0;
        if (params.containsKey("pageId")) {
            String page = request.getParameter("pageId").replaceAll("\\D", "");
            if (page.length() > 0) {
                pageId = new Integer(page);
                start = LIMIT * pageId;
            }
        }
        if (params.containsKey("categoryId")) {
            String categoryIdS = request.getParameter("categoryId");
            if (categoryIdS.length() > 0) {
                try {
                    categoryId = new Integer(categoryIdS);
                } catch (Exception ex) {
                    //
                }
            }
        }
        Integer typeId = 0;
        if (params.containsKey("typeId")) {
            String typeIdS = request.getParameter("typeId");
            if (typeIdS.length() > 0) {
                try {
                    typeId = new Integer(typeIdS);
                } catch (Exception ex) {
                    //
                }
            }
        }
        Integer districtId = 0;
        if (params.containsKey("districtId")) {
            String districtIdS = request.getParameter("districtId");
            if (districtIdS.length() > 0) {
                try {
                    districtId = new Integer(districtIdS);
                } catch (Exception ex) {
                    //
                }
            }
        }
        Integer rooms = -1;
        if (params.containsKey("rooms")) {
            String roomsS = request.getParameter("rooms");
            if (roomsS.length() > 0) {
                try {
                    rooms = new Integer(roomsS);
                } catch (Exception ex) {
                    //
                }
            }
        }
        Integer minPrice = 0;
        if (params.containsKey("minPrice")) {
            String minPriceS = request.getParameter("minPrice").replaceAll("\\D", "");
            if (minPriceS.length() > 0) {
                try {
                    minPrice = new Integer(minPriceS);
                } catch (Exception ex) {
                    //
                }
            }
        }
        Integer maxPrice = 0;
        if (params.containsKey("maxPrice")) {
            String maxPriceS = request.getParameter("maxPrice").replaceAll("\\D", "");
            if (maxPriceS.length() > 0) {
                try {
                    maxPrice = new Integer(maxPriceS);
                } catch (Exception ex) {
                    //
                }
            }
        }
        Integer desc = 0;
        String orderBy = "";
        if (params.containsKey("order") && params.containsKey("orderBy")) {
            try{
                desc = new Integer(request.getParameter("order"));
                orderBy = request.getParameter("orderBy");
                if (!"price".equals(orderBy) && !"date".equals(orderBy)
                        && !"floor".equals(orderBy) && !"square".equals(orderBy)
                        && !"rooms".equals(orderBy)) {
                    desc = 0;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        List<Estate> estateList = estateService.find(categoryId, typeId, districtId, rooms, minPrice, maxPrice, desc, orderBy, start, LIMIT);
        map = makeSearchForm(map);
        int count = estateService.find(categoryId, typeId, districtId, rooms, minPrice, maxPrice, desc, orderBy, 0, 0).size();
        map.put("specials", getSpecials());
        map.put("limit", LIMIT);
        map.put("pageId", pageId );
        map.put("start", start);
        map.put("count", count);
        map.put("orderBy", orderBy);
        map.put("order", desc);
        map.put("pageCount", Math.floor(count * 1.0 / LIMIT));
        map.put("categoryId", categoryId);
        map.put("typeId", typeId);
        map.put("districtId", districtId);
        map.put("rooms", rooms);
        map.put("minPrice", minPrice);
        map.put("maxPrice", maxPrice);
        map.put("estateList", estateList);
        return "public/index";
    }

    @RequestMapping(value = "/admin/estate/deleteImages/{id}")
    public String deleteImages(Map<String, Object> map, @PathVariable("id") Integer id) {
        ru.camoroh13.realtys.domain.Image image = imageService.get(id);
        Integer estateId = image.getEstate().getEstateId();
        imageService.delete(image);
        return "redirect:/admin/estate/editImages/" + estateId;
    }

    @RequestMapping(value = "/admin/estate/deleteImage/{id}")
    public String deleteImage(Map<String, Object> map, @PathVariable("id") Integer id) {
        Estate estate = estateService.get(id);
        if (estate.getImage() != null && !"".equals(estate.getImage()) && !Estate.NO_IMAGE.equals(estate.getImage()) ) {
            if (estate.getImages().size() > 0 ) {
                ru.camoroh13.realtys.domain.Image image = estate.getImages().get(0);
                estate.setImage(image.getImage());
                imageService.delete(image);
            } else {
                estate.setImage(Estate.NO_IMAGE);
                estateService.save(estate);
            }
        }
        return "redirect:/admin/estate/editImages/" + id;
    }

    @RequestMapping(value = "/admin/estate/addEstate", method = RequestMethod.POST)
    public String addEstate(@ModelAttribute("estate") Estate estate,
                            @RequestParam("estateCategoryId") Integer estateCategoryId,
                            @RequestParam("estateTypeId") Integer estateTypeId,
                            @RequestParam("districtId") Integer districtId,
                            @RequestParam("dateStamp") String dateAsText,
                            @RequestParam("description") String description,
                            Map<String, Object> map) {
        District district = districtService.get(districtId);
        EstateCategory estateCategory = estateCategoryService.get(estateCategoryId);
        EstateType estateType = estateTypeService.get(estateTypeId);
        estate = makeEstate(estate);
        estate.setDistrict(district);
        estate.setEstateCategory(estateCategory);
        estate.setEstateType(estateType);
        estate.setDate(toDate(dateAsText));
        estate.setBenefits(description);
        estateService.add(estate);
        List<Estate> estateList = estateService.list();
        System.out.println("estateList = " + estateList.size());
        long count = estateService.count();
        map.put("pageId", 0);
        map.put("estateList", estateList);
        map.put("pageCount", Math.floor(count * 1.0 / LIMIT));
        return "estate/estateList";
    }

    @RequestMapping(value = "/admin/estate/edit/{estateId}", method = RequestMethod.POST)
    public String editSaveEstate(@RequestParam("estateCategoryId") Integer estateCategoryId,
                                 @RequestParam("estateTypeId") Integer estateTypeId,
                                 @RequestParam("districtId") Integer districtId,
                                 @RequestParam("dateStamp") String dateAsText,
                                 @RequestParam("priceStr") String priceAsText,
                                 @RequestParam("floor") String floorAsText,
                                 @RequestParam("maxFloor") String maxFloorAsText,
                                 @RequestParam("square") String squareAsText,
                                 @RequestParam("rooms") Integer rooms,
                                 @RequestParam("special") Boolean special,
                                 @RequestParam("code") String code,
                                 @RequestParam("benefits") String benefits,
                                 @RequestParam("address") String address,
                                 @PathVariable("estateId") Integer estateId,
                                 HttpServletRequest request,
                                 Map<String, Object> map) throws IOException {
        District district = districtService.get(districtId);
        EstateCategory estateCategory = estateCategoryService.get(estateCategoryId);
        EstateType estateType = estateTypeService.get(estateTypeId);
        Estate estate = estateService.get(estateId);
        if (estate != null) {
            priceAsText = priceAsText.replaceAll("\\D", "");
            estate.setCode(code);
            estate.setDate(toDate(dateAsText));
            estate.setAddress(address);
            estate.setSquare(toInteger(squareAsText));
            estate.setFloor(toInteger(floorAsText));
            estate.setMaxFloor(toInteger(maxFloorAsText));
            estate.setPrice(toInteger(priceAsText));
            estate.setRooms(rooms);
            estate.setSpecial(special);
            estate.setEstateCategory(estateCategory);
            estate.setEstateType(estateType);
            estate.setDistrict(district);
            estate.setBenefits(benefits);
            estateService.save(estate);
        }
        return "redirect:/admin/estate";
    }

    private Estate makeEstate(Estate estate) {
        if (estate.getBenefits() == null) {
            estate.setBenefits(DEFAULT_TEXT);
        }
        if (estate.getText() == null) {
            estate.setText(DEFAULT_TEXT);
        }
        if (estate.getAddress() == null) {
            estate.setAddress(DEFAULT_TEXT);
        }
        if (estate.getPrice() == null) {
            estate.setPrice(0);
        }
        if (estate.getCode() == null ) {
            estate.setCode(new Date().getTime() + "");
        }
        return estate;
    }

    @RequestMapping(value = "/admin/estate/addImage/{estateId}", method = RequestMethod.POST)
    public String addImageEstate(@PathVariable("estateId") Integer estateId,
                                 HttpServletRequest request,
                                 Map<String, Object> map) throws IOException {
        Estate estate = estateService.get(estateId);

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        ru.camoroh13.realtys.domain.Image image = new ru.camoroh13.realtys.domain.Image();
        if (file != null && file.getSize() > MIN_FILE_SIZE) {
            final String PATH = servletContext.getRealPath("/resources/thumbs") + "/";
            String fileName = new Date().getTime() + "";
            String smallFile = "s" + fileName + ".png";

            fileName = fileName + ".png";
            File newFile = new File(PATH + fileName);
            File newSmallFile = new File(PATH + smallFile);

            OutputStream os = new FileOutputStream(newFile);
            OutputStream os2 = new FileOutputStream(newSmallFile);

            IOUtils.copy(file.getInputStream(), os);

            image.setImage(fileName);

            BufferedInputStream is = new BufferedInputStream(new FileInputStream(newFile));
            rescale(is, os2, 200, 200, "png");

            is.close();
            os2.close();
            os.close();
            imageService.markImage(PATH + fileName, PATH + "watemark.png", 1 , 1);
        }
        List<ru.camoroh13.realtys.domain.Image> images = estate.getImages();
        if (images == null) {
            images = new LinkedList<ru.camoroh13.realtys.domain.Image>();
        }
        estate.setImages(images);
        estateService.save(estate);
        return "redirect:/admin/estate";
    }

    @RequestMapping(value = "/admin/estate/edit/{estateId}", method = RequestMethod.GET)
    public String editEstate(@PathVariable("estateId") Integer estateId,
                             Map<String, Object> map) {
        Estate estate = estateService.get(estateId);
        List<District> districtList = districtService.list();
        List<EstateCategory> estateCategoryList = estateCategoryService.list();
        List<EstateType> estateTypeList = estateTypeService.list();
        Format formatter = new SimpleDateFormat("dd.MM.yyyy");
        String date = formatter.format(estate.getDate());

        map.put("estate", estate);
        map.put("date", date);
        map.put("estateTypeList", estateTypeList);
        map.put("estateCategoryList", estateCategoryList);
        map.put("districtList", districtList);
        return "estate/editEstate";
    }

    @RequestMapping(value = "/admin/estate/addEstateCategory", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute("estateCategory") EstateCategory estateCategory, Map<String, Object> map) {
        estateCategoryService.add(estateCategory);
        List<EstateCategory> estateCategoryList = estateCategoryService.list();
        map.put("estateCategoryList", estateCategoryList);
        return "estate/estateCategoryList";
    }

    @RequestMapping(value = "/admin/estate/addEstateType", method = RequestMethod.POST)
    public String addType(@ModelAttribute("estateType") EstateType estateType, Map<String, Object> map) {
        estateTypeService.add(estateType);
        List<EstateType> estateTypeList = estateTypeService.list();
        map.put("estateTypeList", estateTypeList);
        return "estate/estateTypeList";
    }

    @RequestMapping(value = "/admin/estate/addDistrict", method = RequestMethod.POST)
    public String addDistrict(@ModelAttribute("district") District district, Map<String, Object> map) {
        districtService.add(district);
        List<District> districtList = districtService.list();
        map.put("districtList", districtList);
        return "estate/districtList";
    }

    @RequestMapping("/admin/estate/delete/{estateId}")
    public String delete(@PathVariable("estateId") Integer estateId) {
        estateService.delete(estateService.get(estateId));
        return "redirect:/admin/estate";
    }

    @RequestMapping("/import")
    public String importEstates() {
        String PATH = "/var/www/admin/data/www/import/";
        File file = new File(PATH + "OutputXML.xml");
        if (!file.exists()) {
            PATH = servletContext.getRealPath("/import") + "/";
            file = new File(PATH + "OutputXML.xml");
        }
        estateService.importEstate(file);
        return "public/import";
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    private static void rescale(@NotNull InputStream input,
                                @NotNull OutputStream output,
                                int tw,
                                int th,
                                String ext) throws IOException {
        // Create the image
        BufferedImage image = new BufferedImage(tw, th, BufferedImage.TYPE_INT_RGB);
        // Create the graphics
        Graphics2D graphics = image.createGraphics();
        try {
            // Set rendering hints
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            // Open the source image
            @NotNull BufferedImage source = ImageIO.read(input);
            // Get width & geight
            int w = source.getWidth(), h = source.getHeight();
            // Calculate the scale
            double scale = Math.min((double) tw / (double) w, (double) th / (double) h);
            // Calculate the coordinates
            int x = (int) (((double) tw - (double) w * scale) / 2.0d), y = (int) (((double) th - (double) h * scale) / 2.0d);
            // Get the scaled instance
            @NotNull Image scaled = source.getScaledInstance((int) (w * scale), (int) (h * scale), Image.SCALE_SMOOTH);
            // Set the color
            graphics.setColor(Color.WHITE);
            // Paint the white rectangle
            graphics.fillRect(0, 0, tw, th);
            // Draw the image
            graphics.drawImage(scaled, x, y, null);
        } finally {
            // Always dispose the graphics
            graphics.dispose();
        }
        // Write the rescaled image
        ImageIO.write(image, ext, output);
    }

    private Integer toInteger(String integerAsText) {
        Integer parsedInteger = 0;
        try {
            parsedInteger = Integer.parseInt(integerAsText);
        }
        catch (NumberFormatException ex) {
            LOG.warning(String.format("Unable to parse '%s;' to integer", integerAsText));
        }
        return parsedInteger;
    }

    private Date toDate(String dateAsText) {
        Date parsedDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            parsedDate = format.parse(dateAsText);
        } catch (ParseException e) {
            LOG.warning(String.format("Unable to parse '%s' to date", dateAsText));
        }
        return parsedDate;
    }
}

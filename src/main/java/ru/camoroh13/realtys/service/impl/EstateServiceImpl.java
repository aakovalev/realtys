package ru.camoroh13.realtys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.camoroh13.realtys.dao.*;
import ru.camoroh13.realtys.domain.*;
import ru.camoroh13.realtys.service.EstateService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User: Konstantin
 * Date: 28.06.11
 * Time: 23:41
 */
@Service
public class EstateServiceImpl implements EstateService {

    @Autowired
    EstateDAO estateDAO;

    @Autowired
    EstateTypeDAO estateTypeDAO;

    @Autowired
    EstateCategoryDAO estateCategoryDAO;

    @Autowired
    DistrictDAO districtDAO;

    @Autowired
    ImageDAO imageDAO;

    @Override
    public Estate get(Integer id) {
        return estateDAO.get(id);
    }

    @Override
    public void add(Estate estate) {
        estateDAO.add(estate);
    }

    @Override
    public void save(Estate estate) {
        estateDAO.save(estate);
    }

    @Override
    public void delete(Estate estate) {
        estateDAO.delete(estate);
    }

    @Override
    public List<Estate> list() {
        return estateDAO.list();
    }

    @Override
    public List<Estate> find(Integer categoryId, Integer typeId, Integer districtId,
                             Integer rooms, Integer minPrice, Integer maxPrice,
                             Integer desc, String orderBy,
                             Integer start, Integer limit) {
        EstateCategory estateCategory = null;
        EstateType estateType = null;
        District district = null;
        if (categoryId != null && categoryId > 0) {
            estateCategory = estateCategoryDAO.get(categoryId);
        }
        if (typeId != null && typeId > 0) {
            estateType = estateTypeDAO.get(typeId);
        }
        if (districtId != null && districtId > 0) {
            district = districtDAO.get(districtId);
        }
        if (desc == null) {
            desc = 0;
        }

        List<Estate> specials = estateDAO.find(estateCategory, estateType, district, rooms, minPrice, maxPrice, desc, orderBy, start, limit);

        return specials;
    }

    @Override
    public List<Estate> listSpecial() {
        List<Estate> specials = estateDAO.listSpecial();
        Set<Estate> estates = new HashSet<Estate>();
        for (Estate estate : specials) {
            estates.add(estate);
        }
        List<Estate> estateList = new LinkedList<Estate>();
        for (Estate estate : estates) {
            estateList.add(estate);
        }
        System.out.println("All Specials size : " + specials.size() + " " + estates.size() + " " + estateList.size());
        return estateList;
    }

    @Override
    public synchronized void importEstate(File file) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        assert db != null;
        Document doc = null;
        try {
            doc = db.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert doc != null;
        doc.getDocumentElement().normalize();

        Map<Integer, EstateCategory> categories = parseCategories(doc);
        Map<Integer, EstateType> types = parseTypes(doc);
        Map<Integer, District> districts = parseDistricts(doc);
        List<Estate> estates = parseEstates(doc, categories, types, districts);

    }

    @Override
    public Long count() {
        return estateDAO.findLearnerCount();
    }

    private List<Estate> parseEstates(Document doc, Map<Integer, EstateCategory> categories,
                                      Map<Integer, EstateType> types, Map<Integer, District> districts) {
        List<Estate> list = new LinkedList<Estate>();
        NodeList nodeLst = doc.getElementsByTagName("Item");

        for (int s = 0; s < nodeLst.getLength(); s++) {
            Node fstNode = nodeLst.item(s);
            if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                Element fstElmnt = (Element) fstNode;

                String id = fstElmnt.getAttribute("id");
                id = id.replaceAll("\\D", "");
                String text = fstElmnt.getAttribute("Information");
                String benefits = fstElmnt.getAttribute("Repair");
                Integer categoryId = new Integer(fstElmnt.getAttribute("categoryId").replaceAll("\\D", ""));

                Integer typeId = new Integer(fstElmnt.getAttribute("typeId").replaceAll("\\D", ""));
                Integer districtId = new Integer(fstElmnt.getAttribute("districtId").replaceAll("\\D", ""));
                NodeList codeNodes = fstElmnt.getElementsByTagName("Code");
                Integer code = new Integer(codeNodes.item(0).getTextContent().replaceAll("\\D", ""));
                NodeList dateNodes = fstElmnt.getElementsByTagName("Date");
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                Date date;
                try {
                    date = format.parse( dateNodes.item(0).getTextContent());
                } catch (ParseException e) {
                    date = new Date();
                }

                Integer rooms = 0;
                try {
                NodeList roomsNodes = fstElmnt.getElementsByTagName("Rooms");
                String roomsStr = roomsNodes.item(0).getTextContent().replaceAll("\\D", "");
                roomsStr.length();
                    rooms = new Integer(roomsNodes.item(0).getTextContent().replaceAll("\\D", ""));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                NodeList addressNodes = fstElmnt.getElementsByTagName("Adress");
                String address = addressNodes.item(0).getTextContent();

                NodeList squareNodes = fstElmnt.getElementsByTagName("Square");
                Integer square = new Integer(squareNodes.item(0).getTextContent().replaceAll("\\D", ""));

                NodeList floorNodes = fstElmnt.getElementsByTagName("Floor");
                String floorString = floorNodes.item(0).getTextContent();
                char c = '\\';
                System.out.println("floorString = " + floorString + "  c = " + c );
                int pos = floorString.indexOf('\\');
                if (pos > -1) {
                    floorString = floorString.substring(0, pos);
                }
                Integer floor;
                try {
                    floor = new Integer(floorString.replaceAll("\\D", ""));
                } catch (RuntimeException ex ){
                    floor = 0;
                }

                NodeList maxFloorNodes = fstElmnt.getElementsByTagName("Maxfloor");
                floorString = maxFloorNodes.item(0).getTextContent();
                pos = floorString.indexOf('\\');
                if (pos > -1) {
                    floorString = floorString.substring(pos, floorString.length());
                }
                Integer maxFloor;
                try {
                    maxFloor = new Integer(floorString.replaceAll("\\D", ""));
                } catch (RuntimeException ex ){
                    maxFloor = 0;
                }

                NodeList priceNodes = fstElmnt.getElementsByTagName("Price");
                Integer price = new Integer(priceNodes.item(0).getTextContent().replaceAll("\\D", ""));

                NodeList specialNodes = fstElmnt.getElementsByTagName("Special");
                Boolean special = (new Integer(specialNodes.item(0).getTextContent().replaceAll("\\D", "")) > 1) ? true : false;

                NodeList withPhotoNodes = fstElmnt.getElementsByTagName("WithPhoto");
                boolean withPhoto = false;
                if (withPhotoNodes != null && withPhotoNodes.getLength() > 0) {
                    withPhoto = parseWithPhotoTag(withPhotoNodes.item(0).getTextContent());
                }

                NodeList repairNodes = fstElmnt.getElementsByTagName("Repair");
                benefits = repairNodes.item(0).getTextContent();

                Estate estate = new Estate();
                estate.setEstateCategory(categories.get(categoryId));
                estate.setEstateType(types.get(typeId));
                estate.setDistrict(districts.get(districtId));
                estate.setCode(code.toString());
                estate.setDate(date);
                estate.setAddress(address);
                estate.setSquare(square);
                estate.setFloor(floor);
                estate.setMaxFloor(maxFloor);
                estate.setPrice(price);
                estate.setSpecial(special);
                estate.setText(text);
                estate.setBenefits(benefits);
                estate.setRooms(rooms);
                estate.setWithPhoto(withPhoto);

                Image image = imageDAO.get(1);
                System.out.println("ImageId = " + image.getImageId());
                if (image.getImageId() == null) {
                    image.setImageId(1);
                }
                List<Image> images = new LinkedList<Image>();
                images.add(image);
                estate.setImages(images);

                Estate oldEstate = estateDAO.getByCode(estate.getCode());
                if (oldEstate == null) {
                    estateDAO.add(estate);
                } else {
                    estate.setEstateId(oldEstate.getEstateId());
                    if (oldEstate.hasImage()) {
                        estate.setImage(oldEstate.getImage());
                    }
                    estate.setImages(oldEstate.getImages());
                    estateDAO.save(estate);
                }
            }
        }
        return list;
    }

    private Map<Integer, District> parseDistricts(Document doc) {
        Map<Integer, District> map = new HashMap<Integer, District>();
        NodeList nodeLst = doc.getElementsByTagName("District");

        for (int s = 0; s < nodeLst.getLength(); s++) {
            Node fstNode = nodeLst.item(s);
            if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                Element fstElmnt = (Element) fstNode;

                String id = fstElmnt.getAttribute("id");
                id = id.replaceAll("\\D", "");
                String categoryName = fstElmnt.getTextContent();

                District district = new District();
                district.setName(categoryName);
                Integer districtId = new Integer(id);
                district.setDistrictId(districtId);

                District estateDistrict;
                try {
                    estateDistrict = districtDAO.get(districtId);
                    if (!estateDistrict.getName().equals(categoryName)) {
                        estateDistrict.setName(categoryName);
                        districtDAO.save(estateDistrict);
                    }
                } catch (RuntimeException ex) {
                    estateDistrict = new District();
                    estateDistrict.setDistrictId(districtId);
                    estateDistrict.setName(categoryName);
                    districtDAO.add(estateDistrict);
                }

                map.put(districtId, estateDistrict);
            }
        }
        return map;
    }

    private Map<Integer, EstateType> parseTypes(Document doc) {
        Map<Integer, EstateType> map = new HashMap<Integer, EstateType>();
        NodeList nodeLst = doc.getElementsByTagName("type");

        for (int s = 0; s < nodeLst.getLength(); s++) {
            Node fstNode = nodeLst.item(s);
            if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                Element fstElmnt = (Element) fstNode;

                String id = fstElmnt.getAttribute("id");
                id = id.replaceAll("\\D", "");
                String typeName = fstElmnt.getTextContent();

                EstateType type = new EstateType();
                type.setName(typeName);
                Integer typeId = new Integer(id);
                type.setEstateTypeId(typeId);

                EstateType estateType;
                try {
                    estateType = estateTypeDAO.get(typeId);
                    if (!estateType.getName().equals(typeName)) {
                        estateType.setName(typeName);
                        estateTypeDAO.save(estateType);
                    }
                } catch (RuntimeException ex) {
                    estateType = new EstateType();
                    estateType.setEstateTypeId(typeId);
                    estateType.setName(typeName);
                    estateTypeDAO.add(estateType);
                }

                map.put(typeId, estateType);
            }
        }
        return map;
    }

    private Map<Integer, EstateCategory> parseCategories(Document doc) {
        Map<Integer, EstateCategory> map = new HashMap<Integer, EstateCategory>();
        System.out.println("Root element " + doc.getDocumentElement().getNodeName());
        NodeList nodeLst = doc.getElementsByTagName("category");
        System.out.println("Information of all employees");

        for (int s = 0; s < nodeLst.getLength(); s++) {

            Node fstNode = nodeLst.item(s);

            if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                Element fstElmnt = (Element) fstNode;

                String id = fstElmnt.getAttribute("id");
                id = id.replaceAll("\\D", "");
                String categoryName = fstElmnt.getTextContent();

                EstateCategory category = new EstateCategory();
                category.setName(categoryName);
                Integer categoryId = new Integer(id);
                category.setEstateCategoryId(categoryId);

                EstateCategory estateCategory;

                try {
                    estateCategory = estateCategoryDAO.get(categoryId);
                    if (!estateCategory.getName().equals(categoryName)) {
                        estateCategory.setName(categoryName);
                        estateCategoryDAO.save(estateCategory);
                    }
                } catch (RuntimeException ex) {
                    estateCategory = new EstateCategory();
                    estateCategory.setEstateCategoryId(categoryId);
                    estateCategory.setName(categoryName);
                    estateCategoryDAO.add(estateCategory);
                }

                map.put(categoryId, estateCategory);
            }
        }
        return map;
    }

    private static Boolean parseWithPhotoTag(String tagValue) {
        if ("истина".equalsIgnoreCase(tagValue)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}

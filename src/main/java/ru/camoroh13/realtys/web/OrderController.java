package ru.camoroh13.realtys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import ru.camoroh13.realtys.domain.*;
import ru.camoroh13.realtys.service.FeedbackService;
import ru.camoroh13.realtys.service.OrderServices;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * User: Konstantin
 * Date: 03.08.11
 */
@Controller
public class OrderController extends HelperController implements ServletContextAware {

    @Autowired
    ServletContext servletContext;

    @Autowired
    private OrderServices orderServices;

    @Autowired
    private FeedbackService feedbackService;

    private String okMessage = "Ваша заявка принята. С Вами свяжется наш менеджер для уточнения деталей.";

    @RequestMapping(value = "/deliveryOrder", method = RequestMethod.GET)
    public String getDeliveryOrder(Map<String, Object> map) {
        map = makePage(map, "index");
        map = makeSearchForm(map);
        Long l = new Date().getTime();
        Integer i = l.intValue();
        map.put("orderId", Math.abs(i));
        return "public/order/delivery";
    }

    @RequestMapping(value = "/deliveryOrder/editImages/{orderId}", method = RequestMethod.POST)
    public String addDeliveryOrderImage(Map<String, Object> map,
                                 HttpServletRequest request,
                                 @PathVariable("orderId") Integer orderId) {
        DeliveryOrder order = orderServices.getDelivery(orderId);

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> itr = multipartRequest.getFileNames();
        while ( itr.hasNext()) {
            System.out.println("Uploaded file name : " + itr.next() );
        }

        MultipartFile file = multipartRequest.getFile("Filedata");
        try {
            if (file != null && file.getBytes().length > 50) {
                byte[] bytes = file.getBytes();

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

                os.write(bytes);

                    List<DeliveryImage> images = order.getImages();
                    if (images == null) {
                        images = new LinkedList<DeliveryImage>();
                    }
                    DeliveryImage img = new DeliveryImage();
                    img.setImage(fileName);
                    Long l = new Date().getTime();
                    img.setDeliveryOrder(order);
                    img.setImageId(Integer.parseInt( l.toString().substring(0, l.toString().length() - 3) ));
                    orderServices.save(img);
                    images.add(img);
                    order.setImages(images);

                orderServices.save(order);

                InputStream is = new FileInputStream(newFile);

                is.close();
                os2.flush();
                os2.close();
                os.flush();
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "common/ok";
    }

    @RequestMapping(value = "/evaluationOrder/editImages/{orderId}", method = RequestMethod.POST)
    public String addEvaluationOrderImage(Map<String, Object> map,
                                 HttpServletRequest request,
                                 @PathVariable("orderId") Integer orderId) {
        EvaluationOrder order = orderServices.getEvaluation(orderId);

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> itr = multipartRequest.getFileNames();
        while ( itr.hasNext()) {
            System.out.println("Uploaded file name : " + itr.next() );
        }
        MultipartFile file = multipartRequest.getFile("Filedata");
        try {
            if (file != null && file.getBytes().length > 50) {
                byte[] bytes = file.getBytes();

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

                os.write(bytes);

                    List<EvaluationImage> images = order.getImages();
                    if (images == null) {
                        images = new LinkedList<EvaluationImage>();
                    }
                    EvaluationImage img = new EvaluationImage();
                    img.setImage(fileName);
                    Long l = new Date().getTime();
                    img.setEvaluationOrder(order);
                    img.setImageId(Integer.parseInt( l.toString().substring(0, l.toString().length() - 3) ));
                    orderServices.save(img);
                    images.add(img);
                    order.setImages(images);

                orderServices.save(order);

                InputStream is = new FileInputStream(newFile);

                is.close();
                os2.flush();
                os2.close();
                os.flush();
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "common/ok";
    }

    @RequestMapping(value = "/rentOrder/editImages/{orderId}", method = RequestMethod.POST)
    public String addRentOrderImage(Map<String, Object> map,
                                 HttpServletRequest request,
                                 @PathVariable("orderId") Integer orderId) {
        RentOrder order = orderServices.getRent(orderId);

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> itr = multipartRequest.getFileNames();
        while ( itr.hasNext()) {
            System.out.println("Uploaded file name : " + itr.next() );
        }
        MultipartFile file = multipartRequest.getFile("Filedata");
        try {
            if (file != null && file.getBytes().length > 50) {
                byte[] bytes = file.getBytes();

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
                os.write(bytes);

                    List<RentImage> images = order.getImages();
                    if (images == null) {
                        images = new LinkedList<RentImage>();
                    }
                    RentImage img = new RentImage();
                    img.setImage(fileName);
                    Long l = new Date().getTime();
                    img.setRentOrder(order);
                    img.setImageId(Integer.parseInt( l.toString().substring(0, l.toString().length() - 3) ));
                    orderServices.save(img);
                    images.add(img);
                    order.setImages(images);

                orderServices.save(order);

                InputStream is = new FileInputStream(newFile);

                is.close();
                os2.flush();
                os2.close();
                os.flush();
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "common/ok";
    }

    @RequestMapping(value = "/deliveryOrder", method = RequestMethod.POST)
    public String addDeliveryOrder(@ModelAttribute("deliveryOrder")DeliveryOrder deliveryOrder,
                                   @RequestParam("districtId") Integer districtId,
                                   Map<String, Object> map) {
        District district = districtService.get(districtId);
        deliveryOrder.setDate(new Date());
        deliveryOrder.setDistrict(district);
        if (deliveryOrder.getAddress() == null) {
            deliveryOrder.setAddress("");
        }
        if (deliveryOrder.getEmail() == null) {
            deliveryOrder.setEmail("");
        }
        if (deliveryOrder.getFloor() == null) {
            deliveryOrder.setFloor(0);
        }
        if (deliveryOrder.getMaxFloor() == null) {
            deliveryOrder.setMaxFloor(0);
        }
        if (deliveryOrder.getName() == null) {
            deliveryOrder.setName("");
        }
        if (deliveryOrder.getPeriod() == null) {
            deliveryOrder.setPeriod(0);
        }
        if (deliveryOrder.getPhone() == null) {
            deliveryOrder.setPhone("");
        }
        if (deliveryOrder.getPrice() == null) {
            deliveryOrder.setPrice(0);
        }
        if (deliveryOrder.getRooms() == null) {
            deliveryOrder.setRooms(0);
        }
        if (deliveryOrder.getText() == null) {
            deliveryOrder.setText("");
        }
        orderServices.add(deliveryOrder);
        map = makePage(map, "index");
        map = makeSearchForm(map);
        try {
            String subject = getMessage("order.notification.subject") ;
            String content = getMessage("delivery.order.notification.content");
            feedbackService.sendMessage(subject, content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        map.put("message", okMessage);
        map.put("id", deliveryOrder.getDeliveryOrderId());
        return "public/order/id";
    }

    @RequestMapping(value = "/evaluationOrder", method = RequestMethod.GET)
    public String getEvaluationOrder(Map<String, Object> map) {
        map = makePage(map, "index");
        map = makeSearchForm(map);
        Long l = new Date().getTime();
        Integer i = l.intValue();
        map.put("orderId", Math.abs(i));
        return "public/order/evaluation";
    }

    @RequestMapping(value = "/evaluationOrder", method = RequestMethod.POST)
    public String addEvaluationOrder(@ModelAttribute("evaluationOrder")EvaluationOrder evaluationOrder,
                                    @RequestParam("estateCategoryId") Integer estateCategoryId,
                                    @RequestParam("districtId") Integer districtId,
                                     Map<String, Object> map) {
        EstateCategory estateCategory = estateCategoryService.get(estateCategoryId);
        District district = districtService.get(districtId);
        evaluationOrder.setDate(new Date());
        evaluationOrder.setEstateCategory(estateCategory);
        evaluationOrder.setDistrict(district);
        if (evaluationOrder.getEmail() == null) {
            evaluationOrder.setEmail("");
        }
        if (evaluationOrder.getFloor() == null) {
            evaluationOrder.setFloor(0);
        }
        if (evaluationOrder.getMaxFloor() == null) {
            evaluationOrder.setMaxFloor(0);
        }
        if (evaluationOrder.getName() == null) {
            evaluationOrder.setName("");
        }
        if (evaluationOrder.getPhone() == null) {
            evaluationOrder.setPhone("");
        }
        if (evaluationOrder.getRooms() == null) {
            evaluationOrder.setRooms(0);
        }
        if (evaluationOrder.getText() == null) {
            evaluationOrder.setText("");
        }
        orderServices.add(evaluationOrder);
        try {
            String subject = getMessage("order.notification.subject") ;
            String content = getMessage("evaluation.order.notification.content");
            feedbackService.sendMessage(subject, content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        map = makePage(map, "index");
        map = makeSearchForm(map);
        map.put("message", okMessage);
        return "public/order/success";
    }

    @RequestMapping(value = "/rentOrder", method = RequestMethod.GET)
    public String getRentOrder(Map<String, Object> map) {
        map = makePage(map, "index");
        map = makeSearchForm(map);
        Long l = new Date().getTime();
        Integer i = l.intValue();
        map.put("orderId", Math.abs(i));
        return "public/order/rent";
    }

    @RequestMapping(value = "/rentOrder", method = RequestMethod.POST)
    public String addRentOrder(@ModelAttribute("rentOrder")RentOrder rentOrder,
                               @RequestParam("districtId") Integer districtId,
                               Map<String, Object> map) {
        District district = districtService.get(districtId);
        rentOrder.setDate(new Date());
        rentOrder.setDistrict(district);
        if (rentOrder.getEmail() == null) {
            rentOrder.setEmail("");
        }
        if (rentOrder.getMaxPrice() == null) {
            rentOrder.setMaxPrice(0);
        }
        if (rentOrder.getMinPrice() == null) {
            rentOrder.setMinPrice(0);
        }
        if (rentOrder.getName() == null) {
            rentOrder.setName("");
        }
        if (rentOrder.getPeriod() == null) {
            rentOrder.setPeriod(0);
        }
        if (rentOrder.getPhone() == null) {
            rentOrder.setPhone("");
        }
        if (rentOrder.getRooms() == null) {
            rentOrder.setRooms(0);
        }
        if (rentOrder.getText() == null) {
            rentOrder.setText("");
        }
        if (rentOrder.getWho() == null) {
            rentOrder.setWho("");
        }
        orderServices.add(rentOrder);
        try {
            String subject = getMessage("order.notification.subject") ;
            String content = getMessage("rent.order.notification.content");
            feedbackService.sendMessage(subject, content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        map = makePage(map, "index");
        map = makeSearchForm(map);
        map.put("message", okMessage);
        return "public/order/success";
    }

    @RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
    public String getOrders(Map<String, Object> map) {
        List<DeliveryOrder> deliveryOrders = orderServices.listDelivery();
        List<EvaluationOrder> evaluationOrders = orderServices.listEvaluation();
        List<RentOrder> rentOrders = orderServices.listRent();
        map.put("deliveryOrders", deliveryOrders);
        map.put("evaluationOrders", evaluationOrders);
        map.put("rentOrders", rentOrders);
        return "/order/list";
    }

    @RequestMapping(value = "/admin/rentOrders/delete/{rentId}", method = RequestMethod.GET)
    public String deleteRent( @PathVariable("rentId") Integer rentId) {
        RentOrder rentOrder = orderServices.getRent(rentId);
        orderServices.delete(rentOrder);
        return "redirect:/admin/orders";
    }

    @RequestMapping(value = "/admin/rentOrders/{rentId}", method = RequestMethod.GET)
    public String getRent( @PathVariable("rentId") Integer rentId,
                            Map<String, Object> map) {
        RentOrder rentOrder = orderServices.getRent(rentId);
        map.put("rent", rentOrder);
        return "/order/rent";
    }

    @RequestMapping(value = "/admin/deliveryOrders/delete/{deliveryId}", method = RequestMethod.GET)
    public String deleteDelivery( @PathVariable("deliveryId") Integer deliveryId) {
        DeliveryOrder deliveryOrder = orderServices.getDelivery(deliveryId);
        orderServices.delete(deliveryOrder);
        return "redirect:/admin/orders";
    }

    @RequestMapping(value = "/admin/deliveryOrders/{deliveryId}", method = RequestMethod.GET)
    public String getDelivery( @PathVariable("deliveryId") Integer deliveryId,
                            Map<String, Object> map) {
        DeliveryOrder deliveryOrder = orderServices.getDelivery(deliveryId);
        map.put("delivery", deliveryOrder);
        return "/order/delivery";
    }

    @RequestMapping(value = "/admin/evaluationOrders/delete/{evaluationId}", method = RequestMethod.GET)
    public String deleteEvaluation( @PathVariable("evaluationId") Integer evaluationId) {
        EvaluationOrder evaluationOrder = orderServices.getEvaluation(evaluationId);
        orderServices.delete(evaluationOrder);
        return "redirect:/admin/orders";
    }

    @RequestMapping(value = "/admin/evaluationOrders/{evaluationId}", method = RequestMethod.GET)
    public String getEvaluation( @PathVariable("evaluationId") Integer evaluationId,
                            Map<String, Object> map) {
        EvaluationOrder evaluationOrder = orderServices.getEvaluation(evaluationId);
        map.put("evaluation", evaluationOrder);
        return "/order/evaluation";
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}

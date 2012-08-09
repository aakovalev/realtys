package ru.camoroh13.realtys.web;

import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.camoroh13.realtys.domain.Feedback;
import ru.camoroh13.realtys.service.FeedbackService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * User: Konstantin
 * Date: 21.08.11
 * Time: 13:30
 */
@Controller
public class MailController  extends HelperController {

    @Autowired
    FeedbackService feedbackService;

    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    public String getDeliveryOrder(@ModelAttribute("feedback") Feedback feedback,
                                   Map<String, Object> map) throws MessagingException {
        map = makePage(map, "feedback");
        map = makeSearchForm(map);
        feedbackService.send(feedback);
        return "public/page";
    }

    @RequestMapping(value = "/complaints", method = RequestMethod.POST)
    public String getComplaint(@ModelAttribute("feedback") Feedback feedback,
                               @RequestParam("jcaptcha") String userCaptchaResponse,
                               HttpServletRequest request,
                               Map<String, Object> map) throws MessagingException {
        map = makePage(map, "complaints");
        map = makeSearchForm(map);
        boolean captchaPassed = SimpleImageCaptchaServlet.validateResponse(request, userCaptchaResponse);
        if (captchaPassed) {
            feedbackService.save(feedback);
            feedbackService.send(feedback);
            map.remove("feedback");
        }
        else {
            // to give a user chance not to loose the details he (she) entered
            // if he (she) failed to enter valid captcha code
            map.put("feedback", feedback);
        }
        map.put("captchaPassed", captchaPassed);
        map.put("complaintList", feedbackService.findAll());
        return "public/page";
    }

    @RequestMapping(value = "/admin/complaints", method = RequestMethod.GET)
    public String getComplaintList(Map<String, Object> map) {
        map.put("complaintList", feedbackService.findAll());
        return "complaints/complaintList";
    }

    @RequestMapping(value = "/admin/complaints/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        feedbackService.delete(id);
        return "redirect:/admin/complaints";
    }
}

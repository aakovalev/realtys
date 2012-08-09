package ru.camoroh13.realtys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.camoroh13.realtys.domain.User;
import ru.camoroh13.realtys.helper.Validator;
import ru.camoroh13.realtys.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * Контроллер для работы с пользователями
 */
@Controller
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(Map<String, Object> map) {
        map.put("user", "");
        map.put("text", "Привет!");
        return "index";
    }

    /**
     * Выбор все пользователей и отрисока формы добаления пользователя
     *
     * @param map
     * @return String freemarker template
     */
    @RequestMapping("/admin/users")
    public String listUsers(Map<String, Object> map) {
        map.put("userList", userService.listUser());
        return "users/user";
    }

    /**
     * Поиск пользователей
     *
     * @param map
     * @param request
     * @return String freemarker template
     */
    @RequestMapping("/admin/users/search/")
    public String searchUsers(Map<String, Object> map, HttpServletRequest request) {
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            System.out.println(params.nextElement());
        }
        String lastname = request.getParameter("lastname");
        String useAdditional = request.getParameter("useAdditional");
        if ("1".equals(useAdditional)) {
            /* Если заданы расширенные параметры поиска */
            String firstname = request.getParameter("firstname");
            String username = request.getParameter("username");
            map.put("userList", userService.search(lastname, firstname, username));
        } else {
            map.put("userList", userService.search(lastname));
        }

        return "users/user";
    }

    /**
     * Главная страница. Происходит редирект на страницу со списком пользователей
     * 
     * @return
     */
    @RequestMapping("/admin")
    public String home() {
        return "redirect:/admin/users";
    }

    /**
     * Добавление пользователя
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "/admin/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user,
                          BindingResult result, HttpServletRequest request) {

        if(Validator.email(user.getEmail()) && Validator.phone(user.getTelephone())
                && Validator.fio(user.getFirstname()) && Validator.fio(user.getLastname())
                && Validator.name(user.getUsername()) && Validator.pass(request.getParameter("password"))) {
            userService.addUser(user);
        }

        return "redirect:/admin/users";
    }

    /**
     * Редактирование пользователя
     *
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/admin/users/edit/{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable("id") int id, Map<String, Object> map) {
        User user = userService.getUser(id);
        if (user == null) {
            System.out.println("User " + id + " is NULL!!!");
        }
        map.put("user", user);
        return "users/edit";
    }

    /**
     * Сохранение пользователя
     *
     * @param id
     * @param user
     * @return
     */
    @RequestMapping(value = "/admin/users/edit/{id}", method = RequestMethod.POST)
    public String saveUser(@PathVariable("id") Integer id, @ModelAttribute("user") User user,
                          HttpServletRequest request) {
        user.setId(id);
        User oldUser = userService.getUser(id);
        // Сохраняем старый пароль
        user.setOldPassword(oldUser.getPassword());
        System.out.println("User name = " + user.getUsername() + " firstname = " + user.getFirstname());
        if(Validator.email(user.getEmail()) && Validator.phone(user.getTelephone())
                && Validator.fio(user.getFirstname()) && Validator.fio(user.getLastname())
                && Validator.name(user.getUsername())) {
            userService.updateUser(user);
        }
        return "redirect:/admin/users";
    }

    /**
     * Удаление пользователя
     *
     * @param userId
     * @return
     */
    @RequestMapping("/admin/users/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) {

        userService.removeUser(userId);

        return "redirect:/admin/users";
    }

}

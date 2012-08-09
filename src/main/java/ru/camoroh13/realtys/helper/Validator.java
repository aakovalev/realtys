package ru.camoroh13.realtys.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для валидации
 *
 */
public class Validator {

    private static Pattern emailPattern = Pattern.compile("[a-z][a-z[0-9]\\u005F\\u002E\\u002D]*[a-z||0-9]+@[a-z][a-z[0-9]\\u005F\\u002E\\u002D]*[a-z||0-9]+\\u002E([a-z]){2,4}");

    private static Pattern phonePattern = Pattern.compile("[0-9|+\\(\\)\\s]{5,}");

    private static Pattern fioPattern = Pattern.compile("[A-Z|a-z|А-Я|а-я|0-9| |_|\\-]{2,}");

    private static Pattern namePattern = Pattern.compile("[A-Z|a-z|0-9|_]{5,}");

    /**
     * Валидация имени и фамилии с использованием кириллицы
     *
     * @param fio
     * @return
     */
    public static boolean fio(String fio) {
        Matcher matcher = fioPattern.matcher(fio);
        return matcher.matches();
    }

    /**
     * Валидация логина без кириллицы
     *
     * @param name
     * @return
     */
    public static boolean name(String name) {
        Matcher matcher = namePattern.matcher(name);
        return matcher.matches();
    }

    /**
     * Валидация email
     *
     * @param email
     * @return
     */
    public static boolean email(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Валидация телефона 
     *
     * @return
     */
    public static boolean phone(String phone) {
        Matcher matcher = phonePattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean pass(String password) {
        if (password.length() > 4) {
            return true;
        } else {
            return false;
        }
    }

}

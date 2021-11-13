package by.KuvonchbekN.service;

import by.KuvonchbekN.model.Card;
import by.KuvonchbekN.model.Category;
import by.KuvonchbekN.model.Course;
import by.KuvonchbekN.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface BaseService<T, R, RL> {
    List<User> userList = new ArrayList<>();
    List<Card> cardList = new ArrayList<>();
    List<Course> courseList = new ArrayList<>();
    List<Category> categoryList = new ArrayList<>();

    T add(T t);
    R get(UUID id);
    T check(String str);
    RL list(T t);
    Boolean delete(UUID id);
}

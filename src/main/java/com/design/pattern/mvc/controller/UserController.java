package com.design.pattern.mvc.controller;

import com.design.pattern.mvc.view.UserView;
import com.design.pattern.mvc.domain.User;

import java.lang.reflect.Field;

/**
 * 用户控制器
 */
public class UserController {

    private User user;

    private UserView userView;

    public UserController(User user, UserView userView) {
        this.user = user;
        this.userView = userView;
    }

    public void setUserName(String userName){
        Field userNameField;
        try {
            userNameField = user.getClass().getDeclaredField("name");
            userNameField.setAccessible(true);
            userNameField.set(user,userName);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void setUserAge(Integer userAge) {
        user.setAge(userAge);
    }

    public String getUserName() {
        return user.getName();
    }

    public Integer getUserAge() {
        return user.getAge();
    }

    public void updateUserView() {
        userView.showUserView(user);
    }
}


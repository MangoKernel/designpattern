package com.design.pattern.mvc.view;

import com.alibaba.fastjson.JSON;
import com.design.pattern.mvc.domain.User;

/**
 * 用户视图
 */
public class UserView {

    public void showUserView(User user) {
        System.out.println("This is a user's information: \nUser name:" + user.getName() + "\nUser age:" + user.getAge());
        System.out.println("This is a user's json information:" + JSON.toJSON(user));
    }
}

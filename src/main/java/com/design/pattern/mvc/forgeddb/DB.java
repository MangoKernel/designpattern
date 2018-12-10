package com.design.pattern.mvc.forgeddb;

import com.design.pattern.mvc.domain.User;

import java.lang.reflect.Field;

/**
 * 伪造的数据库
 */
public class DB {

    public static User getUserInfoFromDB(){
        User user = new User();
        user.setAge(20);
        Field userNameField;
        try {
            userNameField = user.getClass().getDeclaredField("name");
            userNameField.setAccessible(true);
            userNameField.set(user,"MangoKernel");
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return user;
    }
}

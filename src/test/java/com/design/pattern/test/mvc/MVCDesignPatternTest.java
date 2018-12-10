package com.design.pattern.test.mvc;

import com.design.pattern.mvc.controller.UserController;
import com.design.pattern.mvc.domain.User;
import com.design.pattern.mvc.forgeddb.DB;
import com.design.pattern.mvc.view.UserView;
import org.junit.Test;

/**
 * MVC 设计模式测试类
 */
public class MVCDesignPatternTest {

    @Test
    public void mvcTest() {
        //从db中获取一条用户数据
        User user = DB.getUserInfoFromDB();
        //创建视图，将学生信息显示到console
        UserView userView = new UserView();
        UserController userController = new UserController(user,userView);
        userController.updateUserView();

        //更新用户年龄
        user.setAge(30);
        userController.updateUserView();
    }
}

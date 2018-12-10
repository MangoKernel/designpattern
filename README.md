# designpattern
一个mvc的设计模式demo

#环境依赖
jdk 1.8

#版本
1.0.0 关于mvc设计模式的演示

    MVC 设计模式

    MVC模式代表 Model-View-Controller(模型-视图-控制器)模式，这种模式用于应用程序的分层开发。
    Model：模型代表一个存取数据的Java POJO，它也可以带有逻辑，在数据变化时更新控制器。
    View：视图代表模型包含的数据的可视化。
    Controller: 控制器作用于模型上。它控制数据流向模型对象，并在数据发生变化时更新视图。它使视图与模型分离开。
    
#流程示意图
resources/mvc/mvc.png
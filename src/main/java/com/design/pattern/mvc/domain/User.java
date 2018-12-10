package com.design.pattern.mvc.domain;

/**
 * 用户类
 */
public class User {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString() {
        return "name:" + name + "\nage:" + age;
    }
}

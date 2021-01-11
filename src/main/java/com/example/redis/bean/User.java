package com.example.redis.bean;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Auther: zhangyingqi
 * @Date: 2018/8/27 17:17
 * @Description:
 */

public class User implements Serializable {
    private static final long serialVersionUID = -3210884885630038713L;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    private int id;

    private String name;

    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

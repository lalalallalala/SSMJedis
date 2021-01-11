package com.example.redis.controller;


import com.example.redis.bean.User;
import com.example.redis.util.JedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;


/**
 * @ClassName: RedisTestController
 * @Auther: zhangyingqi
 * @Date: 2018/8/28 17:24
 * @Description:
 */
@Controller
@RequestMapping("/redis")
public class RedisTestController{


    /**
     * @auther: zhangyingqi
     * @date: 17:26 2018/8/28
     * @param: []
     * @return: org.springframework.ui.ModelMap
     * @Description: 测试redis存储&读取
     */
    @RequestMapping(value="/test")
    @ResponseBody
    public String test(){
        try {
            User user = new User();
            user.setId(1);
            user.setAge(23);
            user.setName("hanhongchao");

            User user2 = new User();
            user2.setId(1);
            user2.setAge(23);
            user2.setName("hanhongchao");

            ArrayList<User> users = new ArrayList<>();
            users.add(user);
            users.add(user2);
            if(!JedisUtil.exists("users")){
                String isOk = JedisUtil.setObject("user", user);
                String isOk2 = JedisUtil.setObject("users", users);
                if("OK".equals(isOk)){
                    System.out.println("成功:" + isOk);
                }
                if("OK".equals(isOk2)){
                    System.out.println("成功:" + isOk2);
                }
            }else {
                User reuser = (User)JedisUtil.getObject("user");
                System.out.println(reuser);
                System.out.println("------------------------------");
                ArrayList<User> a = (ArrayList<User>) JedisUtil.getObject("users");
                a.forEach( b -> System.out.println(b.toString()));
            }

            return "操作成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "操作失败";
        }
    }

}

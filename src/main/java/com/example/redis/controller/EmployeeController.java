package com.example.redis.controller;

import com.example.redis.bean.Employee;
import com.example.redis.service.EmployeeService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 事务练习
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @ResponseBody
    @GetMapping("/list")
    public List<Employee> list() {
        long l = System.currentTimeMillis();
        List<Employee> EmployeeList = employeeService.list();
        System.out.println(System.currentTimeMillis() - l);

        return EmployeeList;

    }

    @ResponseBody
    @GetMapping("/save")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public void save() {
        System.out.println(employeeService.saveEmployee());
        System.out.println(employeeService.saveEmployee2());
    }


//    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public void save1(){
        EmployeeController a = (EmployeeController) AopContext.currentProxy();
        a.test1();

        a.test2();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
    public void test1(){
        Employee employee = new Employee();
        employee.setAge("1");
        employee.setName("1");
        int save = employeeService.save(employee);
        System.out.println(save);
    }


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public void test2(){
        Employee employee = new Employee();
        employee.setAge("2");
        employee.setName("2");
        int save = employeeService.save(employee);
        int i = 5/0;
        System.out.println(save);
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


//    @Scheduled(fixedRate = 5000)  定时任务
    public void scheduledTasks(){
        System.out.println("The time is now {}" + dateFormat.format(new Date()));
    }
}

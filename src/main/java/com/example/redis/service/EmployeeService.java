package com.example.redis.service;

import com.example.redis.bean.Employee;
import com.example.redis.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    public int save(){
        try {
//            EmployeeService employeeService=(EmployeeService)AopContext.currentProxy();
//            return employeeService.saveEmployee();  //此处this调用不会开启事务，数据会被保存
            return saveEmployee();
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
    public int saveEmployee(){
        Employee employee = new Employee();
        employee.setName("1");
        employee.setAge("1");
        int save = employeeDao.save(employee);
        System.out.println(employee.getId());
        return save;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public int saveEmployee2(){
        Employee employee = new Employee();
        employee.setName("2");
        employee.setAge("2");
        int save = employeeDao.save(employee);
        System.out.println(employee.getId());
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return save;
    }

     public int save(Employee employee){
        return employeeDao.save(employee);
     }

    @Cacheable(value = "client")//设置了缓存
    public List<Employee> list(){
        return employeeDao.list();
    }

    public Employee list2(String id){
        return employeeDao.list2(id);
    }

    /**
     * 缓存管理
     * @return
     */
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("client"),new ConcurrentMapCache("clientId"),new ConcurrentMapCache("jwtinfo")));
        cacheManager.afterPropertiesSet();
        return cacheManager;
    }

}

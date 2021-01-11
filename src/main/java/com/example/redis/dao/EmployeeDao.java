package com.example.redis.dao;

import com.example.redis.bean.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmployeeDao {
    //保存单个
    int save(Employee employee);
    //查询全部
    List<Employee> list();
    //单个查询返回pojo类
    Employee list2(@Param("id") String id);


    //单个查询 返回Map
    Map<String, Object> queryCommodityByCode(String code);
    //批量查询
    List<Map<String, Object>> queryCommodityByCode2(@Param("ids") ArrayList ids);
}

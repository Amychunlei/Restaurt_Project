package com.pro.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pro.reggie.entity.Employee;
import com.pro.reggie.mapper.EmployeeMapper;
import com.pro.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
//两个泛型是实体类对应的mapper，一个是实体类
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}

package org.example.tliasweb.service;

/* 
    @author nanchao 
    @date 2025/4/8
*/

import org.example.tliasweb.pojo.Emp;
import org.example.tliasweb.pojo.LoginInfo;
import org.example.tliasweb.pojo.PageResult;

import java.time.LocalDate;


public interface EmpService {
    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    void save(Emp emp);

    LoginInfo login(Emp emp);
}

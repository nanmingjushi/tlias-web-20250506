package org.example.tliasweb.service;

/* 
    @author nanchao 
    @date 2025/4/6
*/

import org.example.tliasweb.pojo.Dept;

import java.util.List;

public interface DeptService {
    //查询部门
    List<Dept> findAll();

    void deleteById(Integer deptId);

    void add(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}

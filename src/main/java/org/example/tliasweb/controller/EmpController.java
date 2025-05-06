package org.example.tliasweb.controller;

/* 
    @author nanchao 
    @date 2025/4/8
*/

import lombok.extern.slf4j.Slf4j;

import org.example.tliasweb.pojo.Emp;
import org.example.tliasweb.pojo.PageResult;
import org.example.tliasweb.pojo.Result;
import org.example.tliasweb.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

//@Slf4j
@RestController
public class EmpController {

    private static Logger log = LoggerFactory.getLogger(EmpController.class);

    @Autowired
    private EmpService empService;


    //查询员工
    @GetMapping("/emps")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name,
                       Integer gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        log.info("查询员工信息，page={},pageSize={},name={},gender={},begin={},end={}", page, pageSize, name, gender, begin, end);
        PageResult<Emp> pageResult = empService.page(page, pageSize,name, gender, begin, end);
        return Result.success(pageResult);
    }

    //新增员工
    @PostMapping("/emps")
    public Result save(@RequestBody Emp emp){
        log.info("新增员工，emp={}",emp);
        empService.save(emp);
        return Result.success();
    }




}

package org.example.tliasweb.controller;

/* 
    @author nanchao 
    @date 2025/4/6
*/

import lombok.extern.slf4j.Slf4j;
import org.example.tliasweb.pojo.Dept;
import org.example.tliasweb.pojo.Result;
import org.example.tliasweb.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Slf4j
@RestController
public class DeptController {

    private static Logger log= LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result list() {
        log.info("查询部门列表");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @DeleteMapping("/depts")
    public Result delete(@RequestParam(value = "id", required = false) Integer deptId) {
        log.info("根据id删除部门，id:{}",deptId);
        deptService.deleteById(deptId);
        return Result.success();
    }

    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        log.info("新增部门，dept：{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    //查询回显
    @GetMapping("/depts/{id}")
    public Result getById(@PathVariable("id") Integer id){
        log.info("根据id查询部门，id:{}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }
    //修改
    @PutMapping("/depts")
    public Result uopdate(@RequestBody Dept dept){
        log.info("修改部门，dept:{}",dept);
        deptService.update(dept);
        return Result.success();
    }

}

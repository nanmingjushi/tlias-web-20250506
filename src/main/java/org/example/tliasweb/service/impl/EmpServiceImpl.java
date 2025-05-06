package org.example.tliasweb.service.impl;

/* 
    @author nanchao 
    @date 2025/4/8
*/

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.tliasweb.controller.EmpController;
import org.example.tliasweb.mapper.EmpExprMapper;
import org.example.tliasweb.mapper.EmpMapper;
import org.example.tliasweb.pojo.Emp;
import org.example.tliasweb.pojo.EmpExpr;
import org.example.tliasweb.pojo.LoginInfo;
import org.example.tliasweb.pojo.PageResult;
import org.example.tliasweb.service.EmpService;
import org.example.tliasweb.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {

    private static Logger log = LoggerFactory.getLogger(EmpServiceImpl.class);


    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {

        //基于pageHelper进行分页查询
        PageHelper.startPage(page, pageSize);

        //执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);

        //解析查询结果并封装
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());

    }

    //事务管理
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Emp emp) {
        //补全基础属性
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        //保存员工基础信息
        empMapper.insert(emp);

        //保存员工的工作经历
        Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }

    }


    //登录
    @Override
    public LoginInfo login(Emp emp) {
        Emp empLogin=empMapper.getUsernameAndPassword(emp);
        if(empLogin!=null){
            log.info("员工登录成功,员工信息：{}",empLogin);
            //生成JWT令牌
            Map<String ,Object> claims=new HashMap<>();
            claims.put("id",empLogin.getId());
            claims.put("username",empLogin.getUsername());
            String jwt= JwtUtils.generateJwt(claims);

            return new LoginInfo(empLogin.getId(),empLogin.getUsername(),empLogin.getName(),jwt);
        }
        return null;
    }
}

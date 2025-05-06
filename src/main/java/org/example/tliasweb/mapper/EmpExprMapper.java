package org.example.tliasweb.mapper;

/* 
    @author nanchao 
    @date 2025/4/9
*/

import org.apache.ibatis.annotations.Mapper;
import org.example.tliasweb.pojo.EmpExpr;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    //批量插入员工工作经历信息
    void insertBatch(List<EmpExpr> exprList);
}

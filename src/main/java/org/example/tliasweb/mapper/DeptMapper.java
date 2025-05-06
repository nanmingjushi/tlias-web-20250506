package org.example.tliasweb.mapper;

/* 
    @author nanchao 
    @date 2025/4/6
*/

import org.apache.ibatis.annotations.*;
import org.example.tliasweb.pojo.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select id,name,create_time,update_time from dept order by update_time desc")
    List<Dept> findAll();

    @Delete("delete from dept where id=#{id};")
    void deleteById(Integer deptId);

    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime}) ")
    void add(Dept dept);

    @Select("select id,name,create_time,update_time from dept where id=#{id}")
    Dept getById(Integer id);

    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}

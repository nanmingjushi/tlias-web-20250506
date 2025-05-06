package org.example.tliasweb.mapper;

/* 
    @author nanchao 
    @date 2025/4/8
*/

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.example.tliasweb.pojo.Emp;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    //查询所有的员工及其对应的部门名称
    /*@Select("select emp.*,dept.name as deptName from emp " +
            "left join dept on emp.dept_id=dept.id " +
            "where emp.name like concat('%',#{name},'%') and emp.gender=#{gender} and (emp.entry_date between #{begin} and #{end}) "+
            "order by emp.update_time desc ")*/
    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);


    //主键返回，获取员工的Id
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time)" +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    public void insert(Emp emp);

    //根据用户名和密码查询员工信息
    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp getUsernameAndPassword(Emp emp);
}

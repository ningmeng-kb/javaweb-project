package com.hws.mapper;

import com.hws.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询全部部门数据
     * @return
     */
    @Select("select * from dept")
    List<Dept> list();

    /**
     * 根据id删除部门
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * 新增部门信息
     * @param dept
     */
    @Insert("insert into dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    /**
     * 修改部门信息
     *
     * @param dept
     */
    @Update("update dept set name = #{name}, update_time= #{updateTime} where id=#{id}")
    void update(Dept dept);

    @Select("select * from dept where id = #{id}")
    Dept getByID(Integer id);
}

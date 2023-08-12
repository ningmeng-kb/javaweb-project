package com.hws.service;

import com.hws.pojo.Emp;
import com.hws.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 批量删除
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 新增员工
     * @param emp
     */
    void save(Emp emp);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 跟新员工信息
     * @param emp
     */
    void update(Emp emp);

    /**
     * 员工登陆
     * @param emp
     * @return
     */
    Emp login(Emp emp);
}

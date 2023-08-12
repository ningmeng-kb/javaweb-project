package com.hws.service.impl;

import com.hws.mapper.DeptMapper;
import com.hws.mapper.EmpMapper;
import com.hws.pojo.Dept;
import com.hws.pojo.DeptLog;
import com.hws.service.DeptLogService;
import com.hws.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional(rollbackFor = Exception.class) //spring事务管理
    @Override
    public void delete(Integer id) {
        try {
            deptMapper.deleteById(id);//根据id删除部门数据
            int i = 1/0;
            empMapper.deleteByDeptId(id);//根据部门id删除该部门下的员工

        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此次解散的是"+id+"号部门");
            deptLogService.insert(deptLog);
        }
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);

    }

    @Override
    public Dept getByID(Integer id) {
        Dept dept = deptMapper.getByID(id);
        return dept;
    }
}

package com.hws.controller;

import com.hws.pojo.Dept;
import com.hws.pojo.Result;
import com.hws.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

//    private static Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    /**
     * 查询部门数据
     * @return
     */
//    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");
        //调用service查询数据
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    /**
     * 删除部门
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门:{}",id);
        //调用service删除部门
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 新增部门
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门：{}",dept);
        deptService.add(dept);
        return Result.success(dept);

    }

    /**
     * 修改部门
     * @return
     */
    @GetMapping("/{id}")
    public Result getByID(@PathVariable Integer id) {
        log.info("获取部门ID:{}",id);
        Dept dept = deptService.getByID(id);
        return Result.success(dept);
    }
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门：{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}

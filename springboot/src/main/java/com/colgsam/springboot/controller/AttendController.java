package com.colgsam.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.colgsam.springboot.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.colgsam.springboot.service.IAttendService;
import com.colgsam.springboot.entity.Attend;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 肖海冰
 * @since 2023-02-24
 */
@RestController
@RequestMapping("/attend")
public class AttendController {

    @Resource
    private IAttendService attendService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Attend attend) {
        return Result.success(attendService.saveOrUpdate(attend));
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(attendService.removeById(id));
    }

    // 批量删除
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(attendService.removeBatchByIds(ids));
    }

    // 查询所有数据
    @GetMapping
    public Result findAll() {
        return Result.success(attendService.list());
    }

    // 根据id查询
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(attendService.getById(id));
    }

    // 分页查询
    @GetMapping("/page")
    public Page<Attend> findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize) {
        QueryWrapper<Attend> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id"); // id倒序排列
        Page<Attend> page = attendService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return page;
    }

}


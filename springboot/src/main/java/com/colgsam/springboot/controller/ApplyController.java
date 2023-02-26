package com.colgsam.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.colgsam.springboot.common.Result;
import com.colgsam.springboot.entity.Apply;
import com.colgsam.springboot.service.IApplyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 肖海冰
 * @since 2023-02-18
 */
@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Resource
    private IApplyService applyService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Apply apply) {
        return Result.success(applyService.saveOrUpdate(apply));
    }

    // 删除
    @DeleteMapping("/{aid}")
    public Result delete(@PathVariable Integer aid) {
        return Result.success(applyService.removeById(aid));
    }

    // 查询所有数据
    @GetMapping
    public Result findAll() {
        return Result.success(applyService.list());
    }

    // 根据id查询
    @GetMapping("/{aid}")
    public Result findOne(@PathVariable Integer aid) {
        return Result.success(applyService.getById(aid));
    }

    // 分页查询
    @GetMapping("/page")
    public Page<Apply> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam String applyName,
                                @RequestParam String applyType) {
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("aid"); // id倒序排列
        if (!"".equals(applyName)) {
            queryWrapper.like("apply_name", applyName);
        }
        if (!"".equals(applyType)) {
            queryWrapper.like("apply_type", applyType);
        }
        Page<Apply> page = applyService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return page;
    }

}


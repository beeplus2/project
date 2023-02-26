package com.colgsam.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.colgsam.springboot.common.Result;
import com.colgsam.springboot.entity.Type;
import com.colgsam.springboot.service.ITypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 肖海冰
 * @since 2023-02-17
 */
@RestController
@RequestMapping("/type")
public class TypeController {

    @Resource
    private ITypeService typeService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Type type) {
        return Result.success(typeService.saveOrUpdate(type));
    }

    // 删除
    @DeleteMapping("/{tid}")
    public Result delete(@PathVariable Integer tid) {
        return Result.success(typeService.removeById(tid));
    }

    // 查询所有数据
    @GetMapping
    public Result findAll() {
        return Result.success(typeService.list());
    }

    // 根据id查询
    @GetMapping("/{tid}")
    public Result findOne(@PathVariable Integer tid) {
        return Result.success(typeService.getById(tid));
    }

    // 分页查询
    @GetMapping("/page")
    public Page<Type> findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String typeName) {
        QueryWrapper<Type> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("tid"); // id倒序排列
        if (!"".equals(typeName)) {
            queryWrapper.like("type_name", typeName);
        }
        Page<Type> page = typeService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return page;
    }

}


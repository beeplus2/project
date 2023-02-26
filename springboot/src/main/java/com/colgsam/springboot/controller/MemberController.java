package com.colgsam.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.colgsam.springboot.common.Result;
import com.colgsam.springboot.entity.Member;
import com.colgsam.springboot.service.IMemberService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 肖海冰
 * @since 2023-02-24
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private IMemberService memberService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Member member) {
        return Result.success(memberService.saveOrUpdate(member));
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(memberService.removeById(id));
    }

    // 查询所有数据
    @GetMapping
    public Result findAll() {
        return Result.success(memberService.list());
    }

    // 根据id查询
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(memberService.getById(id));
    }

    // 分页查询
    @GetMapping("/page")
    public Page<Member> findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id"); // id倒序排列
        Page<Member> page = memberService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return page;
    }

}


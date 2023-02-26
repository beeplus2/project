package com.colgsam.springboot.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.colgsam.springboot.common.Result;
import com.colgsam.springboot.entity.Activity;
import com.colgsam.springboot.mapper.ActivityMapper;
import com.colgsam.springboot.service.IActivityService;
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
@RequestMapping("/activity")
public class ActivityController {

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private IActivityService activityService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Activity activity) {
        return Result.success(activityService.saveOrUpdate(activity));
    }

    // 删除
    @DeleteMapping("/{acid}")
    public Result delete(@PathVariable Integer acid) {
        return Result.success(activityService.removeById(acid));
    }

    // 查询所有数据
    @GetMapping
    public Result findAll() {
        return Result.success(activityService.list());
    }

    // 根据id查询
    @GetMapping("/{acid}")
    public Result findOne(@PathVariable Integer acid) {
        return Result.success(activityService.getById(acid));
    }

    // 根据所属社团查询可参加活动
    @GetMapping("/club/{cid}")
    public Result findByUsername(@PathVariable Integer cid) {
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("activity_club", cid);
        queryWrapper.eq("activity_state", 1);
        queryWrapper.gt("activity_time", DateUtil.parse(DateUtil.today()).toJdkDate());
        return Result.success(activityMapper.selectList(queryWrapper));
    }

    // 分页查询
    @GetMapping("/page")
    public Page<Activity> findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam String activityName,
                                   @RequestParam String activityClub) {
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("acid"); // id倒序排列
        if (!"".equals(activityName)) {
            queryWrapper.like("activity_name", activityName);
        }
        if (!"".equals(activityClub)) {
            queryWrapper.like("activity_club", activityClub);
        }
        Page<Activity> page = activityService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return page;
    }

}


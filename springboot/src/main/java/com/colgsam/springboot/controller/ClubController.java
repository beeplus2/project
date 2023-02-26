package com.colgsam.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.colgsam.springboot.common.Result;
import com.colgsam.springboot.entity.Club;
import com.colgsam.springboot.mapper.ClubMapper;
import com.colgsam.springboot.service.IClubService;
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
@RequestMapping("/club")
public class ClubController {

    @Resource
    private ClubMapper clubMapper;

    @Resource
    private IClubService clubService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Club club) {
        return Result.success(clubService.saveOrUpdate(club));
    }

    // 删除
    @DeleteMapping("/{cid}")
    public Result delete(@PathVariable Integer cid) {
        return Result.success(clubService.removeById(cid));
    }

    // 查询所有数据
    @GetMapping
    public Result findAll() {
        return Result.success(clubService.list());
    }

    // 根据id查询
    @GetMapping("/{cid}")
    public Result findOne(@PathVariable Integer cid) {
        return Result.success(clubService.getById(cid));
    }

    // 根据社团名称与类型查询尚存社团
    @GetMapping("/clubExist")
    public Result findExist(@RequestParam String clubName,
                            @RequestParam String clubType) {
        QueryWrapper<Club> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("club_state", 1);
        if (!"".equals(clubName)) {
            queryWrapper.like("club_name", clubName);
        }
        if (!"".equals(clubType)) {
            queryWrapper.like("club_type", clubType);
        }
        return Result.success(clubMapper.selectList(queryWrapper));
    }

    // 分页查询
    @GetMapping("/page")
    public Page<Club> findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String clubName,
                               @RequestParam String clubType) {
        QueryWrapper<Club> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("cid"); // id倒序排列
        if (!"".equals(clubName)) {
            queryWrapper.like("club_name", clubName);
        }
        if (!"".equals(clubType)) {
            queryWrapper.like("club_type", clubType);
        }
        Page<Club> page = clubService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return page;
    }

}


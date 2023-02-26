package com.colgsam.springboot.service.impl;

import com.colgsam.springboot.entity.Activity;
import com.colgsam.springboot.mapper.ActivityMapper;
import com.colgsam.springboot.service.IActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 肖海冰
 * @since 2023-02-18
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

}

package com.colgsam.springboot.service.impl;

import com.colgsam.springboot.entity.Club;
import com.colgsam.springboot.mapper.ClubMapper;
import com.colgsam.springboot.service.IClubService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 肖海冰
 * @since 2023-02-17
 */
@Service
public class ClubServiceImpl extends ServiceImpl<ClubMapper, Club> implements IClubService {

}

package com.colgsam.springboot.service.impl;

import com.colgsam.springboot.entity.Member;
import com.colgsam.springboot.mapper.MemberMapper;
import com.colgsam.springboot.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 肖海冰
 * @since 2023-02-24
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

}

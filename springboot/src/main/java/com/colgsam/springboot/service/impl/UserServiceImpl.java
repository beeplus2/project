package com.colgsam.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.colgsam.springboot.common.Constants;
import com.colgsam.springboot.controller.dao.UserDTO;
import com.colgsam.springboot.entity.User;
import com.colgsam.springboot.exception.ServiceException;
import com.colgsam.springboot.mapper.UserMapper;
import com.colgsam.springboot.service.IUserService;
import com.colgsam.springboot.utils.TokenUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 肖海冰
 * @since 2023-02-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            // 设置token
            String token = TokenUtils.genToken(one.getUid().toString(), one.getPassword());
            userDTO.setToken(token);
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDTO, one, true);
            one.setType(3); // 注册为学生
            one.setAvatar("http://localhost:8888/file/70cb27739c724102a53bfb3e8c839c9a.jpg"); // 默认头像
            save(one);  // 把copy完之后的用户对象存储到数据库
        } else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return one;
    }

    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        User one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            log.error(e.toString());
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }
}

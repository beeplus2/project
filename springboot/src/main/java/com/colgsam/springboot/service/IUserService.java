package com.colgsam.springboot.service;

import com.colgsam.springboot.controller.dao.UserDTO;
import com.colgsam.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 肖海冰
 * @since 2023-02-12
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);
}

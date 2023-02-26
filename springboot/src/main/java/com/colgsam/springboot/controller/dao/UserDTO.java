package com.colgsam.springboot.controller.dao;

import lombok.Data;

/**
 * 接收前端登录请求的参数
 */
@Data
public class UserDTO {
    private String username;
    private String password;
    private String name;
    private String avatar;
    private Integer type;
    private String token;
}

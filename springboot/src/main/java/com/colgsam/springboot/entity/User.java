package com.colgsam.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 肖海冰
 * @since 2023-02-12
 */
@Getter
@Setter
@TableName("t_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid")
    private Integer uid;

    private String username;

    private String password;

    private String name;

    private Integer sex;

    private String tel;

    private String email;

    private Integer type;

    private String avatar;
}

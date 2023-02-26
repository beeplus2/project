package com.colgsam.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 肖海冰
 * @since 2023-02-24
 */
@Getter
@Setter
  @TableName("t_attend")
@ApiModel(value = "Attend对象", description = "")
public class Attend implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer acid;

      private Integer uid;


}

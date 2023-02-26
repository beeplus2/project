package com.colgsam.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 肖海冰
 * @since 2023-02-18
 */
@Getter
@Setter
  @TableName("t_apply")
@ApiModel(value = "Apply对象", description = "")
public class Apply implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "aid")
    private Integer aid;

    private String applyName;

    private Integer applyType;

    private Integer applicant;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime applyTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime applyFound;

    private String applyReason;

    private Integer applyState;


}

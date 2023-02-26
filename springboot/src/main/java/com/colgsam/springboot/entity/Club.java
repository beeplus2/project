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
 * @since 2023-02-17
 */
@Getter
@Setter
  @TableName("t_club")
@ApiModel(value = "Club对象", description = "")
public class Club implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cid")
    private Integer cid;

    private String clubName;

    private Integer clubType;

    private Integer clubHead;

    private String clubImg;

    private String clubIntroduction;

    private Integer clubNumber;

    private Double clubGrade;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime clubCreate;

    private Integer clubState;

}

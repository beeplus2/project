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
  @TableName("t_activity")
@ApiModel(value = "Activity对象", description = "")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "acid")
    private Integer acid;

    private String activityName;

    private Integer activityClub;

    private Integer activityHost;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime activityStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime activityTime;

    private String activityAddress;

    private String activityTel;

    private String activityIntroduction;

    private Integer activityState;

}

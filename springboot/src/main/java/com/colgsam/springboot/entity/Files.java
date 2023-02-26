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
 * @since 2023-02-16
 */
@Getter
@Setter
@TableName("t_file")
@ApiModel(value = "File对象", description = "")
public class Files implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "fid")
    private Integer fid;

    private String fileName;

    private String fileType;

    private Long fileSize;

    private String fileUrl;

    private String fileMd5;

}

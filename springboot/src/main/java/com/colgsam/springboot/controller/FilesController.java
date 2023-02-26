package com.colgsam.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.colgsam.springboot.entity.Files;
import com.colgsam.springboot.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 文件上传相关接口
 */
@CrossOrigin
@RestController
@RequestMapping("/file")
public class FilesController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Resource
    private FileMapper fileMapper;

    /**
     * 文件上传接口
     * @param file 前端传送过来的文件
     * @return 文件url
     * @throws IOException 抛出IO异常
     */
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException{
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();

        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        File parentFile = new File(fileUploadPath);
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        // 定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUUID);

        String url;
        // 上传文件到磁盘
        file.transferTo(uploadFile);
        //获取文件的md5
        String md5 = SecureUtil.md5(uploadFile);
        // 从数据库查询是否存在相同的记录
        Files dbFiles = getFileByMd5(md5);
        if (dbFiles != null) {
            url = dbFiles.getFileUrl();
            // 由于文件已存在，所以删除刚才上传的重置文件
            uploadFile.delete();
        } else {
            // 数据库若不存在重复文件，则不删除刚上传文件
            url = "http://localhost:8888/file/" + fileUUID;
        }

        // 存储数据库
        Files saveFile = new Files();
        saveFile.setFileName(originalFilename);
        saveFile.setFileType(type);
        saveFile.setFileSize(size/1024);
        saveFile.setFileUrl(url);
        saveFile.setFileMd5(md5);
        fileMapper.insert(saveFile);
        return url;
    }

    /**
     * 文件下载接口   http://localhost:8888/file/{fileUUID}
     * @param fileUUID 文件url
     * @param response 输出流
     * @throws IOException 抛出IO异常
     */
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-steam");

        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }

    /**
     * 通过文件的md5查询文件
     * @param md5 唯一标识码
     * @return 数据库中md5对应文件信息
     */
    private Files getFileByMd5(String md5) {
        // 查询文件的md5
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_md5", md5);
        List<Files> filesList = fileMapper.selectList(queryWrapper);
        return filesList.size() == 0 ? null : filesList.get(0);
    }
}

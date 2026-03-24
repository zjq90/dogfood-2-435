package com.example.shop.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传工具类
 *
 * @author example
 * @version 1.0.0
 */
@Slf4j
@Component
public class FileUploadUtil {

    @Value("${shop.upload.path:upload/}")
    private String uploadPath;

    @Value("${shop.file.allowed-types:jpg,jpeg,png,gif,bmp}")
    private String allowedTypes;

    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB

    /**
     * 上传文件
     *
     * @param file    上传的文件
     * @param request HTTP请求
     * @return 上传后的文件名
     * @throws IOException IO异常
     */
    public String upload(MultipartFile file, HttpServletRequest request) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("上传文件不能为空");
        }

        // 检查文件大小
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("文件大小不能超过10MB");
        }

        // 检查文件类型
        String extension = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
        List<String> allowedList = Arrays.asList(allowedTypes.split(","));
        if (!allowedList.contains(extension)) {
            throw new IllegalArgumentException("不支持的文件类型: " + extension);
        }

        // 生成新的文件名
        String newFileName = generateFileName(extension);

        // 获取上传目录
        String realPath = request.getSession().getServletContext().getRealPath("/" + uploadPath);
        if (realPath == null) {
            realPath = uploadPath;
        }

        // 创建目录
        Path uploadDir = Paths.get(realPath);
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
            log.debug("创建上传目录: {}", uploadDir);
        }

        // 保存文件
        Path filePath = uploadDir.resolve(newFileName);
        file.transferTo(filePath.toFile());

        log.info("文件上传成功: {}, 原始文件名: {}, 大小: {} bytes",
                newFileName, file.getOriginalFilename(), file.getSize());

        return newFileName;
    }

    /**
     * 生成文件名
     *
     * @param extension 文件扩展名
     * @return 生成的文件名
     */
    private String generateFileName(String extension) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        return timestamp + "_" + uuid + "." + extension;
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名
     * @param request  HTTP请求
     * @return 删除成功返回true
     */
    public boolean delete(String fileName, HttpServletRequest request) {
        if (fileName == null || fileName.isEmpty()) {
            return false;
        }

        try {
            String realPath = request.getSession().getServletContext().getRealPath("/" + uploadPath);
            if (realPath == null) {
                realPath = uploadPath;
            }

            File file = new File(realPath, fileName);
            if (file.exists() && file.delete()) {
                log.info("文件删除成功: {}", fileName);
                return true;
            }
        } catch (Exception e) {
            log.error("文件删除失败: {}", e.getMessage(), e);
        }

        return false;
    }
}

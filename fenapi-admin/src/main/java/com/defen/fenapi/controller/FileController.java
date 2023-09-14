package com.defen.fenapi.controller;

import com.defen.fenapi.manager.CosManager;
import com.defen.fenapicommon.common.BaseResponse;
import com.defen.fenapicommon.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件接口
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    private static final String FILE_DELIMITER = ",";

    @Resource
    private CosManager cosManager;

    /**
     * 通用上传请求（单个）
     */
    @PostMapping("/upload")
    public BaseResponse<Map<String, Object>> uploadFile(@RequestBody MultipartFile file, HttpServletRequest request) throws IOException {
        Map<String, Object> result = new HashMap<>(2);
        // 上传并返回新文件名称
        String uuid = RandomStringUtils.randomAlphanumeric(8);
        String fileName = uuid + "-" + file.getOriginalFilename();
        String imageUrl = cosManager.uploadFile2OSS(file.getInputStream(), fileName);
        result.put("url", imageUrl);
        return ResultUtils.success(result);
    }

    /**
     * 通用上传请求（多个）
     */
    @PostMapping("/uploads")
    public BaseResponse<Map<String, Object>> uploadFiles(List<MultipartFile> files) throws Exception {
        try {
            // 上传文件路径
//            String filePath = RuoYiConfig.getUploadPath();
            List<String> urls = new ArrayList<String>();
            List<String> fileNames = new ArrayList<String>();
            List<String> newFileNames = new ArrayList<String>();
            List<String> originalFilenames = new ArrayList<String>();
            for (MultipartFile file : files) {
                // 上传并返回新文件名称
                String uuid = RandomStringUtils.randomAlphanumeric(8);
                String fileName = uuid + "-" + file.getOriginalFilename();
                String imageUrl = cosManager.uploadFile2OSS(file.getInputStream(), fileName);
                urls.add(imageUrl);
                fileNames.add(fileName);
//                newFileNames.add(FileUtils.getName(fileName));
                originalFilenames.add(file.getOriginalFilename());
            }
            Map<String, Object> result = new HashMap<>();
            result.put("urls", StringUtils.join(urls, FILE_DELIMITER));
            result.put("fileNames", StringUtils.join(fileNames, FILE_DELIMITER));
//            result.put("newFileNames" , StringUtils.join(newFileNames, FILE_DELIMITER));
            result.put("originalFilenames", StringUtils.join(originalFilenames, FILE_DELIMITER));
            return ResultUtils.success(result);
        } catch (Exception e) {
            throw new Exception("上传文件失败");
        }
    }

}

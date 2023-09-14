package com.defen.fenapi.manager;

import com.defen.fenapi.config.CosClientConfig;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * Cos 对象存储操作
 */
@Component
public class CosManager {

    @Resource
    private CosClientConfig cosClientConfig;

    @Value("${cos.endpoint}")
    private String endpoint;

    @Value("${cos.bucketName}")
    private String bucketName;

    /**
     * 上传到OSS服务器 如果同名文件会覆盖服务器上的
     *
     * @param inputStream 文件流
     * @param fileName    文件名称 包括后缀名
     * @return 出错返回"" ,唯一MD5数字签名
     */
    public String uploadFile2OSS(InputStream inputStream, String fileName) {

        String ret = "";
        try {
            // 创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            // - 使用输入流存储，需要设置请求长度
            objectMetadata.setContentLength(inputStream.available());
            // - 设置缓存
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            // - 设置Content-Type
            objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            // 指定文件上传到 COS 上的路径，即对象键。
            String key = "imageHost/" + fileName;
            // 上传文件
            PutObjectResult putResult = cosClientConfig.getCosClient().putObject(bucketName, key, inputStream, objectMetadata);
            ret = putResult.getETag();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "https://" + bucketName + "." + endpoint + "/imageHost/" + fileName;
    }

    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @param filenameExtension 文件后缀
     * @return String
     */
    public static String getContentType(String filenameExtension) {
        if (filenameExtension.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase("jpeg") || filenameExtension.equalsIgnoreCase("jpg")
                || filenameExtension.equalsIgnoreCase("png")) {
            return "image/jpeg";
        }
        if (filenameExtension.equalsIgnoreCase("html")) {
            return "text/html";
        }
        if (filenameExtension.equalsIgnoreCase("txt")) {
            return "text/plain";
        }
        if (filenameExtension.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        }
        if (filenameExtension.equalsIgnoreCase("pptx") || filenameExtension.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (filenameExtension.equalsIgnoreCase("docx") || filenameExtension.equalsIgnoreCase("doc")) {
            return "application/msword";
        }
        if (filenameExtension.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        return "application/octet-stream";
    }

}

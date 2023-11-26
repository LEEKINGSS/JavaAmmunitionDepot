package cn.liking.service;

import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件操作
 * @author liking
 */
public interface IFileOperation {
    /**
     * 上传文件
     */
    String uploadFile(MultipartFile file);

    /**
     * 获取文件
     */
    void getFile(String fileId, String bucket);
}

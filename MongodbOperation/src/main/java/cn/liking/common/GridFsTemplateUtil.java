package cn.liking.common;

import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * mongodb 操作文件工具类
 *
 * @author liking
 * @date 2023/11/26 13:19
 */
@Slf4j
@Component
public class GridFsTemplateUtil {

    @Resource(name = "gridFsTemplate")
    private GridFsTemplate gridFsTemplate;

    @Resource(name = "gridFsTemplateTool")
    private GridFsTemplate gridFsTemplateTool;

    /**
     * 文件保存
     *
     * @param inputStream
     * @param fileName
     * @param contentType
     * @param bucket
     * @return String
     * @author song
     * @date 2023/10/19 10:23
     */
    public String saveFile(InputStream inputStream, String fileName, String contentType, String bucket) {
        ObjectId id;
        try {
            if (SystemConstant.BUSINESS_BUCKET.equals(bucket)) {
                id = gridFsTemplate.store(inputStream, fileName, contentType);
            } else {
                id = gridFsTemplateTool.store(inputStream, fileName, contentType);
            }
            return id.toString();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
    }

    /**
     * 获取文件
     *
     * @param id 文件id
     * @param bucket 桶名
     * @return GridFsResource
     * @author song
     * @date 2023/10/18 14:29
     */
    public GridFsResource getFile(String id, String bucket) {
        Query query = Query.query(Criteria.where("_id").is(new ObjectId(id)));
        GridFSFile gridFsFile;
        GridFsResource resource;
        if (SystemConstant.BUSINESS_BUCKET.equals(bucket)) {
            gridFsFile = gridFsTemplate.findOne(query);
            resource = gridFsTemplate.getResource(gridFsFile);
        } else {
            gridFsFile = gridFsTemplateTool.findOne(query);
            resource = gridFsTemplateTool.getResource(gridFsFile);
        }
        return resource;
    }

    /**
     * 删除文件
     *
     * @param id     文件id
     * @param bucket 桶名
     * @author song
     * @date 2023/10/18 14:30
     */
    public void deleteFile(String id, String bucket) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(new ObjectId(id)));
        if (SystemConstant.BUSINESS_BUCKET.equals(bucket)) {
            gridFsTemplate.delete(query);
        } else {
            gridFsTemplateTool.delete(query);
        }
    }

}

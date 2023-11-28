package cn.liking.service.impl;

import cn.liking.common.GridFsTemplateUtil;
import cn.liking.common.ReflexMethodUtil;
import cn.liking.common.SystemConstant;
import cn.liking.service.IEmployeesService;
import cn.liking.service.IFileOperation;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import io.micrometer.core.instrument.util.IOUtils;
import org.mockito.internal.util.io.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 文件操作实现类
 *
 * @author liking
 */
@Service
public class FileOperationImpl implements IFileOperation {
    @Autowired
    private GridFsTemplateUtil gridFsTemplateUtil;

    @Autowired
    private IEmployeesService employeesService;


    @Override
    public String uploadFile(MultipartFile file) {
        String fileId;
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
            String contentType = file.getContentType();
            String fileName = file.getOriginalFilename();
            fileId = gridFsTemplateUtil.saveFile(inputStream, fileName, contentType, SystemConstant.BUSINESS_BUCKET);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return fileId;
    }

    @Override
    public void getFile(String fileId, String bucket) {
        GridFsResource file = gridFsTemplateUtil.getFile(fileId, bucket);
        try {
            InputStream inputStream = file.getInputStream();
            String str = IOUtils.toString(inputStream);
            JSONArray jsonArray = JSON.parseArray(str);
            for(int i=0;i<jsonArray.size();i++){
                Map<String,Object> map = (Map<String, Object>) jsonArray.get(i);
                for (String key : map.keySet()) {
                    String className = "cn.liking.entity.Employees";
                    List obj = JSON.parseArray(map.get(key).toString(), Class.forName(className));
                    ReflexMethodUtil.executeMethod(key,"printBatch",obj, List.class);
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFile(String fileId, String businessBucket) {
        gridFsTemplateUtil.deleteFile(fileId, businessBucket);
    }
}

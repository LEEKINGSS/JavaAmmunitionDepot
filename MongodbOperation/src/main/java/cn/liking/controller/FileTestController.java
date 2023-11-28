package cn.liking.controller;

import cn.liking.common.Response;
import cn.liking.common.SystemConstant;
import cn.liking.service.IFileOperation;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * 文件解析
 *
 * @author lipeng
 */
@Api(tags = "文件解析 API")
@RestController
@RequestMapping("/file-analysis")
public class FileTestController {

    @Autowired
    private IFileOperation fileOperation;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Response uploadFile(@RequestPart @RequestParam("file") MultipartFile file) {
        String fileId = fileOperation.uploadFile(file);
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", fileId);
    }

    /**
     * 获取文件
     */
    @GetMapping("/get/{fileId}")
    public Response getFile(@PathVariable String fileId) {
        fileOperation.getFile(fileId, SystemConstant.BUSINESS_BUCKET);
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", "");
    }

    /**
     * 删除文件
     */
    @DeleteMapping("/delete/{fileId}")
    public Response deleteFile(@PathVariable String fileId) {
        fileOperation.deleteFile(fileId, SystemConstant.BUSINESS_BUCKET);
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", "");
    }


//        String content;
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
//            content = reader.lines().collect(Collectors.joining("\n"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", content);

}

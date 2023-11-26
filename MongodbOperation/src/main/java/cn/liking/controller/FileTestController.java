package cn.liking.controller;

import cn.liking.common.Response;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * 文件解析
 * @author lipeng
 */
@Api(tags = "文件解析 API")
@RestController
@RequestMapping("/file-analysis")
public class FileTestController {

    @PostMapping("/upload")
    public Response uploadFile(@RequestPart @RequestParam("file") MultipartFile file) {
        String content;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            content = reader.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", content);
    }

}

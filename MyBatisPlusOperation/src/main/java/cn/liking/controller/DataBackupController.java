package cn.liking.controller;

import cn.liking.common.Response;
import cn.liking.service.IDataBackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liking
 * 数据库备份操作
 */

@Api(tags = "数据库备份表 API")
@RestController
@RequestMapping("/databackup")
public class DataBackupController {

    @Autowired
    private IDataBackService iDataBackService;

    /**
     * 备份员工信息表
     */
    @PostMapping("/backupEmployees")
    @ApiOperation("备份员工信息表")
    public Response backupEmployees() {
        iDataBackService.backupEmployees();
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", null);
    }

    /**
     * 恢复员工信息表
     */
    @PostMapping("/restoreEmployees")
    @ApiOperation("恢复员工信息表")
    public Response restoreEmployees() {
        iDataBackService.recoverEmployees();
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", null);
    }


}

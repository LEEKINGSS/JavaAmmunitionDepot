package cn.liking.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.liking.common.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import cn.liking.service.IDepartmentsService;
import cn.liking.entity.Departments;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import cn.liking.common.BaseController;

/**
 * <p>
 * 部门信息表 前端控制器
 * </p>
 *
 * @author liking
 * @since 2023-11-16
 */
@Api(tags = "部门信息表 API")
@RestController
@RequestMapping("/departments")
public class DepartmentsController extends BaseController {

    @Autowired
    private IDepartmentsService iDepartmentsService;

    /**
     * 分页列表查询
     */
    @PostMapping("/listByPage")
    @ApiOperation("分页列表查询")
    public Response list(@RequestBody PageQuery<Departments> pageQuery) {
        Page departmentsPage = getPage(pageQuery);
        Page page = iDepartmentsService.page(departmentsPage, Wrappers.lambdaQuery(pageQuery.getObj()));
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", page);
    }

    /**
     * 普通列表查询
     */
    @PostMapping("/list")
    @ApiOperation("普通列表查询")
    public Response list(@RequestBody Departments departments) {
        List<Departments> list = iDepartmentsService.list(Wrappers.lambdaQuery(departments));
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", list);
    }

    /**
     * 根据id获取数据
     */
    @GetMapping("/get")
    @ApiOperation("根据id获取数据")
    public Response get(String id) {
        Departments departments = iDepartmentsService.getById(id);
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", departments);
    }

    /**
     * 添加数据
     */
    @PostMapping("/save")
    @ApiOperation("添加数据")
    public Response save(@RequestBody Departments departments) {
        iDepartmentsService.save(departments);
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", departments);
    }

    /**
     * 更新数据
     */
    @PostMapping("/update")
    @ApiOperation("更新数据")
    public Response update(@RequestBody Departments departments) {
        iDepartmentsService.updateById(departments);
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", departments);
    }

    /**
     * 删除数据(可批量删除)
     */
    @PostMapping("/remove")
    @ApiOperation("删除数据(可批量删除)")
    public Response remove(@RequestBody List<String> ids) {
        List<Integer> intIds = ids.stream().map(item -> Integer.parseInt(item)).collect(java.util.stream.Collectors.toList());
        iDepartmentsService.removeByIds(intIds);
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", null);
    }

}


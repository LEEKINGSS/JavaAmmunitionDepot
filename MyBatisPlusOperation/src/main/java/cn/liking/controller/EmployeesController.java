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
import cn.liking.service.IEmployeesService;
import cn.liking.entity.Employees;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import cn.liking.common.BaseController;

/**
 * <p>
 * 员工信息表 前端控制器
 * </p>
 *
 * @author liking
 * @since 2023-11-16
 */
@Api(tags = "员工信息表 API")
@RestController
@RequestMapping("/employees")
public class EmployeesController extends BaseController {

    @Autowired
    private IEmployeesService iEmployeesService;

    /**
     * 分页列表查询
     */
    @PostMapping("/listByPage")
    @ApiOperation("分页列表查询")
    public Response list(@RequestBody PageQuery<Employees> pageQuery) {
        Page employeesPage = getPage(pageQuery);
        Page page = iEmployeesService.page(employeesPage, Wrappers.lambdaQuery(pageQuery.getObj()));
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", page);
    }

    /**
     * 普通列表查询
     */
    @PostMapping("/list")
    @ApiOperation("普通列表查询")
    public Response list(@RequestBody Employees employees) {
        List<Employees> list = iEmployeesService.list(Wrappers.lambdaQuery(employees));
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", list);
    }

    /**
     * 根据id获取数据
     */
    @GetMapping("/get")
    @ApiOperation("根据id获取数据")
    public Response get(String id) {
        Employees employees = iEmployeesService.getById(id);
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", employees);
    }

    /**
     * 添加数据
     */
    @PostMapping("/save")
    @ApiOperation("添加数据")
    public Response save(@RequestBody Employees employees) {
        iEmployeesService.save(employees);
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", employees);
    }

    /**
     * 更新数据
     */
    @PostMapping("/update")
    @ApiOperation("更新数据")
    public Response update(@RequestBody Employees employees) {
        iEmployeesService.updateById(employees);
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", employees);
    }

    /**
     * 删除数据(可批量删除)
     */
    @PostMapping("/remove")
    @ApiOperation("删除数据(可批量删除)")
    public Response remove(@RequestBody List<String> ids) {
        iEmployeesService.removeByIds(ids);
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", null);
    }

}


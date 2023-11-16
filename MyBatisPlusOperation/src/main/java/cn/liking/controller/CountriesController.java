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
import cn.liking.service.ICountriesService;
import cn.liking.entity.Countries;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import cn.liking.common.BaseController;

/**
 * <p>
 * 城市信息表 前端控制器
 * </p>
 *
 * @author liking
 * @since 2023-11-16
 */
@Api(tags = "城市信息表 API")
@RestController
@RequestMapping("/countries")
public class CountriesController extends BaseController {

    @Autowired
    private ICountriesService iCountriesService;

    /**
     * 分页列表查询
     */
    @PostMapping("/listByPage")
    @ApiOperation("分页列表查询")
    public Response list(@RequestBody PageQuery<Countries> pageQuery) {
        Page countriesPage = getPage(pageQuery);
        Page page = iCountriesService.page(countriesPage, Wrappers.lambdaQuery(pageQuery.getObj()));
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", page);
    }

    /**
     * 普通列表查询
     */
    @PostMapping("/list")
    @ApiOperation("普通列表查询")
    public Response list(@RequestBody Countries countries) {
        List<Countries> list = iCountriesService.list(Wrappers.lambdaQuery(countries));
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", list);
    }

    /**
     * 根据id获取数据
     */
    @GetMapping("/get")
    @ApiOperation("根据id获取数据")
    public Response get(String id) {
        Countries countries = iCountriesService.getById(id);
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", countries);
    }

    /**
     * 添加数据
     */
    @PostMapping("/save")
    @ApiOperation("添加数据")
    public Response save(@RequestBody Countries countries) {
        iCountriesService.save(countries);
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", countries);
    }

    /**
     * 更新数据
     */
    @PostMapping("/update")
    @ApiOperation("更新数据")
    public Response update(@RequestBody Countries countries) {
        iCountriesService.updateById(countries);
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", countries);
    }

    /**
     * 删除数据(可批量删除)
     */
    @PostMapping("/remove")
    @ApiOperation("删除数据(可批量删除)")
    public Response remove(@RequestBody List<String> ids) {
        iCountriesService.removeByIds(ids);
        return Response.getInstance().setOk(Response.CodeEnum.SUCCESSED, "", "success", null);
    }

}


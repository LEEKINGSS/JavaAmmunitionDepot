package cn.liking.service;

import cn.liking.entity.Employees;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 员工信息表 服务类
 * </p>
 *
 * @author liking
 * @since 2023-11-16
 */
public interface IEmployeesService extends IService<Employees> {

    Page pageEmployeesVOList(Page employeesPage, Employees obj);
}

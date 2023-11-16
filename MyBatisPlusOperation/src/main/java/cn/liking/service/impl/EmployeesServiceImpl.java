package cn.liking.service.impl;

import cn.liking.entity.Employees;
import cn.liking.mapper.EmployeesMapper;
import cn.liking.service.IEmployeesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工信息表 服务实现类
 * </p>
 *
 * @author liking
 * @since 2023-11-16
 */
@Service
public class EmployeesServiceImpl extends ServiceImpl<EmployeesMapper, Employees> implements IEmployeesService {

}

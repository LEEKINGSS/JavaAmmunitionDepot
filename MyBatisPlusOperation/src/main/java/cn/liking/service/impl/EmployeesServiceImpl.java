package cn.liking.service.impl;

import cn.liking.entity.Countries;
import cn.liking.entity.Departments;
import cn.liking.entity.Employees;
import cn.liking.entity.vo.EmployeesVO;
import cn.liking.mapper.EmployeesMapper;
import cn.liking.service.ICountriesService;
import cn.liking.service.IDepartmentsService;
import cn.liking.service.IEmployeesService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private ICountriesService countriesService;

    @Autowired
    private IEmployeesService employeesService;

    @Autowired
    private IDepartmentsService departmentsService;

    @Override
    public Page pageEmployeesVOList(Page employeesPage, Employees obj) {
        Page page = this.page(employeesPage, Wrappers.lambdaQuery(obj));
        List<Employees> employeesList = page.getRecords();
        page.setRecords(employeesToVO(employeesList));
        return page;
    }

    private List employeesToVO(List<Employees> employeesList) {
        //获取部门类所有数据
        Map<Integer, String> departmentMap = departmentsService.list().stream().collect(Collectors.toMap(Departments::getDepartmentId, Departments::getDepartmentName));
        //获取员工类所有数据
        Map<Integer, String> employeesMap = employeesService.list().stream().collect(Collectors.toMap(Employees::getEmployeeId,Employees::getFirstName));
        List<EmployeesVO> employeesVOList = employeesList.stream().map(employees -> {
            EmployeesVO employeesVO = new EmployeesVO();
            BeanUtils.copyProperties(employees, employeesVO);
            //根据用户id获取用户名称
            if (employees.getManagerId() != null) {
                employeesVO.setManagerName(employeesMap.get(employees.getManagerId()));
            }
            //根据城市id获取城市名称
            employeesVO.setDepartmentName(departmentMap.get(employees.getDepartmentId()));
            return employeesVO;
        }).collect(Collectors.toList());
        return employeesVOList;
    }
}

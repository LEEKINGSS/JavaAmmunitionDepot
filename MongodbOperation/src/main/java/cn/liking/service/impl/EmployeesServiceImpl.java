package cn.liking.service.impl;

import cn.liking.service.IEmployeesService;

import java.util.List;

/**
 * 员工接口实现类
 * @Author: liking
 */
public class EmployeesServiceImpl implements IEmployeesService {
    /**
     * 展示员工信息
     * @param obj
     */
    @Override
    public void printBatch(List obj) {
        System.out.println(obj);
    }
}

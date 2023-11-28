package cn.liking.service;


import cn.liking.entity.Employees;

import java.util.List;

/**
 * 员工接口类
 * @author liking
 */
public interface IEmployeesService {
    /**
     * 展示员工信息
     */
    void printBatch(List obj);
}

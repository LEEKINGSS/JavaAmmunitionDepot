package cn.liking.service.impl;

import cn.liking.entity.Departments;
import cn.liking.mapper.DepartmentsMapper;
import cn.liking.service.IDepartmentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门信息表 服务实现类
 * </p>
 *
 * @author liking
 * @since 2023-11-16
 */
@Service
public class DepartmentsServiceImpl extends ServiceImpl<DepartmentsMapper, Departments> implements IDepartmentsService {

}

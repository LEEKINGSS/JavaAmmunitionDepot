package cn.liking.entity.vo;

import cn.liking.entity.Employees;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 部门信息表VO
 * @author liking
 */
@Data
@ApiModel(value = "Employees对象VO", description = "部门信息表VO")
public class EmployeesVO extends Employees {

    @ApiModelProperty("管理员名称")
    private String managerName;

    @ApiModelProperty("部门名称")
    private String departmentName;

    public EmployeesVO() {
    }

    public EmployeesVO(Employees employees) {
        super(employees);
    }


}

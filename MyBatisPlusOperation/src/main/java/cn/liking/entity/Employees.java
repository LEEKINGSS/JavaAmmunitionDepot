package cn.liking.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 员工信息表
 * </p>
 *
 * @author liking
 * @since 2023-11-16
 */
@Getter
@Setter
@TableName("t_employees")
@ApiModel(value = "Employees对象", description = "员工信息表")
public class Employees implements Serializable {

    public Employees() {
    }

    public Employees(Employees employees) {
        this.employeeId = employees.getEmployeeId();
        this.firstName = employees.getFirstName();
        this.lastName = employees.getLastName();
        this.email = employees.getEmail();
        this.phoneNumber = employees.getPhoneNumber();
        this.salary = employees.getSalary();
        this.managerId = employees.getManagerId();
        this.departmentId = employees.getDepartmentId();
        this.createTime = employees.getCreateTime();
        this.updateTime = employees.getUpdateTime();
        this.deteled = employees.getDeteled();
    }

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableId(value = "employee_id", type = IdType.AUTO)
    private Integer employeeId;

    @ApiModelProperty("用户名")
    private String firstName;

    @ApiModelProperty("用户姓")
    private String lastName;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("电话号码")
    private String phoneNumber;

    @ApiModelProperty("用户工资")
    private Double salary;

    @ApiModelProperty("管理员id")
    private Integer managerId;

    @ApiModelProperty("部门id")
    private Integer departmentId;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty("逻辑删除（0未删除，1已删除）")
    private Integer deteled;
}

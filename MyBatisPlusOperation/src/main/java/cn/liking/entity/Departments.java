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
 * 部门信息表
 * </p>
 *
 * @author liking
 * @since 2023-11-16
 */
@Getter
@Setter
@TableName("t_departments")
@ApiModel(value = "Departments对象", description = "部门信息表")
public class Departments implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("部门id")
    @TableId(value = "department_id", type = IdType.ASSIGN_ID)
    private Integer departmentId;

    @ApiModelProperty("部门名称")
    private String departmentName;

    @ApiModelProperty("管理员id")
    private Integer managerId;

    @ApiModelProperty("位置id")
    private Integer locationId;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("更新id")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty("逻辑删除(0未删除，1已删除)")
    private Integer deleted;
}

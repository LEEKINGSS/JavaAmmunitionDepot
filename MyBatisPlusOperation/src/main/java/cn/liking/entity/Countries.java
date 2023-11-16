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
 * 城市信息表
 * </p>
 *
 * @author liking
 * @since 2023-11-16
 */
@Getter
@Setter
@TableName("t_countries")
@ApiModel(value = "Countries对象", description = "城市信息表")
public class Countries implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("城市id")
    @TableId(value = "country_id", type = IdType.ASSIGN_ID)
    private String countryId;

    @ApiModelProperty("城市名称")
    private String countryName;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}

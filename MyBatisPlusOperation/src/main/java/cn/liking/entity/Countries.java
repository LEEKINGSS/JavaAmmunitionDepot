package cn.liking.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author liking
 * @since 2023-11-16
 */
@Getter
@Setter
@TableName("t_countries")
public class Countries implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 城市id
     */
    @TableId(value = "country_id", type = IdType.ASSIGN_ID)
    private String countryId;

    /**
     * 城市名称
     */
    private String countryName;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}

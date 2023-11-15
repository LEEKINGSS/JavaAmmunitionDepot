package cn.liking.mapper;

import cn.liking.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * (User)表数据库访问层
 * @since 2021-03-10 15:58:35
 * @author liking
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
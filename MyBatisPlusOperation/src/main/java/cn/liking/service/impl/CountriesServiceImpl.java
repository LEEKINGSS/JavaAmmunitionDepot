package cn.liking.service.impl;

import cn.liking.entity.Countries;
import cn.liking.mapper.CountriesMapper;
import cn.liking.service.ICountriesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 城市信息表 服务实现类
 * </p>
 *
 * @author liking
 * @since 2023-11-16
 */
@Service
public class CountriesServiceImpl extends ServiceImpl<CountriesMapper, Countries> implements ICountriesService {

}

package cn.liking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import static cn.liking.common.SystemConstant.BUSINESS_BUCKET;
import static cn.liking.common.SystemConstant.TOOL_BUCKET;

/**
 * @author liking
 * @date 2023/15/44 15:03
 */
@Configuration
public class MongodbConfig {

    /**
     * 业务类用的桶
     *
     * @param dbFactory
     * @param converter
     * @return GridFsTemplate
     * @author song
     * @date 2023/10/18 19:43
     */
    @Bean(name = "gridFsTemplate")
    public GridFsTemplate gridFsTemplate(MongoDatabaseFactory dbFactory, MongoConverter converter) {
        return new GridFsTemplate(dbFactory, converter, BUSINESS_BUCKET);
    }

    /**
     * 工具类用的桶
     *
     * @param dbFactory
     * @param converter
     * @return GridFsTemplate
     * @author song
     * @date 2023/10/18 19:43
     */
    @Bean(name = "gridFsTemplateTool")
    public GridFsTemplate gridFsTemplateTool(MongoDatabaseFactory dbFactory, MongoConverter converter) {
        return new GridFsTemplate(dbFactory, converter, TOOL_BUCKET);
    }

}

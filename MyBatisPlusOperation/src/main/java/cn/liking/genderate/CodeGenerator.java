package cn.liking.genderate;

import cn.liking.common.BaseController;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.sql.Types;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 用于生成代码 只需要输入表名即可
 * </p>
 *
 * @author liking
 * @date 2023-11-16
 */
public class CodeGenerator {

    public static final String URL = "jdbc:mysql://bj-cynosdbmysql-grp-hjy6r29e.sql.tencentcdb.com:20553/mybatisplus_db?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&autoReconnect=true&failOverReadOnly=false";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "LIpeng158.";
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig.Builder(URL, USERNAME, PASSWORD);

    public static void main(String[] args) {
        //程序的入口方法
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig((scanner, builder) -> builder
                        .author(scanner.apply("请输入作者名称？"))
                        //设置日期类型为仅日期，即只保留日期部分，不包含时间。
                        .dateType(DateType.ONLY_DATE)
                        //输出目录为当前项目的根目录下的 src/main/java 目录。
                        .outputDir(System.getProperty("user.dir") + "/MyBatisPlusOperation/src/main/java")
                        .disableOpenDir()
                        //启用Swagger注解支持，生成的代码中会包含Swagger相关的注解。
                        //.enableSwagger()
                        )
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    //数据库类型为tinyint时候设置为Integer(不设置默认是Boolean)
                    if (typeCode == Types.BIT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);
                }))
                // 包配置
                .packageConfig((scanner, builder) -> builder
                        .parent("cn.liking")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/MyBatisPlusOperation/src/main/resources/mapper"))
                )
//                策略配置（可覆盖/数据库以名字t_开头）
                .strategyConfig((scanner, builder) -> builder.addTablePrefix("t_").addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .controllerBuilder()
                        //生成的控制器的父类为 BaseController。
                        .superClass(BaseController.class)
                        //启用REST风格的控制器，生成的控制器会包含常见的RESTful API方法。
                        .enableRestStyle()
                        //启用驼峰转连字符，即将控制器的类名中的驼峰部分转换为连字符，如：UserController会变为user-controller。
                        .enableHyphenStyle()
                        //启用文件覆盖，即如果生成的文件已存在，会直接覆盖原文件。
                        .enableFileOverride()
                        .mapperBuilder()
                        //启用BaseResultMap，生成的XML中会包含BaseResultMap。
                        .enableBaseResultMap()
                        //启用BaseColumnList，生成的XML中会包含BaseColumnList。
                        .enableFileOverride()
                        //指定生成实体类配置
                        .entityBuilder()
                        //设置实体类的主键生成策略为 ASSIGN_ID，即手动指定。
                        .idType(IdType.ASSIGN_ID)
                        //启用Lombok插件，生成的实体类会自动添加Lombok注解。
                        .enableLombok()
                        //设置实体类的填充字段，即在插入和更新时自动填充指定的字段。
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT),
                                new Column("update_time", FieldFill.INSERT_UPDATE)
                        )
                        .enableFileOverride()
                        .serviceBuilder()
                        .enableFileOverride()
                        .build())
                // 模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }

    /**
     * 处理all的情况
     */
    protected static List<String> getTables(String tables) {
        //多个表名使用逗号分隔
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }


}

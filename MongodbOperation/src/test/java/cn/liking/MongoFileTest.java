package cn.liking;

import cn.liking.common.GridFsTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Mongodb进阶文件操作
 * @author liking
 */

@SpringBootTest(classes = MongodbOperationApplication.class)
public class MongoFileTest {
    @Autowired
    private GridFsTemplateUtil gridFsTemplateUtil;


}

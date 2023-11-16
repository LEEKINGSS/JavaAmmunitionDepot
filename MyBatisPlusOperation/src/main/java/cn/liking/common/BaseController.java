package cn.liking.common;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.servlet.http.HttpServletRequest;

/**
 * 顶级controller
 * @author liking
 */
public class BaseController {


    /**
     * 分页方式二[2]
     *
     * @param pageQuery
     * @return
     */
    protected Page getPage(PageQuery pageQuery) {
        Long pageNum = pageQuery.getPageNum();
        Long pageSize = pageQuery.getPageSize();
        Page alertRecordPage = new Page<>();
        if (ObjectUtil.isNotEmpty(pageNum) && ObjectUtil.isNotEmpty(pageSize)) {
            alertRecordPage = new Page<>(pageNum, pageSize);
        }
        return alertRecordPage;
    }

}

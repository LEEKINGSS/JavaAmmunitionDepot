package cn.liking.common;

import lombok.Data;

/**
 * @author ws
 * @param <T>
 */
@Data
public class PageQuery<T> {

    private Long pageNum;

    private Long pageSize;

    private T obj;

}

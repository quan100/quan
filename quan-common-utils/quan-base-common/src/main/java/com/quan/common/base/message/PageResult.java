package com.quan.common.base.message;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author: wangquan
 * @date: 18-12-16 14:54
 * @description:
 */
@Data
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = -6090269741449990770L;

    // 分页参数
    /**
     * 总数
     */
    private long total;

    /**
     * 总页数
     */
    private int pages;

    /**
     * 页码，从1开始
     */
    private Integer pageNum;

    /**
     * 页面大小
     */
    private Integer pageSize;

    /**
     * 查询数据列表
     */
    private List<T> records = Collections.emptyList();

}

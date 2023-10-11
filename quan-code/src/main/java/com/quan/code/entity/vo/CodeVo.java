package com.quan.code.entity.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangquan
 * @date 2020/8/23 15:49
 */
public class CodeVo implements Serializable {

    private static final long serialVersionUID = -4185290728526113744L;
    private List<String> tableNames;

    public List<String> getTableNames() {
        return tableNames;
    }

    public CodeVo setTableNames(List<String> tableNames) {
        this.tableNames = tableNames;
        return this;
    }
}

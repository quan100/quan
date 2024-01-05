package cn.javaquan.common.base.message;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author: wangquan
 * @date: 18-12-16 15:02
 * @description:
 */
@Data
public class BasePage implements Serializable {

    private static final long serialVersionUID = -5477370648213222451L;

    public static final Integer PAGE_NUM = 1;
    public static final Integer PAGE_SIZE = 10;

    // 分页参数

    /**
     * 页码，从1开始
     */
    @Min(value = 1, message = "页码从1开始")
    private Integer pageNum;

    /**
     * 页面大小
     */
    @Max(value = 100, message = "超过最大值")
    private Integer pageSize;

    public Integer getPageNum() {
        return null == pageNum ? PAGE_NUM : pageNum;
    }

    public Integer getPageSize() {
        return null == pageSize ? PAGE_SIZE : pageSize;
    }
}

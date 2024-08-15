package cn.javaquan.common.base.message;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 基础分页.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Data
public class BasePage implements Serializable {

    private static final long serialVersionUID = -5477370648213222451L;

    /**
     * 默认页码.
     */
    public static final Integer PAGE_NUM = 1;

    /**
     * 默认页面大小.
     */
    public static final Integer PAGE_SIZE = 10;

    // 分页参数

    /**
     * 页码，从1开始.
     */
    @Min(value = 1, message = "页码从1开始")
    private Integer pageNum;

    /**
     * 页面大小.
     */
    @Max(value = 100, message = "超过最大值")
    private Integer pageSize;

    /**
     * 获取页码.
     * @return 页码，当页码为空时，获取默认值。
     */
    public Integer getPageNum() {
        return (null == this.pageNum) ? PAGE_NUM : this.pageNum;
    }

    /**
     * 获取页面大小.
     * @return 页面大小，当页面大小为空时，获取默认值。
     */
    public Integer getPageSize() {
        return (null == this.pageSize) ? PAGE_SIZE : this.pageSize;
    }

}

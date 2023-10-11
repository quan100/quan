package com.quan.app.common.module.article;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 热点文章查询
 *
 * @author wangquan
 */
@Data
public class HotArticleQuery implements Serializable {

    private static final long serialVersionUID = 1876294478340132249L;

    /**
     * 文章ID
     */
    private String articleId;

    /**
     * 分类ID
     */
    @NotBlank(message = "分类ID不可为空")
    private String categoryId;

    /**
     * 标签ID
     */
    private String tagId;

    /**
     * 记录数
     */
    @Max(value = 10, message = "最多获取10条记录")
    private Integer pageSize;
}

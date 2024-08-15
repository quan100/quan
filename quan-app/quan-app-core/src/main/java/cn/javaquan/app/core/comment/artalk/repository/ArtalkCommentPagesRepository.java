package cn.javaquan.app.core.comment.artalk.repository;

import cn.javaquan.app.core.comment.artalk.entity.ArtalkCommentPagesPO;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * artalk 评论数据接口.
 *
 * @author javaquan
 * @since 1.0.0
 */
public interface ArtalkCommentPagesRepository extends IService<ArtalkCommentPagesPO> {

    /**
     * 分页查询.
     * @param po 查询参数
     * @param basePage 分页参数
     * @return 查询结果
     */
    PageResult<ArtalkCommentPagesPO> page(ArtalkCommentPagesPO po, BasePage basePage);

    /**
     * 查询页面统计数据.
     * @param keys 页面唯一键
     * @return 统计数据
     */
    List<ArtalkCommentPagesPO> statistics(List<String> keys);

}

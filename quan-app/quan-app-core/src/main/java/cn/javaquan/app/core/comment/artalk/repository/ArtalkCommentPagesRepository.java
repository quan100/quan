package cn.javaquan.app.core.comment.artalk.repository;

import cn.javaquan.app.core.comment.artalk.entity.ArtalkCommentPagesPO;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author JavaQuan
 * @version 1.0.0
 */
public interface ArtalkCommentPagesRepository extends IService<ArtalkCommentPagesPO> {

    /**
     * 分页查询
     *
     * @param po
     * @param basePage
     * @return
     */
    PageResult<ArtalkCommentPagesPO> page(ArtalkCommentPagesPO po, BasePage basePage);

    /**
     * 查询页面统计数据
     *
     * @param keys
     * @return
     */
    List<ArtalkCommentPagesPO> statistics(List<String> keys);
}


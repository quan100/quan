package cn.javaquan.app.core.article.controller;

import cn.javaquan.app.core.article.convert.ArticleCategoryConfigAssembler;
import cn.javaquan.app.core.article.entity.ArticleCategoryConfigPO;
import cn.javaquan.app.core.article.repository.ArticleCategoryConfigRepository;
import cn.javaquan.app.common.module.article.ArticleCategoryConfigAddCommand;
import cn.javaquan.app.common.module.article.ArticleCategoryConfigQuery;
import cn.javaquan.app.common.module.article.ArticleCategoryConfigUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 文章分类配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/article/category/config/")
public class ArticleCategoryConfigController {

    private final ArticleCategoryConfigRepository articleCategoryConfigRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ArticleCategoryConfigPO>> page(ArticleCategoryConfigQuery query) {
        ArticleCategoryConfigPO po = ArticleCategoryConfigAssembler.INSTANCE.toQueryPO(query);
        return Result.success(articleCategoryConfigRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ArticleCategoryConfigPO> details(@RequestParam Long id) {
        return Result.success(articleCategoryConfigRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ArticleCategoryConfigUpdateCommand cmd) {
        ArticleCategoryConfigPO po = ArticleCategoryConfigAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(articleCategoryConfigRepository.updateById(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ArticleCategoryConfigAddCommand cmd) {
        ArticleCategoryConfigPO po = ArticleCategoryConfigAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(articleCategoryConfigRepository.save(po));
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ArticleCategoryConfigAddCommand> cmds) {
        List<ArticleCategoryConfigPO> pos = ArticleCategoryConfigAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(articleCategoryConfigRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(articleCategoryConfigRepository.removeByIds(ids));
    }

}

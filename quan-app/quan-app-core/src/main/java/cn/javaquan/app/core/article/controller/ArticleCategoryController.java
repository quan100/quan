package cn.javaquan.app.core.article.controller;

import cn.javaquan.app.core.article.convert.ArticleCategoryAssembler;
import cn.javaquan.app.core.article.entity.ArticleCategoryPO;
import cn.javaquan.app.core.article.repository.ArticleCategoryRepository;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.common.module.article.ArticleCategoryAddCommand;
import cn.javaquan.app.common.module.article.ArticleCategoryQuery;
import cn.javaquan.app.common.module.article.ArticleCategoryUpdateCommand;
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
 * 文章分类.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/article/category/")
public class ArticleCategoryController {

    private final ArticleCategoryRepository articleCategoryRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ArticleCategoryPO>> page(ArticleCategoryQuery query) {
        ArticleCategoryPO po = ArticleCategoryAssembler.INSTANCE.toQueryPO(query);
        return Result.success(articleCategoryRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ArticleCategoryPO> details(@RequestParam Long id) {
        return Result.success(articleCategoryRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ArticleCategoryUpdateCommand cmd) {
        ArticleCategoryPO po = ArticleCategoryAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(articleCategoryRepository.updateById(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ArticleCategoryAddCommand cmd) {
        ArticleCategoryPO po = ArticleCategoryAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(articleCategoryRepository.save(po));
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ArticleCategoryAddCommand> cmds) {
        List<ArticleCategoryPO> pos = ArticleCategoryAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(articleCategoryRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(articleCategoryRepository.deleteByIds(ids));
    }

}

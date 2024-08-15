package cn.javaquan.app.core.article.controller;

import cn.javaquan.app.core.article.convert.ArticleTagConfigAssembler;
import cn.javaquan.app.core.article.entity.ArticleTagConfigPO;
import cn.javaquan.app.core.article.repository.ArticleTagConfigRepository;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.common.module.article.ArticleTagConfigAddCommand;
import cn.javaquan.app.common.module.article.ArticleTagConfigQuery;
import cn.javaquan.app.common.module.article.ArticleTagConfigUpdateCommand;
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
 * 文章标签配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/article/tag/config/")
public class ArticleTagConfigController {

    private final ArticleTagConfigRepository articleTagConfigRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ArticleTagConfigPO>> page(ArticleTagConfigQuery query) {
        ArticleTagConfigPO po = ArticleTagConfigAssembler.INSTANCE.toQueryPO(query);
        return Result.success(articleTagConfigRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ArticleTagConfigPO> details(@RequestParam Long id) {
        return Result.success(articleTagConfigRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ArticleTagConfigUpdateCommand cmd) {
        ArticleTagConfigPO po = ArticleTagConfigAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(articleTagConfigRepository.updateById(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ArticleTagConfigAddCommand cmd) {
        ArticleTagConfigPO po = ArticleTagConfigAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(articleTagConfigRepository.save(po));
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ArticleTagConfigAddCommand> cmds) {
        List<ArticleTagConfigPO> pos = ArticleTagConfigAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(articleTagConfigRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(articleTagConfigRepository.removeByIds(ids));
    }

}

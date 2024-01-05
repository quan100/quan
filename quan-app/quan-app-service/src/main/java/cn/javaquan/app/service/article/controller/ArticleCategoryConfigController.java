package cn.javaquan.app.service.article.controller;

import cn.javaquan.app.common.module.article.ArticleCategoryConfigAddCommand;
import cn.javaquan.app.common.module.article.ArticleCategoryConfigDTO;
import cn.javaquan.app.common.module.article.ArticleCategoryConfigQuery;
import cn.javaquan.app.common.module.article.ArticleCategoryConfigUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.article.service.ArticleCategoryConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 文章分类配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/article/category/config/")
public class ArticleCategoryConfigController {

    private final ArticleCategoryConfigService articleCategoryConfigService;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<ArticleCategoryConfigDTO>> page(ArticleCategoryConfigQuery query) {
        return articleCategoryConfigService.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<ArticleCategoryConfigDTO> details(@RequestParam Long id) {
        return articleCategoryConfigService.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ArticleCategoryConfigUpdateCommand cmd) {
        return articleCategoryConfigService.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ArticleCategoryConfigAddCommand cmd) {
        return articleCategoryConfigService.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ArticleCategoryConfigAddCommand> cmds) {
        return articleCategoryConfigService.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return articleCategoryConfigService.deleteByIds(ids);
    }

}

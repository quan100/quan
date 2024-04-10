package cn.javaquan.app.mobile.bff.comment.controller;

import cn.javaquan.app.common.module.artalk.OpenArtalkCommentPagesVO;
import cn.javaquan.app.mobile.bff.comment.service.ArtalkCommentPagesService;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author JavaQuan
 * @version 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/open/artalk/comment/pages/")
public class ArtalkCommentPagesController {

    private final ArtalkCommentPagesService artalkCommentPagesService;

    /**
     * 获取文章列表的统计数据
     *
     * @param keys
     * @return
     */
    @GetMapping("statistics")
    public Result<List<OpenArtalkCommentPagesVO>> statistics(@RequestParam(value = "keys")List<String> keys) {
        return artalkCommentPagesService.statistics(keys);
    }

}

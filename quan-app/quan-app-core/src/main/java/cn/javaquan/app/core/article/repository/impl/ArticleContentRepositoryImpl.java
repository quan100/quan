package cn.javaquan.app.core.article.repository.impl;

import cn.javaquan.app.core.article.repository.ArticleContentRepository;
import cn.javaquan.app.core.article.entity.ArticleContentPO;
import cn.javaquan.app.core.article.mapper.ArticleContentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文章内容 服务实现类.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Repository
public class ArticleContentRepositoryImpl implements ArticleContentRepository {

    private final MongoTemplate mongoTemplate;

    private final ArticleContentMapper articleContentMapper;

    @Override
    public boolean updateByArticleId(ArticleContentPO po) {
        articleContentMapper.save(po);
        return true;
    }

    @Override
    public boolean removeByByArticleIds(List<String> articleIds) {
        articleContentMapper.deleteAllById(articleIds);
        return true;
    }

    @Override
    public ArticleContentPO getOne(String articleId) {
        return mongoTemplate.findById(articleId, ArticleContentPO.class);
    }

    @Override
    public boolean save(ArticleContentPO po) {
        mongoTemplate.save(po);
        return true;
    }

}

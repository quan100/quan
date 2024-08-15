package cn.javaquan.app.core.article.mapper;

import cn.javaquan.app.core.article.entity.ArticleContentPO;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 文章内容.
 *
 * @author wangquan
 * @since 1.0.0
 */
public interface ArticleContentMapper extends MongoRepository<ArticleContentPO, String> {

}

package com.quan.app.core.article.mapper;

import com.quan.app.core.article.entity.ArticleContentPO;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 文章内容
 *
 * @author wangquan
 * @version 1.0.0
 * @date 2023-01-03 21:31:47
 */
public interface ArticleContentMapper extends MongoRepository<ArticleContentPO, String> {

}

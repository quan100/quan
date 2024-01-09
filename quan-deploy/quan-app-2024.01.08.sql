ALTER TABLE article ADD COLUMN `topping` bit(1) DEFAULT b'0' COMMENT '是否置顶，0：否，1：是';

ALTER TABLE article ADD INDEX `idx_article_id`(`article_id` ASC) USING BTREE COMMENT '文章业务主键';
ALTER TABLE article ADD INDEX `idx_category_id`(`category_id` ASC) USING BTREE COMMENT '分类业务主键';
ALTER TABLE article_category ADD INDEX `idx_category_id`(`category_id` ASC) USING BTREE COMMENT '分类业务主键';
ALTER TABLE article_category_config ADD INDEX `idx_article_id`(`article_id` ASC) USING BTREE COMMENT '文章业务主键';
ALTER TABLE article_category_config ADD INDEX `idx_category_id`(`category_id` ASC) USING BTREE COMMENT '分类业务主键';
ALTER TABLE article_content ADD INDEX `idx_article_id`(`article_id` ASC) USING BTREE COMMENT '文章业务主键';
ALTER TABLE article_tag ADD INDEX `idx_tag_id`(`tag_id` ASC) USING BTREE COMMENT '标签业务主键';
ALTER TABLE article_tag_config ADD INDEX `idx_article_id`(`article_id` ASC) USING BTREE COMMENT '文章业务主键';
ALTER TABLE article_tag_config ADD INDEX `idx_tag_id`(`tag_id` ASC) USING BTREE COMMENT '标签业务主键';

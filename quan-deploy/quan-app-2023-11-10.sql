-- 字段调整
ALTER TABLE `quan-app`.`article` MODIFY COLUMN `author_accounts_public` bit(1) NULL DEFAULT NULL COMMENT '作者联系账号是否公开' AFTER `author_accounts`;
ALTER TABLE `quan-app`.`article` MODIFY COLUMN `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除状态，0：正常，1：删除' AFTER `status`;
ALTER TABLE `quan-app`.`article_category` MODIFY COLUMN `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除状态，0：正常，1：删除' AFTER `update_user`;
ALTER TABLE `quan-app`.`article_category_config` MODIFY COLUMN `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除状态，0：正常，1：删除' AFTER `category_id`;
ALTER TABLE `quan-app`.`article_content` MODIFY COLUMN `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除状态，0：正常，1：删除' AFTER `update_time`;
ALTER TABLE `quan-app`.`article_tag` MODIFY COLUMN `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除状态，0：正常，1：删除' AFTER `update_time`;
ALTER TABLE `quan-app`.`article_tag_config` MODIFY COLUMN `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除状态，0：正常，1：删除' AFTER `tag_id`;
ALTER TABLE `quan-app`.`friendly_link` MODIFY COLUMN `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除状态，0：正常，1：删除' AFTER `status`;
ALTER TABLE `quan-app`.`sys_permission` MODIFY COLUMN `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除状态，0：正常，1：删除' AFTER `sort`;
ALTER TABLE `quan-app`.`sys_role` MODIFY COLUMN `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除状态，0：正常，1：删除' AFTER `status`;
ALTER TABLE `quan-app`.`sys_user_account` MODIFY COLUMN `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除状态，0：正常，1：删除' AFTER `status`;
ALTER TABLE `quan-app`.`sys_user_info` MODIFY COLUMN `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除状态，0：正常，1：删除' AFTER `avatar`;
ALTER TABLE `quan-app`.`sys_user_tripartite_account` MODIFY COLUMN `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除状态，0：正常，1：删除' AFTER `bind_status`;
ALTER TABLE `quan-app`.`tools` MODIFY COLUMN `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除状态，0：正常，1：删除' AFTER `status`;


-- chat 服务相关表
CREATE TABLE `quan-app`.`chat_group_info`  (
                                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                               `group_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '群组ID',
                                               `group_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '群组名称',
                                               `group_notice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '群公告，最大200个字符',
                                               `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '群主用户ID',
                                               `group_limit` int NULL DEFAULT 500 COMMENT '群成员数量限制，默认：500',
                                               `user_count` int NULL DEFAULT 0 COMMENT '群成员数量',
                                               `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                               `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                               `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除状态',
                                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '群组信息' ROW_FORMAT = Dynamic;

CREATE TABLE `quan-app`.`chat_history`  (
                                            `id` bigint NOT NULL COMMENT '主键',
                                            `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户ID',
                                            `friend_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '好友用户ID',
                                            `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '聊天内容',
                                            `send_time` timestamp NULL DEFAULT NULL COMMENT '发送时间',
                                            `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除状态',
                                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '聊天记录表' ROW_FORMAT = Dynamic;

CREATE TABLE `quan-app`.`chat_user_friend`  (
                                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                                `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户ID',
                                                `friend_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '好友用户ID',
                                                `friend_group_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '好友分组ID',
                                                `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                                `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                                `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除状态',
                                                `disturb` bit(1) NULL DEFAULT b'0' COMMENT '是否免打扰',
                                                `topping` bit(1) NULL DEFAULT b'0' COMMENT '是否置顶',
                                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户好友信息关联表' ROW_FORMAT = Dynamic;

CREATE TABLE `quan-app`.`chat_user_friend_group`  (
                                                      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                                      `group_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分组ID',
                                                      `group_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分组名称',
                                                      `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户ID',
                                                      `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                                      `disturb` bit(1) NULL DEFAULT b'0' COMMENT '是否免打扰',
                                                      `topping` bit(1) NULL DEFAULT b'0' COMMENT '是否置顶',
                                                      `order` int NULL DEFAULT NULL COMMENT '排序',
                                                      `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                                      `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                                      `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除状态',
                                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户好友分组表' ROW_FORMAT = Dynamic;

CREATE TABLE `quan-app`.`chat_user_group`  (
                                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                               `group_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '群组ID',
                                               `disturb` bit(1) NULL DEFAULT b'0' COMMENT '是否免打扰',
                                               `topping` bit(1) NULL DEFAULT b'0' COMMENT '是否置顶',
                                               `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户ID',
                                               `group_leader` tinyint(1) NULL DEFAULT 0 COMMENT '0：群员，1：群主，2：群管理员',
                                               `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                               `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                               `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除状态',
                                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户群组表' ROW_FORMAT = Dynamic;

CREATE TABLE `quan-app`.`chat_user_info`  (
                                              `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                              `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户ID',
                                              `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
                                              `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'online：在线；offline：离线',
                                              `sign` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '签名信息',
                                              `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
                                              `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                              `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                              `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除状态',
                                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

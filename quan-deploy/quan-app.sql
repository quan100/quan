/*
 Navicat Premium Data Transfer

 Source Server         : 14.116.150.209
 Source Server Type    : MySQL
 Source Server Version : 50736 (5.7.36)
 Source Host           : 14.116.150.209:3306
 Source Schema         : quan-app

 Target Server Type    : MySQL
 Target Server Version : 50736 (5.7.36)
 File Encoding         : 65001

 Date: 28/09/2023 11:54:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `article_id` varchar(32) DEFAULT NULL COMMENT '文章ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `author` varchar(64) DEFAULT NULL COMMENT '作者',
  `author_url` varchar(128) DEFAULT NULL COMMENT '作者链接',
  `type` tinyint(6) DEFAULT NULL COMMENT '文章类型, 1：原创，2：转载，3：官方',
  `publish_type` tinyint(1) DEFAULT NULL COMMENT '发布类型, 1：全部可见，2：仅自己可见，3：粉丝可见',
  `source` varchar(128) DEFAULT NULL COMMENT '文章来源',
  `source_url` varchar(128) DEFAULT NULL COMMENT '文章来源链接',
  `avatar` varchar(128) DEFAULT NULL COMMENT '图标地址',
  `cover` varchar(128) DEFAULT NULL COMMENT '封面图',
  `author_accounts` varchar(128) DEFAULT NULL COMMENT '作者联系账号',
  `author_accounts_public` tinyint(1) DEFAULT NULL COMMENT '作者联系账号是否公开',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `jump_url` varchar(128) DEFAULT NULL COMMENT '内容跳转链接',
  `jump_type` int(4) DEFAULT NULL COMMENT '跳转类型',
  `sort` int(8) DEFAULT '0' COMMENT '排序',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(32) DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  `status` tinyint(2) DEFAULT NULL COMMENT '状态，0：正常，1：审核中，2：审核不通过',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除状态，0：正常，1：删除',
  `brief_content` varchar(255) DEFAULT NULL COMMENT '文章缩略文',
  `category_id` varchar(32) DEFAULT NULL COMMENT '文章分类ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COMMENT='文章';

-- ----------------------------
-- Table structure for article_category
-- ----------------------------
DROP TABLE IF EXISTS `article_category`;
CREATE TABLE `article_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `category_id` varchar(32) DEFAULT NULL COMMENT '分类ID',
  `name` varchar(32) DEFAULT NULL COMMENT '分类名称',
  `type` tinyint(1) DEFAULT '0' COMMENT '0：自定义分类；1：系统分类',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(32) DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除状态，0：正常，1：删除',
  `color` varchar(16) DEFAULT NULL COMMENT '字体颜色',
  `sort` int(8) DEFAULT '0' COMMENT '分类排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='文章分类';

-- ----------------------------
-- Table structure for article_category_config
-- ----------------------------
DROP TABLE IF EXISTS `article_category_config`;
CREATE TABLE `article_category_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `article_id` varchar(32) DEFAULT NULL COMMENT '文章ID',
  `category_id` varchar(32) DEFAULT NULL COMMENT '分类ID',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除状态，0：正常，1：删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='文章分类配置';

-- ----------------------------
-- Table structure for article_content
-- ----------------------------
DROP TABLE IF EXISTS `article_content`;
CREATE TABLE `article_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `article_id` varchar(32) DEFAULT NULL COMMENT '文章ID',
  `content` text COMMENT '内容',
  `brief_content` varchar(1024) DEFAULT NULL COMMENT '文章缩略文',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除状态，0：正常，1：删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章内容';

-- ----------------------------
-- Table structure for article_tag
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `tag_id` varchar(32) DEFAULT NULL COMMENT '标签ID',
  `name` varchar(32) DEFAULT NULL COMMENT '标签名称',
  `color` varchar(16) DEFAULT NULL COMMENT '标签颜色',
  `icon` varchar(16) DEFAULT NULL COMMENT '标签图标',
  `type` tinyint(1) DEFAULT '0' COMMENT '0：自定义标签；1：系统标签',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除状态，0：正常，1：删除',
  `sort` int(8) DEFAULT '0' COMMENT '标签排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='文章标签';

-- ----------------------------
-- Table structure for article_tag_config
-- ----------------------------
DROP TABLE IF EXISTS `article_tag_config`;
CREATE TABLE `article_tag_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `article_id` varchar(32) DEFAULT NULL COMMENT '文章ID',
  `tag_id` varchar(32) DEFAULT NULL COMMENT '标签ID',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除状态，0：正常，1：删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COMMENT='文章标签配置';

-- ----------------------------
-- Table structure for base_config
-- ----------------------------
DROP TABLE IF EXISTS `base_config`;
CREATE TABLE `base_config` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `host` varchar(128) DEFAULT NULL COMMENT '服务器地址',
  `port` int(8) DEFAULT NULL COMMENT '服务端口',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `protocol` varchar(16) DEFAULT NULL COMMENT '协议',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='消息配置';

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `code` varchar(32) NOT NULL COMMENT '字典编码',
  `value` json DEFAULT NULL COMMENT '字典值',
  `open` tinyint(1) DEFAULT '0' COMMENT '是否开放权限，0：否，1：是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `name` varchar(64) DEFAULT NULL COMMENT '字典名称',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for friendly_link
-- ----------------------------
DROP TABLE IF EXISTS `friendly_link`;
CREATE TABLE `friendly_link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(64) DEFAULT NULL COMMENT '站点名称',
  `link_url` varchar(256) DEFAULT NULL COMMENT '链接',
  `avatar` varchar(128) DEFAULT NULL COMMENT '图标地址',
  `email` varchar(128) DEFAULT NULL COMMENT '联系邮箱',
  `email_public` tinyint(1) DEFAULT NULL COMMENT '联系邮箱是否公开',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `sort` int(8) DEFAULT '0' COMMENT '排序',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(32) DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  `status` tinyint(2) DEFAULT NULL COMMENT '状态，0：正常，1：审核中，2：审核不通过',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除状态，0：正常，1：删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='友情链接';

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '上级ID，顶级默认为0',
  `path` varchar(128) DEFAULT NULL COMMENT '配置可以被 path-to-regexp@^1.7.0 理解的路径通配符。',
  `component` varchar(64) DEFAULT NULL COMMENT '配置 location 和 path 匹配后用于渲染的 React 组件路径。可以是绝对路径，也可以是相对路径，如果是相对路径，会从 src/pages 开始找起。',
  `exact` tinyint(1) DEFAULT '1' COMMENT '表示是否严格匹配，即 location 是否和 path 完全对应上。',
  `redirect` varchar(128) DEFAULT NULL COMMENT '配置路由跳转\n',
  `wrappers` varchar(128) DEFAULT NULL COMMENT '配置路由组件的包装组件，通过包装组件可以为当前的路由组件组合进更多的功能。 比如，可以用于路由级别的权限校验\n',
  `title` varchar(64) DEFAULT NULL COMMENT '配置路由的标题。',
  `hash` tinyint(1) DEFAULT '0' COMMENT '配置是否让生成的文件包含 hash 后缀，通常用于增量发布和避免浏览器加载缓存。',
  `name` varchar(64) DEFAULT NULL COMMENT '菜单的名字',
  `icon` varchar(64) DEFAULT NULL COMMENT '配置路由的图标',
  `routes` varchar(255) DEFAULT NULL COMMENT '配置子路由，通常在需要为多个路径增加 layout 组件时使用。',
  `key` varchar(64) DEFAULT NULL COMMENT '用于标定选中的值，默认是 path',
  `target` varchar(32) DEFAULT NULL COMMENT '指定外链打开形式，同a标签',
  `hide_children_in_menu` tinyint(1) DEFAULT '0' COMMENT '在菜单中隐藏子节点',
  `hide_in_menu` tinyint(1) DEFAULT '0' COMMENT '在菜单中隐藏自己和子节点',
  `locale` varchar(64) DEFAULT NULL COMMENT '自定义菜单的国际化',
  `disabled` tinyint(1) DEFAULT NULL COMMENT 'disable 菜单选项',
  `parent_keys` varchar(64) DEFAULT NULL COMMENT '当此节点被选中的时候也会选中 parentKeys 的节点',
  `flat_menu` tinyint(1) DEFAULT '0' COMMENT '隐藏自己，并且将子节点提升到与自己平级',
  `tooltip` varchar(128) DEFAULT NULL COMMENT 'menuItem 的 tooltip 显示的路径',
  `app_type` varchar(16) DEFAULT NULL COMMENT '应用编码，标识权限所属的应用',
  `permission` varchar(32) DEFAULT NULL COMMENT '与项目提供的权限拦截匹配的权限，如果不匹配，则会被禁止访问该路由页面',
  `type` tinyint(1) DEFAULT NULL COMMENT '类型，1：菜单，2：按钮',
  `description` varchar(128) DEFAULT NULL COMMENT '资源描述',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除状态，0：正常，1：删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_user` varchar(64) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=utf8mb4 COMMENT='系统资源权限配置';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(32) DEFAULT NULL COMMENT '角色编码',
  `status` tinyint(1) DEFAULT '0' COMMENT '0：启用，1：禁用',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除状态，0：正常，1：删除',
  `app_type` varchar(16) DEFAULT NULL COMMENT '应用编码，标识权限所属的应用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='角色配置';

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3412 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限配置';

-- ----------------------------
-- Table structure for sys_user_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_account`;
CREATE TABLE `sys_user_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户ID',
  `account` varchar(64) DEFAULT NULL COMMENT '账号',
  `salt` varchar(128) DEFAULT NULL COMMENT '盐',
  `secret` varchar(128) DEFAULT NULL COMMENT '登录凭证',
  `type` tinyint(1) DEFAULT '1' COMMENT '登录类型（1：普通用户；2：会员用户）',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态（0：正常，1：冻结，2：注销）',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除状态，0：正常，1：删除',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `app_type` varchar(16) DEFAULT NULL COMMENT '应用编码，标识权限所属的应用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COMMENT='用户账号';

-- ----------------------------
-- Table structure for sys_user_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户ID',
  `nick_name` varchar(32) DEFAULT NULL COMMENT '账号',
  `sex` tinyint(1) DEFAULT '0' COMMENT '性别（0：保密）',
  `address` varchar(128) DEFAULT NULL COMMENT '通讯地址',
  `email` varchar(128) DEFAULT NULL COMMENT '通讯邮箱',
  `real_name` varchar(128) DEFAULT NULL COMMENT '真实姓名',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `description` varchar(256) DEFAULT NULL COMMENT '个人描述',
  `phone` varchar(16) DEFAULT NULL COMMENT '通讯电话',
  `avatar` varchar(256) DEFAULT NULL COMMENT '用户头像',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除状态，0：正常，1：删除',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `user_id` varchar(64) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色配置';

-- ----------------------------
-- Table structure for sys_user_tripartite_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_tripartite_account`;
CREATE TABLE `sys_user_tripartite_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户ID',
  `account` varchar(64) DEFAULT NULL COMMENT '账号',
  `third_type` varchar(16) DEFAULT NULL COMMENT '第三方类型',
  `third_id` varchar(128) DEFAULT NULL COMMENT '第三方ID',
  `bind_status` tinyint(1) DEFAULT '0' COMMENT '绑定状态，0：未绑定，1：已绑定',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除状态，0：正常，1：删除',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态（0：正常，1：冻结，2：注销）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COMMENT='用户第三方账户';

-- ----------------------------
-- Table structure for tools
-- ----------------------------
DROP TABLE IF EXISTS `tools`;
CREATE TABLE `tools` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `avatar` varchar(128) DEFAULT NULL COMMENT '图标地址',
  `cover` varchar(128) DEFAULT NULL COMMENT '封面图',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `data_type` tinyint(6) DEFAULT NULL COMMENT '数据类型',
  `list_type` tinyint(6) DEFAULT NULL COMMENT '列表类型',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(32) DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  `status` tinyint(2) DEFAULT NULL COMMENT '状态，0：正常，1：审核中，2：审核不通过',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除状态，0：正常，1：删除',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `jump_url` varchar(128) DEFAULT NULL COMMENT '内容跳转链接',
  `jump_type` int(4) DEFAULT NULL COMMENT '跳转类型',
  `sort` int(8) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Records of chat_user_friend
-- ----------------------------
BEGIN;
INSERT INTO `chat_user_friend` (`id`, `user_id`, `friend_user_id`, `friend_group_id`, `create_time`, `update_time`, `del_flag`, `disturb`, `topping`) VALUES (1, '10000', '10004', '10000-2', NULL, NULL, b'0', b'0', b'0');
INSERT INTO `chat_user_friend` (`id`, `user_id`, `friend_user_id`, `friend_group_id`, `create_time`, `update_time`, `del_flag`, `disturb`, `topping`) VALUES (2, '10000', '10005', '10000-1', NULL, NULL, b'0', b'0', b'0');
INSERT INTO `chat_user_friend` (`id`, `user_id`, `friend_user_id`, `friend_group_id`, `create_time`, `update_time`, `del_flag`, `disturb`, `topping`) VALUES (3, '10004', '10000', '10004-1', NULL, NULL, b'0', b'0', b'0');
INSERT INTO `chat_user_friend` (`id`, `user_id`, `friend_user_id`, `friend_group_id`, `create_time`, `update_time`, `del_flag`, `disturb`, `topping`) VALUES (4, '10005', '10000', '10005-1', NULL, NULL, b'0', b'0', b'0');
COMMIT;

-- ----------------------------
-- Records of chat_user_friend_group
-- ----------------------------
BEGIN;
INSERT INTO `chat_user_friend_group` (`id`, `group_id`, `group_name`, `user_id`, `status`, `disturb`, `topping`, `order`, `create_time`, `update_time`, `del_flag`) VALUES (1, '10000-1', '坦克', '10000', NULL, b'0', b'0', 0, NULL, NULL, b'0');
INSERT INTO `chat_user_friend_group` (`id`, `group_id`, `group_name`, `user_id`, `status`, `disturb`, `topping`, `order`, `create_time`, `update_time`, `del_flag`) VALUES (2, '10000-2', '法师', '10000', NULL, b'0', b'0', 1, NULL, NULL, b'0');
INSERT INTO `chat_user_friend_group` (`id`, `group_id`, `group_name`, `user_id`, `status`, `disturb`, `topping`, `order`, `create_time`, `update_time`, `del_flag`) VALUES (3, '10004-1', '坦克', '10004', NULL, b'0', b'0', 1, NULL, NULL, b'0');
INSERT INTO `chat_user_friend_group` (`id`, `group_id`, `group_name`, `user_id`, `status`, `disturb`, `topping`, `order`, `create_time`, `update_time`, `del_flag`) VALUES (4, '10005-1', '坦克', '10005', NULL, b'0', b'0', 0, NULL, NULL, b'0');
COMMIT;

-- ----------------------------
-- Records of chat_user_info
-- ----------------------------
BEGIN;
INSERT INTO `chat_user_info` (`id`, `user_id`, `user_name`, `status`, `sign`, `avatar`, `create_time`, `update_time`, `del_flag`) VALUES (1, '10000', '亚瑟', 'online', NULL, NULL, NULL, NULL, b'0');
INSERT INTO `chat_user_info` (`id`, `user_id`, `user_name`, `status`, `sign`, `avatar`, `create_time`, `update_time`, `del_flag`) VALUES (2, '10001', '甄姬', 'offline', NULL, NULL, NULL, NULL, b'0');
INSERT INTO `chat_user_info` (`id`, `user_id`, `user_name`, `status`, `sign`, `avatar`, `create_time`, `update_time`, `del_flag`) VALUES (3, '10002', '芈月', 'online', NULL, NULL, NULL, NULL, b'0');
INSERT INTO `chat_user_info` (`id`, `user_id`, `user_name`, `status`, `sign`, `avatar`, `create_time`, `update_time`, `del_flag`) VALUES (4, '10004', '貂蝉', 'online', NULL, NULL, NULL, NULL, b'0');
INSERT INTO `chat_user_info` (`id`, `user_id`, `user_name`, `status`, `sign`, `avatar`, `create_time`, `update_time`, `del_flag`) VALUES (5, '10005', '程咬金', 'offline', NULL, NULL, NULL, NULL, b'0');
COMMIT;
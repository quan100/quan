/**
 * 约定统一的消息处理器格式
 * <p>
 * {@code MessageHandler} 为处理器标识
 * <p>
 * 固定的命名为 消息类型 + 处理器标识
 * <p>
 * 示例：FriendMessageHandler  (friend为消息类型，转换为首字母大写、驼峰)
 */
package cn.javaquan.tools.chat.core.handler;
package cn.javaquan.chat.bff.controller;

import cn.javaquan.chat.bff.service.ChatServerService;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 聊天服务接口
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/open/chat/server/")
public class ChatServerController {

    private final ChatServerService chatServerService;

    /**
     * 获取好友信息列表
     *
     * @param userId
     * @return
     */
    @GetMapping("getList")
    public Result getList(@RequestParam String userId) {
        return Result.success(chatServerService.getList(userId));
    }


}

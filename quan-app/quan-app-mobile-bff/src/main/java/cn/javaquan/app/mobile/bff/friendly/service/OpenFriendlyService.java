package cn.javaquan.app.mobile.bff.friendly.service;

import cn.javaquan.app.common.constant.MessageTemplateEnum;
import cn.javaquan.app.common.convert.PageResultAssembler;
import cn.javaquan.app.common.module.friendly.*;
import cn.javaquan.app.common.util.RunUtil;
import cn.javaquan.app.common.util.TemplateUtils;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.app.common.util.dictionary.DictionaryUtil;
import cn.javaquan.app.mobile.bff.friendly.convert.OpenFriendlyLinkAssembler;
import cn.javaquan.app.mobile.bff.friendly.feign.OpenFriendlyLinkServiceFeign;
import cn.javaquan.common.base.constant.TopicEnum;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.tools.dingtalk.api.ding.entity.DingRobotTextMsgCommand;
import cn.javaquan.tools.jasypt.EncryptUtil;
import cn.javaquan.tools.jms.JmsUtil;
import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author wangquan
 * @version 1.0.0
 * @date 2020-02-12 19:50:38
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/open/friendly/link/")
public class OpenFriendlyService {

    @Value("${quan.tools.notify.webhook:}")
    private String to;
    private final OpenFriendlyLinkServiceFeign friendlyLinkServiceFeign;
    private final JmsUtil jmsUtil;

    /**
     * 查询列表
     *
     * @param basePage
     * @return
     */
    public Result<PageResult<FriendlyLinkVO>> page(BasePage basePage) {
        FriendlyLinkQuery query = new FriendlyLinkQuery();
        query.setStatus(0);
        query.setPageNum(basePage.getPageNum());
        query.setPageSize(basePage.getPageSize());
        Result<PageResult<FriendlyLinkDTO>> result = friendlyLinkServiceFeign.page(query);
        return RunUtil.doRun(result, () -> {
            PageResult<FriendlyLinkVO> page = PageResultAssembler.INSTANCE.toPageResult(result.getData());
            page.setRecords(OpenFriendlyLinkAssembler.INSTANCE.toFriendlyLinkVoList(result.getData().getRecords()));
            return Result.success(page);
        });
    }

    /**
     * 申请友链
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(FriendlyLinkApplyCommand cmd) {
        FriendlyLinkAddCommand addCommand = OpenFriendlyLinkAssembler.INSTANCE.toFriendlyLinkAddCommand(cmd);
        return friendlyLinkServiceFeign.save(addCommand);
    }

    /**
     * 申请友链
     *
     * @param cmd
     */
    public Result<Boolean> apply(FriendlyLinkApplyCommand cmd) {
        Result<Boolean> result = save(cmd);
        RunUtil.doRun(result.isData() && result.getData(), false, () -> {
            // 发送钉钉通知
            String friendlyApplyJson = DictionaryUtil.value(MessageTemplateEnum.FRIENDLY_APPLY.name());
            if (Validate.isBlank(friendlyApplyJson)) {
                log.warn("获取友链申请消息通知模版失败 [FRIENDLY_APPLY] 未配置！");
                return;
            }
            DingRobotTextMsgCommand msgCommand = JSON.parseObject(friendlyApplyJson, DingRobotTextMsgCommand.class);
            if (Validate.isBlank(msgCommand.getTo())) {
                log.warn("获取钉钉通知地址失败，请检查 [FRIENDLY_APPLY] 模版中 [to] 参数配置！");
                return;
            }
            String content = TemplateUtils.getText(msgCommand.getContent(), cmd);
            msgCommand.setContent(content);
            msgCommand.setTo(EncryptUtil.decrypt(msgCommand.getTo()));
            List<String> atMobiles = msgCommand.getAtMobiles();
            if (Validate.isNotEmpty(atMobiles)) {
                atMobiles.forEach(EncryptUtil::decrypt);
            }
            jmsUtil.send(TopicEnum.DING_MSG_TOPIC, msgCommand);
        });
        return result;
    }

}

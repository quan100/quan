package cn.javaquan.app.mobile.bff.friendly.service;

import cn.javaquan.app.common.constant.MessageTemplateEnum;
import cn.javaquan.app.common.convert.PageResultAssembler;
import cn.javaquan.app.common.module.friendly.FriendlyLinkAddCommand;
import cn.javaquan.app.common.module.friendly.FriendlyLinkApplyCommand;
import cn.javaquan.app.common.module.friendly.FriendlyLinkDTO;
import cn.javaquan.app.common.module.friendly.FriendlyLinkQuery;
import cn.javaquan.app.common.module.friendly.FriendlyLinkVO;
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
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 友链接口业务实现.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class OpenFriendlyService {

    @Value("${quan.tools.notify.webhook:}")
    private String to;

    private final OpenFriendlyLinkServiceFeign friendlyLinkServiceFeign;

    private final JmsUtil jmsUtil;

    /**
     * 查询列表.
     * @param basePage 分页查询参数
     * @return 查询结果
     */
    public Result<PageResult<FriendlyLinkVO>> page(BasePage basePage) {
        FriendlyLinkQuery query = new FriendlyLinkQuery();
        query.setStatus(0);
        query.setPageNum(basePage.getPageNum());
        query.setPageSize(basePage.getPageSize());
        Result<PageResult<FriendlyLinkDTO>> result = friendlyLinkServiceFeign.page(query);
        return RunUtil.doRun(result, () -> {
            PageResult<FriendlyLinkVO> page = PageResultAssembler.INSTANCE.toIgnoreDataPageResult(result.getData());
            page.setRecords(OpenFriendlyLinkAssembler.INSTANCE.toFriendlyLinkVoList(result.getData().getRecords()));
            return Result.success(page);
        });
    }

    /**
     * 申请友链.
     * @param cmd 申请友链参数
     * @return 申请操作是否成功
     */
    public Result<Boolean> save(FriendlyLinkApplyCommand cmd) {
        FriendlyLinkAddCommand addCommand = OpenFriendlyLinkAssembler.INSTANCE.toFriendlyLinkAddCommand(cmd);
        return friendlyLinkServiceFeign.save(addCommand);
    }

    /**
     * 申请友链.
     * @param cmd 申请友链参数
     * @return 申请操作是否成功
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
                msgCommand.setAtMobiles(atMobiles.stream().map(EncryptUtil::decrypt).collect(Collectors.toList()));
            }
            jmsUtil.send(TopicEnum.DING_MSG_TOPIC, msgCommand);
        });
        return result;
    }

}

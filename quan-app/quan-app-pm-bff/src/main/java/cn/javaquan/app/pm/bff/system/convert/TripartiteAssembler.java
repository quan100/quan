package cn.javaquan.app.pm.bff.system.convert;

import com.aliyun.dingtalkcontact_1_0.models.GetUserResponseBody;
import cn.javaquan.app.common.module.auth.TripartiteBoundEvent;
import cn.javaquan.app.common.module.auth.TripartiteLoginEvent;
import cn.javaquan.app.common.constant.ThirdTypeEnum;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountAddCommand;
import cn.javaquan.app.common.util.LocalDateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 第三方账号数据转换器.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Mapper(imports = { ThirdTypeEnum.class, LocalDateUtils.class })
public interface TripartiteAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    TripartiteAssembler INSTANCE = Mappers.getMapper(TripartiteAssembler.class);

    /**
     * 钉钉用户信息转换为绑定事件参数.
     * @param userResponseBody 钉钉用户信息
     * @return 绑定事件
     */
    @Mapping(target = "thirdId", source = "openId")
    @Mapping(target = "account", source = "mobile")
    @Mapping(target = "thirdType", expression = "java(ThirdTypeEnum.DINGTALK.name())")
    @Mapping(target = "bindStatus", constant = "0")
    @Mapping(target = "status", constant = "3")
    @Mapping(target = "createUser", source = "nick")
    @Mapping(target = "createTime", expression = "java(LocalDateUtils.now())")
    TripartiteBoundEvent toDingTalkBoundEvent(GetUserResponseBody userResponseBody);

    /**
     * 绑定事件参数转换为第三方账号添加指令参数.
     * @param event 绑定事件参数
     * @return 第三方账号添加指令参数
     */
    SysUserTripartiteAccountAddCommand toTripartiteAccountAddCommand(TripartiteBoundEvent event);

    /**
     * 根据第三方账号类型、第三方账号ID构建登录事件参数.
     * @param thirdType 第三方账号类型
     * @param thirdId 第三方账号ID
     * @return 登录事件参数
     */
    TripartiteLoginEvent build(String thirdType, String thirdId);

}

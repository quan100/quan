package com.quan.app.pm.bff.system.convert;

import com.aliyun.dingtalkcontact_1_0.models.GetUserResponseBody;
import com.quan.app.common.module.auth.TripartiteBoundEvent;
import com.quan.app.common.module.auth.TripartiteLoginEvent;
import com.quan.app.common.constant.ThirdTypeEnum;
import com.quan.app.common.module.system.SysUserTripartiteAccountAddCommand;
import com.quan.app.common.util.LocalDateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author wangquan
 */
@Mapper(imports = {
        ThirdTypeEnum.class,
        LocalDateUtils.class
})
public interface TripartiteAssembler {

    TripartiteAssembler INSTANCE = Mappers.getMapper(TripartiteAssembler.class);

    @Mapping(target = "thirdId", source = "openId")
    @Mapping(target = "account", source = "mobile")
    @Mapping(target = "thirdType", expression = "java(ThirdTypeEnum.DINGTALK.name())")
    @Mapping(target = "bindStatus", constant = "0")
    @Mapping(target = "status", constant = "3")
    @Mapping(target = "createUser", source = "nick")
    @Mapping(target = "createTime", expression = "java(LocalDateUtils.now())")
    TripartiteBoundEvent toDingTalkBoundEvent(GetUserResponseBody userResponseBody);

    SysUserTripartiteAccountAddCommand toTripartiteAccountAddCommand(TripartiteBoundEvent event);


    TripartiteLoginEvent build(String thirdType, String thirdId);
}

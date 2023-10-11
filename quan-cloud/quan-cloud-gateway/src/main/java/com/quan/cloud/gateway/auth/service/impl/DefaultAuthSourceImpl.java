package com.quan.cloud.gateway.auth.service.impl;

import com.quan.common.base.message.Result;
import com.quan.cloud.gateway.auth.constant.PermEnum;
import com.quan.cloud.gateway.auth.service.AuthSourceFeign;
import com.quan.cloud.gateway.auth.service.IAuthSource;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * 默认的权限加载
 * 若未开启加载配置，则不生效
 *
 * @author wangquan
 */
@RequiredArgsConstructor
public class DefaultAuthSourceImpl implements IAuthSource {

    private final AuthSourceFeign authSourceFeign;

    @Override
    public List<String> getAuth() {
        Result<List<String>> result = null;
        try {
            result = authSourceFeign.getAuth(PermEnum.USER.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        if (result.isData()) {
            return result.getData();
        }
        return Collections.emptyList();
    }
}

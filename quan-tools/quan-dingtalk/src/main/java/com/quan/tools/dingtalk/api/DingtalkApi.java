package com.quan.tools.dingtalk.api;

import com.aliyun.dingtalkcontact_1_0.models.GetUserHeaders;
import com.aliyun.dingtalkcontact_1_0.models.GetUserResponseBody;
import com.aliyun.dingtalkoauth2_1_0.models.GetUserTokenRequest;
import com.aliyun.dingtalkoauth2_1_0.models.GetUserTokenResponse;
import com.aliyun.dingtalkoauth2_1_0.models.GetUserTokenResponseBody;
import com.aliyun.tea.utils.StringUtils;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.quan.tools.redis.service.CacheUtil;

/**
 * 钉钉API
 *
 * @author javaquan
 * @since 2.2.0
 */
public class DingtalkApi {

    private final DingtalkProperties properties;

    public DingtalkApi(DingtalkProperties properties) {
        this.properties = properties;
    }

    public static com.aliyun.dingtalkoauth2_1_0.Client authClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkoauth2_1_0.Client(config);
    }

    /**
     * 获取用户token
     *
     * @param authCode
     * @return
     * @throws Exception
     */
    //接口地址：注意/auth与钉钉登录与分享的回调域名地址一致
    public GetUserResponseBody getAccessToken(String authCode, String state) throws Exception {
        String accessTokenKey = new StringBuffer().append("dingtalk:auth:access_token:").append(authCode).toString();
        String refreshTokenKey = new StringBuffer().append("dingtalk:auth:refresh_token:").append(authCode).toString();

        String accessToken = CacheUtil.get(accessTokenKey);
        if (StringUtils.isEmpty(accessToken)) {
            String refreshToken = CacheUtil.get(refreshTokenKey);
            GetUserTokenResponseBody body;

            if (StringUtils.isEmpty(refreshToken)) {
                GetUserTokenRequest request = new GetUserTokenRequest()
                        .setCode(authCode)
                        .setGrantType("authorization_code");

                body = getToken(request);
                refreshToken = body.getRefreshToken();
                if ("REMEMBER".equals(state)) {
                    CacheUtil.set(refreshTokenKey, refreshToken, properties.determineDefaultExpireIn());
                }
            } else {
                GetUserTokenRequest request = new GetUserTokenRequest()
                        .setRefreshToken(refreshToken)
                        .setGrantType("refresh_token");
                body = getToken(request);
            }

            //获取用户个人token
            accessToken = body.getAccessToken();
            CacheUtil.set(accessTokenKey, accessToken, body.getExpireIn());
        }
        return getUserinfo(accessToken);
    }

    public GetUserTokenResponseBody getToken(GetUserTokenRequest request) throws Exception {
        com.aliyun.dingtalkoauth2_1_0.Client client = authClient();
        //应用基础信息-应用信息的AppKey,请务必替换为开发的应用AppKey
        request.setClientId(properties.determineDefaultAppKey())
                //应用基础信息-应用信息的AppSecret，,请务必替换为开发的应用AppSecret
                .setClientSecret(properties.determineDefaultAppSecret());
        GetUserTokenResponse getUserTokenResponse = client.getUserToken(request);
        return getUserTokenResponse.getBody();
    }

    public static com.aliyun.dingtalkcontact_1_0.Client contactClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkcontact_1_0.Client(config);
    }

    /**
     * 获取用户个人信息
     *
     * @param accessToken
     * @return
     * @throws Exception
     */
    public GetUserResponseBody getUserinfo(String accessToken) throws Exception {
        com.aliyun.dingtalkcontact_1_0.Client client = contactClient();
        GetUserHeaders getUserHeaders = new GetUserHeaders();
        getUserHeaders.xAcsDingtalkAccessToken = accessToken;
        //获取用户个人信息，如需获取当前授权人的信息，unionId参数必须传me
        GetUserResponseBody userResponseBody = client.getUserWithOptions("me", getUserHeaders, new RuntimeOptions()).getBody();
        return userResponseBody;
    }

}

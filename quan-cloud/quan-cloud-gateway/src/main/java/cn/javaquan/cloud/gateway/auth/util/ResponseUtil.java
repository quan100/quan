package cn.javaquan.cloud.gateway.auth.util;

import com.alibaba.fastjson.JSONObject;
import cn.javaquan.common.base.message.Result;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

public class ResponseUtil {

    /**
     * 输出信息
     *
     * @param response
     * @param result
     * @return
     */
    public static Mono<Void> writeResponse(ServerHttpResponse response, Result result) {
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONBytes(result));
        return response.writeWith(Mono.just(dataBuffer));
    }
}

package com.quan.cloud.gateway.auth.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.quan.common.base.message.Result;
import org.apache.commons.lang.StringUtils;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * API响应参数处理器
 *
 * @author wangquan
 */
@Component
public class ResponseFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(exchange.getResponse()) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (shouldFilter(getStatusCode()) && body instanceof Flux) {
                    CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, getHeaders());
                    return BodyInserters.fromPublisher(ClientResponse
                                    .create(getStatusCode()).body(Flux.from(body)).build()
                                    .bodyToMono(String.class)
                                    .flatMap(bodyStr -> Mono.just(writeResponse(bodyStr))), String.class)
                            .insert(outputMessage, new BodyInserterContext())
                            .then(Mono.defer(() -> getDelegate().writeWith(outputMessage.getBody())));
                }
                return super.writeWith(body);
            }
        };
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }

    @Override
    public int getOrder() {
        return AuthFilter.AUTH_FILTER_ORDER + 1;
    }

    private String writeResponse(String body) {
        if (StringUtils.isBlank(body)) {
            return body;
        }
        char prefix = body.charAt(0);
        if (prefix == '{') {
            JSONObject jsonObject = JSON.parseObject(body);
            if (jsonObject.containsKey(Result.MESSAGE_TYPE)) {
                return body;
            }
            return Result.success(jsonObject).toJSONString();
        }
        if (prefix == '[') {
            return Result.success(JSON.parseArray(body)).toJSONString();
        }
        return Result.success(body).toJSONString();
    }

    private boolean shouldFilter(HttpStatus status) {
        return HttpStatus.OK.equals(status);
    }
}

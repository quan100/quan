package cn.javaquan.app.mobile.bff.news.feign;

import cn.javaquan.app.mobile.bff.news.feign.vo.QQ;
import com.alibaba.fastjson.JSONObject;
import cn.javaquan.app.common.util.Validate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

/**
 * QQ热搜.
 *
 * @author wangquan
 * @since 1.0.0
 */
@FeignClient(name = "qq-hot-search", url = "https://r.inews.qq.com")
public interface QQHotSearchFeign {

    // /**
    // * 获取热搜内容
    // *
    // * @return
    // */
    // @GetMapping("/trpc.qqnews_web.kv_srv.kv_srv_http_proxy/list")
    // Message<QQ> getNews(@SpringQueryMap JSONObject jsonObject);

    /**
     * 获取热点榜.
     * @param jsonObject 请求参数
     * @return 腾讯新闻
     */
    @GetMapping("/gw/event/hot_ranking_list")
    QQ getNews(@SpringQueryMap JSONObject jsonObject);

    /**
     * 参数构建.
     * @param offset 偏移量
     * @param pageSize 每页数据量
     * @param idsHash ids_hash
     * @return 腾讯新闻接口查询参数
     */
    default JSONObject buildParams(Integer offset, Integer pageSize, String idsHash) {
        JSONObject params = new JSONObject();
        if (Validate.isNotBlank(idsHash)) {
            params.put("ids_hash", idsHash);
        }
        params.put("offset", offset);
        params.put("page_size", pageSize);
        return params;
    }

    /**
     * 参数构建.
     * @return 参数
     */
    default JSONObject buildParams() {
        JSONObject params = new JSONObject();
        params.put("sub_srv_id", "24hours");
        params.put("srv_id", "pc");
        params.put("offset", 0);
        params.put("limit", 50);
        params.put("strategy", 1);

        JSONObject ext = new JSONObject();
        ext.put("pool", Arrays.asList("top"));
        ext.put("is_filter", 7);
        ext.put("check_type", true);
        params.put("ext", ext);
        return params;
    }

}

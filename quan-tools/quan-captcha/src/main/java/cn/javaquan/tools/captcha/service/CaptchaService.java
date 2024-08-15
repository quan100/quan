package cn.javaquan.tools.captcha.service;

import com.github.bingoohuang.patchca.background.MyCustomBackgroundFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.DiffuseRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.DoubleRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.MarbleRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.WobbleRippleFilterFactory;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import cn.javaquan.tools.redis.service.IRedisService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 验证码功能服务.
 *
 * @author javaquan
 * @since 2.2.0
 */
public class CaptchaService {

    private final IRedisService redisService;

    private final CaptchaProperties properties;

    private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();

    private static Random random = new Random();

    /**
     * 初始化验证码图片配置.
     */
    void initializers() {
        cs.setColorFactory((x) -> {
            int[] c = new int[3];
            int i = random.nextInt(c.length);
            for (int fi = 0; fi < c.length; fi++) {
                if (fi == i) {
                    c[fi] = random.nextInt(71);
                }
                else {
                    c[fi] = random.nextInt(256);
                }
            }
            return new Color(c[0], c[1], c[2]);
        });
        RandomWordFactory wf = new RandomWordFactory();
        wf.setCharacters(properties.determineDefaultCharacters());
        wf.setMaxLength(properties.determineDefaultMaxLength());
        wf.setMinLength(properties.determineDefaultMinLength());
        cs.setWordFactory(wf);
        cs.setBackgroundFactory(new MyCustomBackgroundFactory());
    }

    /**
     * 构造验证码服务类.
     * @param redisService redis服务
     * @param properties 验证码配置信息
     */
    public CaptchaService(IRedisService redisService, CaptchaProperties properties) {
        this.redisService = redisService;
        this.properties = properties;

        initializers();
    }

    /**
     * 校验验证码.
     * @param verifycode 验证码
     * @param uuid uuid
     * @return 校验是否通过
     */
    public boolean verifyImageCode(String verifycode, String uuid) {
        String code = redisService.get(getKey(uuid));
        return StringUtils.hasText(code) && code.equalsIgnoreCase(verifycode);
    }

    /**
     * 随机生成验证码.
     * @param uuid uuid
     * @param os 输出流
     * @throws IOException 异常
     */
    public void createImage(String uuid, OutputStream os) throws IOException {
        Assert.hasLength(uuid, "uuid不可为空");
        // 生成验证码
        switch (random.nextInt(5)) {
            case 0:
                cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
                break;
            case 1:
                cs.setFilterFactory(new MarbleRippleFilterFactory());
                break;
            case 2:
                cs.setFilterFactory(new DoubleRippleFilterFactory());
                break;
            case 3:
                cs.setFilterFactory(new WobbleRippleFilterFactory());
                break;
            case 4:
                cs.setFilterFactory(new DiffuseRippleFilterFactory());
                break;
        }

        String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", os);
        // 存入用户对应的验证码
        redisService.set(getKey(uuid), token, properties.determineDefaultInvalidTime());
    }

    /**
     * 生成验证码缓存键.
     * @param uuid uuid
     * @return 缓存键
     */
    public String getKey(String uuid) {
        StringBuffer sb = new StringBuffer("quan:admin:user:captcha:");
        sb.append(uuid);
        return sb.toString();
    }

    /**
     * 生成邮箱验证码缓存键.
     * @param uuid uuid
     * @param email 邮箱
     * @return 缓存键
     */
    public String getKey(String uuid, String email) {
        return getKey(DigestUtils.md5Hex(uuid + email));
    }

    /**
     * 创建邮箱验证码.
     * @param uuid uuid
     * @param email 邮箱
     * @return 验证码
     */
    public String createEmailCode(String uuid, String email) {
        String code = RandomUtil.generate(properties.determineDefaultCharacters(),
                properties.determineDefaultMaxLength());
        redisService.set(getKey(uuid, email), DigestUtils.md5Hex(code), properties.determineDefaultInvalidTime());
        return code;
    }

    /**
     * 校验邮箱验证码.
     * @param verifycode 验证码
     * @param uuid uuid
     * @param email 邮箱
     * @return 校验是否通过
     */
    public boolean verifyEmailCode(String verifycode, String uuid, String email) {
        String code = redisService.get(getKey(uuid, email));
        return StringUtils.hasText(code) && !code.equalsIgnoreCase(verifycode);
    }

}

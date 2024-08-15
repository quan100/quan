package cn.javaquan.code.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wangquan
 * @since 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "generator")
public class GeneratorProperties {

    /**
     * 代码生成器，配置信息 group id.
     */
    private String mainPath;

    /**
     * 包名.
     */
    private String packageName;

    /**
     * 模块名.
     */
    private String moduleName;

    /**
     * artifact id.
     */
    private String artifactIdPath;

    /**
     * 领域模块.
     */
    private String prefixCore;

    private String prefixService;

    private String prefixFeignUrl;

    /**
     * 作者.
     */
    private String author;

    /**
     * Email.
     */
    private String email;

    /**
     * 版本.
     */
    private String version;

    /**
     * 表前缀(类名不会包含表前缀).
     */
    private String tablePrefix;

    /**
     * 模板类型.
     */
    private String templateType;

    /**
     * 类型转换，配置信息.
     */
    private String tinyintType;

    private String smallintType;

    private String mediumintType;

    private String intType;

    private String integerType;

    private String bigintType;

    private String floatType;

    private String doubleType;

    private String decimalType;

    private String bitType;

    private String charType;

    private String varcharType;

    private String tinytextType;

    private String textType;

    private String mediumtextType;

    private String longtextType;

    private String dateType;

    private String datetimeType;

    private String timestampType;

    private String jsonType;

    ///// ** ./ 类型转换，配置信息 ////// */

}

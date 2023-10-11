package com.quan.code.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wangquan
 * @date 2020/9/4 14:19
 */
@Component
@ConfigurationProperties(
        prefix = "generator"
)
public class GeneratorProperties {

    public static final String MYBATIS_PREFIX = "generator";


    // 代码生成器，配置信息
    // group id
    private String mainPath;

    // 包名
    private String packageName;
    // 模块名
    private String moduleName;
    // artifact id
    private String artifactIdPath;
    // 领域模块
    private String prefixCore;
    private String prefixService;

    private String prefixFeignUrl;


    // 作者
    private String author;
    // Email
    private String email;
    // 版本
    private String version;


    // 表前缀(类名不会包含表前缀)
    private String tablePrefix;
    // 模板类型
    private String templateType;


    ////// 类型转换，配置信息 //////
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

    ////// ./ 类型转换，配置信息 //////

    public String getMainPath() {
        return mainPath;
    }

    public GeneratorProperties setMainPath(String mainPath) {
        this.mainPath = mainPath;
        return this;
    }

    public String getPackageName() {
        return packageName;
    }

    public GeneratorProperties setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public String getModuleName() {
        return moduleName;
    }

    public GeneratorProperties setModuleName(String moduleName) {
        this.moduleName = moduleName;
        return this;
    }

    public String getArtifactIdPath() {
        return artifactIdPath;
    }

    public GeneratorProperties setArtifactIdPath(String artifactIdPath) {
        this.artifactIdPath = artifactIdPath;
        return this;
    }

    public String getPrefixCore() {
        return prefixCore;
    }

    public GeneratorProperties setPrefixCore(String prefixCore) {
        this.prefixCore = prefixCore;
        return this;
    }

    public String getPrefixService() {
        return prefixService;
    }

    public GeneratorProperties setPrefixService(String prefixService) {
        this.prefixService = prefixService;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public GeneratorProperties setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public GeneratorProperties setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public GeneratorProperties setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public GeneratorProperties setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
        return this;
    }

    public String getTemplateType() {
        return templateType;
    }

    public GeneratorProperties setTemplateType(String templateType) {
        this.templateType = templateType;
        return this;
    }

    public String getTinyintType() {
        return tinyintType;
    }

    public GeneratorProperties setTinyintType(String tinyintType) {
        this.tinyintType = tinyintType;
        return this;
    }

    public String getSmallintType() {
        return smallintType;
    }

    public GeneratorProperties setSmallintType(String smallintType) {
        this.smallintType = smallintType;
        return this;
    }

    public String getMediumintType() {
        return mediumintType;
    }

    public GeneratorProperties setMediumintType(String mediumintType) {
        this.mediumintType = mediumintType;
        return this;
    }

    public String getIntType() {
        return intType;
    }

    public GeneratorProperties setIntType(String intType) {
        this.intType = intType;
        return this;
    }

    public String getIntegerType() {
        return integerType;
    }

    public GeneratorProperties setIntegerType(String integerType) {
        this.integerType = integerType;
        return this;
    }

    public String getBigintType() {
        return bigintType;
    }

    public GeneratorProperties setBigintType(String bigintType) {
        this.bigintType = bigintType;
        return this;
    }

    public String getFloatType() {
        return floatType;
    }

    public GeneratorProperties setFloatType(String floatType) {
        this.floatType = floatType;
        return this;
    }

    public String getDoubleType() {
        return doubleType;
    }

    public GeneratorProperties setDoubleType(String doubleType) {
        this.doubleType = doubleType;
        return this;
    }

    public String getDecimalType() {
        return decimalType;
    }

    public GeneratorProperties setDecimalType(String decimalType) {
        this.decimalType = decimalType;
        return this;
    }

    public String getBitType() {
        return bitType;
    }

    public GeneratorProperties setBitType(String bitType) {
        this.bitType = bitType;
        return this;
    }

    public String getCharType() {
        return charType;
    }

    public GeneratorProperties setCharType(String charType) {
        this.charType = charType;
        return this;
    }

    public String getVarcharType() {
        return varcharType;
    }

    public GeneratorProperties setVarcharType(String varcharType) {
        this.varcharType = varcharType;
        return this;
    }

    public String getTinytextType() {
        return tinytextType;
    }

    public GeneratorProperties setTinytextType(String tinytextType) {
        this.tinytextType = tinytextType;
        return this;
    }

    public String getTextType() {
        return textType;
    }

    public GeneratorProperties setTextType(String textType) {
        this.textType = textType;
        return this;
    }

    public String getMediumtextType() {
        return mediumtextType;
    }

    public GeneratorProperties setMediumtextType(String mediumtextType) {
        this.mediumtextType = mediumtextType;
        return this;
    }

    public String getLongtextType() {
        return longtextType;
    }

    public GeneratorProperties setLongtextType(String longtextType) {
        this.longtextType = longtextType;
        return this;
    }

    public String getDateType() {
        return dateType;
    }

    public GeneratorProperties setDateType(String dateType) {
        this.dateType = dateType;
        return this;
    }

    public String getDatetimeType() {
        return datetimeType;
    }

    public GeneratorProperties setDatetimeType(String datetimeType) {
        this.datetimeType = datetimeType;
        return this;
    }

    public String getTimestampType() {
        return timestampType;
    }

    public GeneratorProperties setTimestampType(String timestampType) {
        this.timestampType = timestampType;
        return this;
    }

    public String getJsonType() {
        return jsonType;
    }

    public GeneratorProperties setJsonType(String jsonType) {
        this.jsonType = jsonType;
        return this;
    }

    public String getPrefixFeignUrl() {
        return prefixFeignUrl;
    }

    public GeneratorProperties setPrefixFeignUrl(String prefixFeignUrl) {
        this.prefixFeignUrl = prefixFeignUrl;
        return this;
    }
}

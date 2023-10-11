package com.quan.code.utils;

import com.alibaba.fastjson.JSON;
import com.quan.code.autoconfigure.GeneratorProperties;
import com.quan.code.entity.ColumnEntity;
import com.quan.code.entity.TableEntity;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器   工具类
 *
 * @author chenshun
 * @author wangquan
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午11:40:24
 */
@Component
public class GenUtils {

    @Resource
    private GeneratorProperties config;
    @Autowired
    private Environment environment;
    @Autowired
    ResourceLoader resourceLoader;

    /**
     * 获取模版
     *
     * @param templateType 模版类型，多个用逗号{@code ,}隔开
     * @return
     */
    public static List<String> getTemplates(String templateType) {
        List<String> templateTypes = Arrays.asList(templateType.split(","));
        List<String> templates = templateTypes.stream().flatMap(type -> {
            List<String> templateList = getFileList("classpath:template/" + type);
            return templateList.stream();
        }).collect(Collectors.toList());
        return templates;
    }

    public static List<String> getFileList(String directoryPath) {
        List<String> fileInfos = new ArrayList<>(16);
        return getFileList(directoryPath, fileInfos);
    }

    /**
     * 获取文件列表
     *
     * @param directoryPath 目录路径
     * @return
     */
    public static List<String> getFileList(String directoryPath, List<String> fileInfos) {
        File directoryFile;
        try {
            directoryFile = ResourceUtils.getFile(directoryPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        File[] listFiles = directoryFile.listFiles();
        if (listFiles == null) {
            return Collections.emptyList();
        }
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isDirectory()) {
                getFileList(listFiles[i].getAbsolutePath(), fileInfos);
            } else {
                File file = listFiles[i];
                String absolutePath = file.getAbsolutePath();
                String path = absolutePath.substring(absolutePath.indexOf("template"));
                fileInfos.add(path);
            }
        }
        return fileInfos;
    }

    /**
     * 生成代码
     *
     * @param table
     * @param columns
     * @param zip
     */
    public void generatorCode(Map<String, String> table, List<Map<String, String>> columns, ZipOutputStream zip) {
        TableEntity tableEntity = getTableInfo(table, columns);
        VelocityContext context = initVelocityContext(tableEntity);
        initVelocity();

        //获取模板列表
        List<String> templates = getTemplates(config.getTemplateType());

        templates.stream().forEach(template -> {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);

            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName(), config.getPackageName(), config.getModuleName())));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new RRException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
            }
        });
    }

    /**
     * 获取表信息
     *
     * @param table   mysql 数据库表信息
     * @param columns mysql 数据库表列信息
     * @return
     */
    private TableEntity getTableInfo(Map<String, String> table, List<Map<String, String>> columns) {
        String tableName = table.get("tableName");
        //表信息
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(table.get("tableName"));
        tableEntity.setComments(table.get("tableComment"));
        //表名转换成Java类名
        String className = tableToJava(tableEntity.getTableName(), config.getTablePrefix());
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));

        // 模块路径
        if (tableName.indexOf("_") > 0) {
            String mapping = tableName.substring(tableName.indexOf("_") + 1).toLowerCase();
            tableEntity.setMapping(mapping);

            // 是否设置模块名称，没有则默认取表前缀
            config.setModuleName(tableName.substring(0, tableName.indexOf("_")));
        } else {
            config.setModuleName(tableName);
        }

        //列信息
        List<ColumnEntity> columsList = new ArrayList<>();
        for (Map<String, String> column : columns) {
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(column.get("columnName"));
            columnEntity.setDataType(column.get("dataType"));
            columnEntity.setComments(column.get("columnComment"));
            columnEntity.setExtra(column.get("extra"));

            //列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            //列的数据类型，转换成Java类型
            StringBuffer dataType = new StringBuffer("generator.").append(columnEntity.getDataType()).append("Type");
            String attrType = environment.getProperty(dataType.toString(), "unknowType");

            columnEntity.setAttrType(attrType);
            if (attrType.equals("BigDecimal")) {
                tableEntity.setHasBigDecimal(true);
            } else if (attrType.equals("Date")) {
                tableEntity.setHasDate(true);
            }
            //是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null) {
                tableEntity.setPk(columnEntity);
            }

            columsList.add(columnEntity);
        }
        tableEntity.setColumns(columsList);

        //没主键，则第一个字段为主键
        if (tableEntity.getPk() == null) {
            tableEntity.setPk(tableEntity.getColumns().get(0));
        }
        return tableEntity;
    }

    /**
     * 数据分析，初始化总配置信息
     *
     * @param tableEntity
     * @return
     */
    private VelocityContext initVelocityContext(TableEntity tableEntity) {
        String mainPath = config.getMainPath();
        mainPath = StringUtils.isBlank(mainPath) ? "com.quan" : mainPath;
        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("table", tableEntity);
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments());
        map.put("pk", tableEntity.getPk());

        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());

        map.put("classNameEntity", tableEntity.getClassName() + "Entity");
        map.put("classnameentity", tableEntity.getClassname() + "Entity");

        map.put("pathName", tableEntity.getClassname().toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("hasBigDecimal", tableEntity.getHasBigDecimal());
        map.put("hasDate", tableEntity.getHasDate());
        map.put("mainPath", mainPath);
        map.put("package", config.getPackageName());
        map.put("moduleName", config.getModuleName());

        // 注释信息
        map.put("author", config.getAuthor());
        map.put("email", config.getEmail());
        map.put("version", config.getVersion());
        map.put("datetime", LocalDateUtils.getCurDateTime(LocalDateUtils.STANDARD_DATETIME_FORMAT));

        map.put("artifactIdPath", config.getArtifactIdPath());
        map.put("prefixCore", config.getPrefixCore());
        map.put("prefixService", config.getPrefixService());

        map.put("prefixFeignUrl", config.getPrefixFeignUrl());

        // 获取controller路径
        String controllerAddress = tableEntity.getTableName();
        controllerAddress = controllerAddress.replace(config.getModuleName(), "");
        controllerAddress = controllerAddress.startsWith("_") ? controllerAddress.substring(controllerAddress.indexOf("_") + 1) : controllerAddress;
        controllerAddress = controllerAddress.replace("_", "/");
        StringBuffer sb = new StringBuffer();
        sb.append("/").append(controllerAddress);
        if (StringUtils.isNotBlank(controllerAddress)) {
            sb.append("/");
        }
        map.put("controllerAddress", sb.toString());

        // TEST
        System.out.println("get system params: " + JSON.toJSONString(map));
        // ./TEST

        VelocityContext context = new VelocityContext(map);
        return context;
    }

    private void initVelocity() {
        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
    }

    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }
        return columnToJava(tableName);
    }

    /**
     * 获取文件名
     * <p>
     * 暂不按照完整路径生成存放文件目录，请手动将相关的文件复制到对应的目录中
     *
     * @param template    模板全路径
     * @param className   当前类名
     * @param packageName 生成完整路径使用，参数暂不启用
     * @param moduleName  当前模块名称
     * @return
     */
    public static String getFileName(String template, String className, String packageName, String moduleName) {
        String[] templateInfo = template.split("/");
        String type = templateInfo[1];

        String templateFileName = templateInfo[templateInfo.length - 1];
        String templateFileDirectory = templateFileName.substring(0, templateFileName.indexOf(".")).toLowerCase();
        String javaFileName = className.concat(templateFileName.substring(0, templateFileName.lastIndexOf(".")));

        StringBuffer javaFilePath = new StringBuffer();
        javaFilePath.append(type).append(File.separator).append(moduleName).append(File.separator);
        for (int i = 2; i < templateInfo.length; i++) {
            if (i >= templateInfo.length - 1) {
                javaFilePath.append(javaFileName);
            } else {
                javaFilePath.append(templateInfo[i]).append(File.separator);
            }
        }
        return javaFilePath.toString();
    }

    /**
     * TODO TEST
     *
     * @param args
     */
    public static void main(String[] args) throws FileNotFoundException {
//        Object l = getFileList("/Users/wangquan/IdeaProjects/wq/quan/quan-code/src/main/resources/template/quan-app");
//        System.out.println(JSON.toJSONString(l));
    }
}

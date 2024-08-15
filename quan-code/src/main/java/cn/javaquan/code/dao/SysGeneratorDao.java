package cn.javaquan.code.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author chenshun/sunlightcs@gmail.com
 * @since 1.0.0
 */
@Mapper
public interface SysGeneratorDao {

    /**
     * 查询表信息.
     * @param map 参数
     * @return 表信息
     */
    List<Map<String, Object>> queryList(Map<String, Object> map);

    /**
     * 查询表数量.
     * @param map 参数
     * @return 数量
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 查询表信息.
     * @param tableName 表名
     * @return 表信息
     */
    Map<String, String> queryTable(String tableName);

    /**
     * 查询表列信息.
     * @param tableName 表名
     * @return 列信息
     */
    List<Map<String, String>> queryColumns(String tableName);

}

package cn.javaquan.code.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import cn.javaquan.code.dao.SysGeneratorDao;
import cn.javaquan.code.entity.vo.CodeVo;
import cn.javaquan.code.utils.GenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 代码生成器
 *
 * @author chenshun/sunlightcs@gmail.com
 * @since 1.0.0
 */
@Service
public class SysGeneratorService {

    @Autowired
    private SysGeneratorDao sysGeneratorDao;

    @Autowired
    private GenUtils genUtils;

    /**
     * 查询表信息.
     * @param map 参数
     * @return 表信息
     */
    public List<Map<String, Object>> queryList(Map<String, Object> map) {
        return sysGeneratorDao.queryList(map);
    }

    /**
     * 查询表数量.
     * @param map 参数
     * @return 数量
     */
    public int queryTotal(Map<String, Object> map) {
        return sysGeneratorDao.queryTotal(map);
    }

    /**
     * 查询表信息.
     * @param tableName 表名
     * @return 表信息
     */
    public Map<String, String> queryTable(String tableName) {
        return sysGeneratorDao.queryTable(tableName);
    }

    /**
     * 查询表列信息.
     * @param tableName 表名
     * @return 列信息
     */
    public List<Map<String, String>> queryColumns(String tableName) {
        return sysGeneratorDao.queryColumns(tableName);
    }

    /**
     * 生成代码.
     * @param codeVo 参数
     * @return 文件数据流
     * @throws IOException 异常
     */
    public byte[] generatorCode(CodeVo codeVo) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (ZipOutputStream zip = new ZipOutputStream(outputStream)) {
            for (String tableName : codeVo.getTableNames()) {
                // 查询表信息
                Map<String, String> table = queryTable(tableName);
                // 查询列信息
                List<Map<String, String>> columns = queryColumns(tableName);
                // 生成代码
                genUtils.generatorCode(table, columns, zip);
            }
        }

        return outputStream.toByteArray();
    }

}

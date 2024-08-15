package cn.javaquan.code.controller;

import cn.javaquan.code.service.SysGeneratorService;
import cn.javaquan.code.entity.vo.CodeVo;
import cn.javaquan.code.utils.PageUtils;
import cn.javaquan.code.utils.Query;
import cn.javaquan.code.utils.R;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author chenshun/sunlightcs@gmail.com
 * @since 1.0.0
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {

    @Autowired
    private SysGeneratorService sysGeneratorService;

    /**
     * 列表.
     * @param params params
     * @return R
     */
    @ResponseBody
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<Map<String, Object>> list = sysGeneratorService.queryList(query);
        int total = sysGeneratorService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 生成代码.
     * @param codeVo codeVo
     * @param response response
     * @throws IOException 异常
     */
    @PostMapping("/code")
    public void code(@RequestBody CodeVo codeVo, HttpServletResponse response) throws IOException {

        byte[] data = sysGeneratorService.generatorCode(codeVo);

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"www.quan.icu.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }

}

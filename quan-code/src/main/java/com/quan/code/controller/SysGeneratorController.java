package com.quan.code.controller;

import com.quan.code.entity.vo.CodeVo;
import com.quan.code.service.SysGeneratorService;
import com.quan.code.utils.PageUtils;
import com.quan.code.utils.Query;
import com.quan.code.utils.R;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午9:12:58
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {
    @Autowired
    private SysGeneratorService sysGeneratorService;

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<Map<String, Object>> list = sysGeneratorService.queryList(query);
        int total = sysGeneratorService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 生成代码
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

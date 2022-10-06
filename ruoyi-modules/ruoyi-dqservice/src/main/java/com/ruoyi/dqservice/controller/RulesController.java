package com.ruoyi.dqservice.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.dqservice.domain.Rules;
import com.ruoyi.dqservice.service.IRulesService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 规则Controller
 * 
 * @author ruoyi
 * @date 2022-10-06
 */
@RestController
@RequestMapping("/rule")
public class RulesController extends BaseController
{
    @Autowired
    private IRulesService rulesService;

    /**
     * 查询规则列表
     */
    @RequiresPermissions("dqservice:rule:list")
    @GetMapping("/list")
    public TableDataInfo list(Rules rules)
    {
        startPage();
        List<Rules> list = rulesService.selectRulesList(rules);
        return getDataTable(list);
    }

    /**
     * 导出规则列表
     */
    @RequiresPermissions("dqservice:rule:export")
    @Log(title = "规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Rules rules)
    {
        List<Rules> list = rulesService.selectRulesList(rules);
        ExcelUtil<Rules> util = new ExcelUtil<Rules>(Rules.class);
        util.exportExcel(response, list, "规则数据");
    }

    /**
     * 获取规则详细信息
     */
    @RequiresPermissions("dqservice:rule:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(rulesService.selectRulesById(id));
    }

    /**
     * 新增规则
     */
    @RequiresPermissions("dqservice:rule:add")
    @Log(title = "规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Rules rules)
    {
        return toAjax(rulesService.insertRules(rules));
    }

    /**
     * 修改规则
     */
    @RequiresPermissions("dqservice:rule:edit")
    @Log(title = "规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Rules rules)
    {
        return toAjax(rulesService.updateRules(rules));
    }

    /**
     * 删除规则
     */
    @RequiresPermissions("dqservice:rule:remove")
    @Log(title = "规则", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(rulesService.deleteRulesByIds(ids));
    }
}

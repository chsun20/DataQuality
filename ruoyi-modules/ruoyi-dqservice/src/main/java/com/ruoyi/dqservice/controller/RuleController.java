package com.ruoyi.dqservice.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.dqservice.dto.DroolsRule2;
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
import com.ruoyi.dqservice.domain.Rule;
import com.ruoyi.dqservice.service.IRuleService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 规则Controller
 *
 * @author ruoyi
 * @date 2022-10-26
 */
@RestController
@RequestMapping("/rule")
public class RuleController extends BaseController
{
    @Autowired
    private IRuleService ruleService;

    /**
     * 查询规则列表
     */
    @RequiresPermissions("dqservice:rule:list")
    @GetMapping("/list")
    public TableDataInfo list(Rule rule)
    {
        startPage();
        List<Rule> list = ruleService.selectRuleList(rule);
        return getDataTable(list);
    }

    /**
     * 导出规则列表
     */
    @RequiresPermissions("dqservice:rule:export")
    @Log(title = "规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Rule rule)
    {
        List<Rule> list = ruleService.selectRuleList(rule);
        ExcelUtil<Rule> util = new ExcelUtil<Rule>(Rule.class);
        util.exportExcel(response, list, "规则数据");
    }

    /**
     * 获取规则详细信息
     */
    @RequiresPermissions("dqservice:rule:query")
    @GetMapping(value = "/{ruleId}")
    public AjaxResult getInfo(@PathVariable("ruleId") Long ruleId)
    {
        return AjaxResult.success(ruleService.selectRuleByRuleId(ruleId));
    }

    /**
     * 新增规则
     */
    @RequiresPermissions("dqservice:rule:add")
    @Log(title = "规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DroolsRule2 droolsRule2)
    {
        Rule rule = new Rule(droolsRule2);
        return toAjax(ruleService.insertRule(rule));
    }

    /**
     * 修改规则
     */
    @RequiresPermissions("dqservice:rule:edit")
    @Log(title = "规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Rule rule)
    {
        return toAjax(ruleService.updateRule(rule));
    }

    /**
     * 删除规则
     */
    @RequiresPermissions("dqservice:rule:remove")
    @Log(title = "规则", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ruleIds}")
    public AjaxResult remove(@PathVariable Long[] ruleIds)
    {
        return toAjax(ruleService.deleteRuleByRuleIds(ruleIds));
    }
}

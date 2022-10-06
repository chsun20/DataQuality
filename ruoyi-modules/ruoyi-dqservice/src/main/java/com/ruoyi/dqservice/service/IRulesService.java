package com.ruoyi.dqservice.service;

import java.util.List;
import com.ruoyi.dqservice.domain.Rules;

/**
 * 规则Service接口
 * 
 * @author ruoyi
 * @date 2022-10-06
 */
public interface IRulesService 
{
    /**
     * 查询规则
     * 
     * @param id 规则主键
     * @return 规则
     */
    public Rules selectRulesById(Long id);

    /**
     * 查询规则列表
     * 
     * @param rules 规则
     * @return 规则集合
     */
    public List<Rules> selectRulesList(Rules rules);

    /**
     * 新增规则
     * 
     * @param rules 规则
     * @return 结果
     */
    public int insertRules(Rules rules);

    /**
     * 修改规则
     * 
     * @param rules 规则
     * @return 结果
     */
    public int updateRules(Rules rules);

    /**
     * 批量删除规则
     * 
     * @param ids 需要删除的规则主键集合
     * @return 结果
     */
    public int deleteRulesByIds(Long[] ids);

    /**
     * 删除规则信息
     * 
     * @param id 规则主键
     * @return 结果
     */
    public int deleteRulesById(Long id);
}

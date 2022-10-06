package com.ruoyi.dqservice.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dqservice.mapper.RulesMapper;
import com.ruoyi.dqservice.domain.Rules;
import com.ruoyi.dqservice.service.IRulesService;

/**
 * 规则Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-06
 */
@Service
public class RulesServiceImpl implements IRulesService
{
    @Autowired
    private RulesMapper rulesMapper;

    /**
     * 查询规则
     *
     * @param id 规则主键
     * @return 规则
     */
    @Override
    public Rules selectRulesById(Long id)
    {
        return rulesMapper.selectRulesById(id);
    }

    /**
     * 查询规则列表
     *
     * @param rules 规则
     * @return 规则
     */
    @Override
    public List<Rules> selectRulesList(Rules rules)
    {
        return rulesMapper.selectRulesList(rules);
    }

    /**
     * 新增规则
     *
     * @param rules 规则
     * @return 结果
     */
    @Override
    public int insertRules(Rules rules)
    {
        rules.setCreateTime(DateUtils.getNowDate());
        rules.setUserId(SecurityUtils.getLoginUser().getUserid());
        rules.setDel(0);
        return rulesMapper.insertRules(rules);
    }

    /**
     * 修改规则
     *
     * @param rules 规则
     * @return 结果
     */
    @Override
    public int updateRules(Rules rules)
    {
        rules.setUpdateTime(DateUtils.getNowDate());
        return rulesMapper.updateRules(rules);
    }

    /**
     * 批量删除规则
     *
     * @param ids 需要删除的规则主键
     * @return 结果
     */
    @Override
    public int deleteRulesByIds(Long[] ids)
    {
        return rulesMapper.deleteRulesByIds(ids);
    }

    /**
     * 删除规则信息
     *
     * @param id 规则主键
     * @return 结果
     */
    @Override
    public int deleteRulesById(Long id)
    {
        return rulesMapper.deleteRulesById(id);
    }
}

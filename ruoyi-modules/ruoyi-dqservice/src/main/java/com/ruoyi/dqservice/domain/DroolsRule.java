package com.ruoyi.dqservice.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 规则模板生成的规则对象 drools_rule
 *
 * @author ruoyi
 * @date 2022-10-09
 */
public class DroolsRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规则id */
    private Long ruleId;

    /** kieBase名称 */
    @Excel(name = "kieBase名称")
    private String kieBaseName;

    /** kiePackage名称 */
    @Excel(name = "kiePackage名称")
    private String kiePackageName;

    /** 规则内容 */
    @Excel(name = "规则内容")
    private String ruleContent;

    public void setRuleId(Long ruleId)
    {
        this.ruleId = ruleId;
    }

    public Long getRuleId()
    {
        return ruleId;
    }
    public void setKieBaseName(String kieBaseName)
    {
        this.kieBaseName = kieBaseName;
    }

    public String getKieBaseName()
    {
        return kieBaseName;
    }
    public void setKiePackageName(String kiePackageName)
    {
        this.kiePackageName = kiePackageName;
    }

    public String getKiePackageName()
    {
        return kiePackageName;
    }
    public void setRuleContent(String ruleContent)
    {
        this.ruleContent = ruleContent;
    }

    public String getRuleContent()
    {
        return ruleContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("ruleId", getRuleId())
                .append("kieBaseName", getKieBaseName())
                .append("kiePackageName", getKiePackageName())
                .append("ruleContent", getRuleContent())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }

    public void validate() {
        if (this.ruleId == null || isBlank(kieBaseName) || isBlank(kiePackageName) || isBlank(ruleContent)) {
            throw new RuntimeException("参数有问题");
        }
    }

    private boolean isBlank(String str) {
        return str == null || str.isEmpty();
    }
}

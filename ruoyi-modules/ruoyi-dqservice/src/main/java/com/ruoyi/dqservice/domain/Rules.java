package com.ruoyi.dqservice.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 规则对象 rules
 * 
 * @author ruoyi
 * @date 2022-10-06
 */
public class Rules extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规则id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 规则地址 */
    @Excel(name = "规则地址")
    private String url;

    /** 删除位 */
    @Excel(name = "删除位")
    private Integer del;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setDel(Integer del) 
    {
        this.del = del;
    }

    public Integer getDel() 
    {
        return del;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("url", getUrl())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("del", getDel())
            .toString();
    }
}

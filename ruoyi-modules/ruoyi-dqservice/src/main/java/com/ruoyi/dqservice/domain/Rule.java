package com.ruoyi.dqservice.domain;

import com.ruoyi.dqservice.dto.ColInfo;
import com.ruoyi.dqservice.dto.DroolsRule2;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

import java.util.Objects;

/**
 * 规则对象 rule
 *
 * @author ruoyi
 * @date 2022-10-26
 */
public class Rule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规则id */
    private Long ruleId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String ruleName;

    /** 创建者id */
    @Excel(name = "创建者id")
    private Long userId;

    /** 创建者名称 */
    @Excel(name = "创建者名称")
    private String userName;

    /** 规则文本 */
    @Excel(name = "规则文本")
    private String ruleContext;

    /** 数据库名称 */
    @Excel(name = "数据库名称")
    private String dbName;

    /** 表名 */
    @Excel(name = "表名")
    private String tableName;

    /** 字段名 */
    @Excel(name = "字段名")
    private String columnName;

    /** 规则类型名称 */
    @Excel(name = "规则类型名称")
    private String typeName;

    /** 规则类型代码 */
    @Excel(name = "规则类型代码")
    private String typeCode;

    /** 分区开始时间 */
    @Excel(name = "分区开始时间")
    private String sourceWhere;

    /** 分区大小 */
    @Excel(name = "分区大小")
    private Integer sourcePartitionNumber;

    /** 分区单位 */
    @Excel(name = "分区单位")
    private String sourcePartitionUnit;

    /** 分区时区 */
    @Excel(name = "分区时区")
    private String sourceTimeZone;

    /** 删除位 */
    @Excel(name = "删除位")
    private Long del;

    public void setRuleId(Long ruleId)
    {
        this.ruleId = ruleId;
    }

    public Long getRuleId()
    {
        return ruleId;
    }
    public void setRuleName(String ruleName)
    {
        this.ruleName = ruleName;
    }

    public String getRuleName()
    {
        return ruleName;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setRuleContext(String ruleContext)
    {
        this.ruleContext = ruleContext;
    }

    public String getRuleContext()
    {
        return ruleContext;
    }
    public void setDbName(String dbName)
    {
        this.dbName = dbName;
    }

    public String getDbName()
    {
        return dbName;
    }
    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public String getTableName()
    {
        return tableName;
    }
    public void setColumnName(String columnName)
    {
        this.columnName = columnName;
    }

    public String getColumnName()
    {
        return columnName;
    }
    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public String getTypeName()
    {
        return typeName;
    }
    public void setTypeCode(String typeCode)
    {
        this.typeCode = typeCode;
    }

    public String getTypeCode()
    {
        return typeCode;
    }
    public void setSourceWhere(String sourceWhere)
    {
        this.sourceWhere = sourceWhere;
    }

    public String getSourceWhere()
    {
        return sourceWhere;
    }
    public void setSourcePartitionNumber(Integer sourcePartitionNumber)
    {
        this.sourcePartitionNumber = sourcePartitionNumber;
    }

    public Integer getSourcePartitionNumber()
    {
        return sourcePartitionNumber;
    }
    public void setSourcePartitionUnit(String sourcePartitionUnit)
    {
        this.sourcePartitionUnit = sourcePartitionUnit;
    }

    public String getSourcePartitionUnit()
    {
        return sourcePartitionUnit;
    }
    public void setSourceTimeZone(String sourceTimeZone)
    {
        this.sourceTimeZone = sourceTimeZone;
    }

    public String getSourceTimeZone()
    {
        return sourceTimeZone;
    }
    public void setDel(Long del)
    {
        this.del = del;
    }

    public Long getDel()
    {
        return del;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("ruleId", getRuleId())
                .append("ruleName", getRuleName())
                .append("userId", getUserId())
                .append("userName", getUserName())
                .append("ruleContext", getRuleContext())
                .append("dbName", getDbName())
                .append("tableName", getTableName())
                .append("columnName", getColumnName())
                .append("typeName", getTypeName())
                .append("typeCode", getTypeCode())
                .append("sourceWhere", getSourceWhere())
                .append("sourcePartitionNumber", getSourcePartitionNumber())
                .append("sourcePartitionUnit", getSourcePartitionUnit())
                .append("sourceTimeZone", getSourceTimeZone())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("del", getDel())
                .toString();
    }
    public Rule(DroolsRule2 droolsRule2) {
        this.ruleName = droolsRule2.getRuleName();
        this.dbName = droolsRule2.getDb();
        this.tableName = droolsRule2.getTable();
        this.userId = droolsRule2.getUserId();
        this.userName = droolsRule2.getUserName();
        this.sourcePartitionNumber = droolsRule2.getSourcePartitionNumber();
        this.sourcePartitionUnit = droolsRule2.getSourcePartitionUnit();
        this.sourceWhere = droolsRule2.getSourceWhere();
        this.sourceTimeZone = droolsRule2.getSourceTimeZone();
        this.typeName = droolsRule2.getRuleType();
        this.typeCode = droolsRule2.getRuleTypeCode();
        StringBuilder columns = new StringBuilder();
        StringBuilder constraintText = new StringBuilder();
        ColInfo[] cols = droolsRule2.getCols();
        StringBuilder declareText = new StringBuilder();
        // 对于每个字段
        for(int i = 0; i < cols.length; i++) {
            if(i != 0) columns.append(",");
            declareText.append(cols[i].getName()).append(": ").append(cols[i].getType()).append("\n");
            columns.append(cols[i].getName());
            String[] constraints = cols[i].getRules();
            // 对于每个字段的每个约束
            for(int j = 0; j < constraints.length; j++) {
                if(constraintText.length() != 0) constraintText.append(" && ");
                if(Objects.equals(constraints[j], "nullCount"))
                    constraintText.append(cols[i].getName()).append(" != null ");
            }
        }
        this.columnName = columns.toString();
        this.ruleContext = "package com." + droolsRule2.getUserName() + "." + droolsRule2.getRuleName() + "\n" +
                "import java.util.List\n" +
                "declare " + droolsRule2.getTable() + "\n" + declareText + "end\n" +
                "rule \"" + droolsRule2.getRuleName() + "\"\n" +
                "when $li: List()\n$" + droolsRule2.getTable() + ": (" + constraintText + ") from $li\nthen\nend";
    }
}

package com.ruoyi.dqservice.dto;

import lombok.Data;

@Data
public class DroolsRule2 {
    private Long ruleId;

    private String ruleName;

    private String ruleDescription;

    private String userName;

    private Long userId;

    private String ruleType;

    private String ruleTypeCode;

    private ColInfo[] cols;

    private String db;

    private String table;

    private String sourceWhere;

    private String sourceTimeZone;

    private int sourcePartitionNumber;

    private String sourcePartitionUnit;
}

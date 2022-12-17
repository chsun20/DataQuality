package com.ruoyi.dqservice.util;

import com.ruoyi.dqservice.domain.Rule;
import com.ruoyi.dqservice.dto.ColInfo;
import com.sch.order_rules.fact.Bank;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RuleParser {
    public static void nullCountParser(Rule rule) {
        StringBuilder columns = new StringBuilder();
        StringBuilder constraintText = new StringBuilder();
        ColInfo[] cols = rule.getCols();
        StringBuilder declareText = new StringBuilder();
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
        rule.setColumnName(columns.toString());
        rule.setRuleContext("package com." + rule.getUserName() + "." + rule.getRuleName() + "\n" +
                "import java.util.List\n" +
                "declare " + rule.getTableName() + "\n" + declareText + "end\n" +
                "rule \"" + rule.getRuleName() + "\"\n" +
                "when $li: List()\n$" + rule.getTableName() + ": (" + constraintText + ") from $li\nthen\nend");
    }
    public static void aprioriParser(String url) {
        try {
            ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(url)));
            List<Mymap> map = (List<Mymap>) ois.readObject();
            StringBuilder rule = new StringBuilder();
            rule.append("package com.sch.order_rules\nimport java.util.List\n");
            for(int i = 0; i < map.size(); i++) {
                List<String> tmp = map.get(i).li;
                StringBuilder ruleContext = new StringBuilder();
                ruleContext.append("rule \"rule").append(i).append("\"\nwhen $li: List()\n$bank:(");
                for(int j = 0; j < tmp.size(); j++) {
                    if(j != 0) ruleContext.append(" AND ");
                    ruleContext.append(tmp.get(j));
                }
                ruleContext.append(") from $li\nthen\nend\n");
                rule.append(ruleContext);
            }
            writeFile("bank.drl", rule.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void printFile(String url) {
        try {
            ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(url)));
            System.out.println(ois.readObject().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static <T> void writeFile(String url, T t) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(url)));
            oos.writeObject(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<Bank> saveBankData(String url) {
        List<Bank> list = new ArrayList<>();
        try {
            File file = new File(url);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(Files.newInputStream(file.toPath()));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTXT = bufferedReader.readLine();
                while ((lineTXT = bufferedReader.readLine()) != null) {
                    Bank bank = new Bank();
                    String[] line = lineTXT.split(";");
                    if(line.length == 17) {
                        bank.setAge(Integer.parseInt(line[0]));
                        bank.setJob(line[1]);
                        bank.setMarital(line[2]);
                        bank.setEducation(line[3]);
                        bank.setIsDefault(line[4]);
                        bank.setBalance(Integer.parseInt(line[5]));
                        bank.setHousing(line[6]);
                        bank.setLoan(line[7]);
                        bank.setContact(line[8]);
                        bank.setDay(Integer.parseInt(line[9]));
                        bank.setMonth(line[10]);
                        bank.setDuration(Integer.parseInt(line[11]));
                        bank.setCampaign(Integer.parseInt(line[12]));
                        bank.setPdays(Integer.parseInt(line[13]));
                        bank.setPrevious(Integer.parseInt(line[14]));
                        bank.setPoutcome(line[15]);
                        bank.setY(line[16]);
                        list.add(bank);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        writeFile("bankData", list);
        return list;
    }
    public static void main(String[] args) {
        printFile("bank.drl");
    }
}

package com.sch.test;

import com.sch.order_rules.fact.Order;
import org.drools.core.impl.KnowledgeBaseImpl;
import org.drools.core.io.impl.UrlResource;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.definition.type.FactType;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestDrools2 {
    // 实现方式2：在workbench配置javabean，在后端定义与其相同的javabean，再将其insert
    @Test
    public void testWorkbench() throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        String url = "file:///c:\\Users\\TUF\\dockerRepo\\kie\\global\\com\\sch\\order-rules\\1.0.2\\order-rules-1.0.2.jar";
        KieServices kieServices = KieServices.Factory.get();
        UrlResource resource = (UrlResource) kieServices.getResources().newUrlResource(url);
        resource.setBasicAuthentication("enabled");
        resource.setPassword("admin");
        resource.setUsername("admin");
        System.out.println(resource.getFile().getName());
        InputStream is = null;
        try {
            is = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        KieRepository kieRepository = kieServices.getRepository();
        KieModule kieModule = kieRepository.addKieModule(kieServices.getResources().newInputStreamResource(is));
        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());

        KieSession kieSession = kieContainer.newKieSession();

        List<Order> list = new ArrayList<>();
        for(int i = 0; i < 1000000; i++) {
            Order order = new Order();
            order.setInfo("aaaaaaaaaaaab");
            list.add(order);
        }
        int count = 0;
        kieSession.setGlobal("count", count);
        kieSession.insert(list);

        int ruleFiredCount = 0;

        long pre = System.currentTimeMillis();
//        for(int i = 0; i < 1000000; i++)
            ruleFiredCount += kieSession.fireAllRules();
        System.out.println("drools cost " + (System.currentTimeMillis() - pre) + "ms");
        System.out.println("触发了" + ruleFiredCount + "条规则");
        System.out.println("触发了" + count + "条规则2");
//        System.out.println("订单提交之后积分：" + order.getScore());
        kieSession.dispose();
    }
}

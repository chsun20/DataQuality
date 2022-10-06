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
import java.util.Collection;

public class TestDrools {
    @Test
    public void testWorkbench() throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        String url = "http://localhost:8080/drools-wb/maven2/com/sch/order-rules/1.0.0/order-rules-1.0.0.jar";
//        String url = "file:///c:\\Users\\TUF\\dockerRepo\\kie\\global\\com\\sch\\order-rules\\1.0.0\\order-rules-1.0.0.jar";
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

        KieBase kieBase = kieContainer.getKieBase("myKbase1");
//        FactType factType = kieBase.getFactType("com.sch", "Order");
//        Object obj = factType.newInstance();
//        factType.set(obj, "amout", 250);

        String reqFactClassName = "com.*.*.Order";
        Class<?> reqClazz = ((KnowledgeBaseImpl) kieBase).getClassFieldAccessorCache().getClassLoader().loadClass(reqFactClassName);


        KieSession kieSession = kieContainer.newKieSession();

        Collection<?> results = kieSession.getObjects(new ClassObjectFilter(reqClazz));

        Order order = new Order();
        order.setAmout(250);
        kieSession.insert(order);

//        kieSession.insert(obj);

        int ruleFiredCount = kieSession.fireAllRules();
        System.out.println("触发了" + ruleFiredCount + "条规则");
//        System.out.println("订单提交之后积分：" + factType.get(obj, "score"));
        System.out.println("订单提交之后积分：" + order.getScore());
        kieSession.dispose();
    }
}

package com.simes.activiti;

import static org.junit.Assert.assertTrue;

import com.simes.activiti.service.TestService;
import com.simes.core.domain.test.TestDO;
import com.simes.core.util.spring.SpringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.SpringBeanContainer;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void shouldAnswerWithTrue() {
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        TestService service = wac.getBean(TestService.class);
        List<TestDO> testDOS = service.get();
        System.out.println("====size====" + testDOS.size());
        assertTrue( true );
    }
}

package org.txlcn.demo.servicea;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author LiuWang
 * @date 2019/6/19
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class DemoControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenQuerySucess() throws Exception {
        String ex = null;
        final int testTime = 100;
        for (int i = 0; i < testTime; i++) {
            mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/txlcn/1")
                    .param("value", String.valueOf(i))
                    .param("ex", ex)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
            ).andExpect(MockMvcResultMatchers.status().isOk());
        }
    }

    /**
     * 测试 DubboServiceC 集群功能
     */
    @Test
    public void testDubboServiceCAggre() throws Exception {
        final int testTime = 100;
        for (int i = 0; i < testTime; i++) {
            mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/txlcn/2")
                    .param("value", String.valueOf(i))
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
            ).andExpect(MockMvcResultMatchers.status().isOk());
        }
    }
}

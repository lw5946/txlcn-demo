package org.txlcn.demo.dubbo.servicec.config;

import com.codingapi.txlcn.common.util.id.ModIdProvider;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author LiuWang
 * @date 2019/6/26
 */
//@Component
@AllArgsConstructor
public class DemoModIdProvider implements ModIdProvider {
    private ServerProperties serverProperties;
    private ConfigurableEnvironment environment;

    @Override
    public String modId() {
        String name = environment.getProperty("spring.application.name");
        name = StringUtils.hasText(name) ? name : "application";
        return name + serverProperties.getPort();
    }
}

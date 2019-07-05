package org.txlcn.demo.dubbo.servicec.config;

import com.codingapi.txlcn.common.util.Maps;
import com.codingapi.txlcn.tc.core.transaction.txc.analy.def.PrimaryKeysProvider;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author LiuWang
 * @date 2019/6/26
 */
//@Component
public class MysqlPrimaryKeysProvider implements PrimaryKeysProvider {
    @Override
    public Map<String, List<String>> provide() {
        return Maps.newHashMap("t_demo", Collections.singletonList("id"));
    }
}

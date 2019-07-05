package org.txlcn.demo.servicea;

import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.txlcn.demo.common.db.domain.Demo;
import org.txlcn.demo.common.dubbo.DemoServiceB;
import org.txlcn.demo.common.dubbo.DemoServiceC;
import org.txlcn.demo.common.enume.ExFlagEnum;

@Service
@Slf4j
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Reference(retries = -1, loadbalance = "txlcn_random")
    private DemoServiceB demoServiceB;

    @Reference(retries = -1, loadbalance = "txlcn_random")
    private DemoServiceC demoServiceC;

    @Override
    @TxcTransaction(propagation = DTXPropagation.SUPPORTS)
//    @LcnTransaction
    @Transactional
    public String update(String value, String exFlag) {

//        demoServiceB.update(1L, value, exFlag);

        // 调用 serviceB
        String resultB = restTemplate.getForObject("http://127.0.0.1:12002/update?id=1&value=" + value + "&ex=" + exFlag, String.class);

        // 调用 serviceC
        String resultC = restTemplate.getForObject("http://127.0.0.1:12003/update?id=1&value=" + value + "&ex=" + exFlag, String.class);

        // 执行本地事务
        Demo demo = new Demo();
        demo.setDemoField("a:" + value);
        demo.setId(1L);
        demoMapper.update(demo);

        // 调用 dubboService
//        demoServiceC.update(1L, value, exFlag);

        // 置异常标志，DTX 回滚
        if (ExFlagEnum.SERVICE_A.getFlag().equals(exFlag)) {
            throw new IllegalStateException("by exFlag");
        }
        return "1";
    }

    @Override
    @LcnTransaction
    @Transactional
    public String update(String value) {
        demoServiceC.update(1L, value, null);
        return "1";
    }

    @Override
    @TxcTransaction(propagation = DTXPropagation.SUPPORTS)
//    @LcnTransaction
    @Transactional
    public String insertAndUpdate(String value, String exFlag) {

        demoServiceB.insert(value, exFlag);

        // 执行本地事务
        Demo demo = new Demo();
        demo.setDemoField("a:" + value);
        demo.setId(1L);
        demoMapper.update(demo);

        // 调用 dubboService
        demoServiceC.insert(value, exFlag);

        // 置异常标志，DTX 回滚
        if (ExFlagEnum.SERVICE_A.getFlag().equals(exFlag)) {
            throw new IllegalStateException("by exFlag");
        }
        return "1";
    }
}

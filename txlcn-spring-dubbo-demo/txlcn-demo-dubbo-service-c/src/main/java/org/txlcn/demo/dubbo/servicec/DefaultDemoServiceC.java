package org.txlcn.demo.dubbo.servicec;

import com.codingapi.txlcn.common.util.Transactions;
import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TccTransaction;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import com.codingapi.txlcn.tracing.TracingContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.txlcn.demo.common.db.domain.Demo;
import org.txlcn.demo.common.dubbo.DemoServiceC;
import org.txlcn.demo.common.enume.ExFlagEnum;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 * Company: CodingApi
 * Date: 2018/12/14
 *
 * @author ujued
 */
@Service
@Slf4j
public class DefaultDemoServiceC implements DemoServiceC {

    @Autowired
    private DemoMapper demoMapper;

    private ConcurrentHashMap<String, Long> ids = new ConcurrentHashMap<>();

    @Override
//    @TccTransaction(confirmMethod = "cm", cancelMethod = "cl", executeClass = DefaultDemoServiceC.class)
//    @LcnTransaction
    @TxcTransaction
    @Transactional
    public String insert(String name, String exFlag) {
        log.info("执行dubboC.insert({},{})", name, exFlag);
        Demo demo = new Demo();
        demo.setDemoField("dubboC:" + name);
        demoMapper.save(demo);
        if (ExFlagEnum.DUBBO_C_BEFORE.getFlag().equals(exFlag)) {
            throw new IllegalStateException("by exFlag: "+ exFlag);
        }
        return "ok-service-c";
    }

    @Override
//    @TccTransaction(confirmMethod = "cm", cancelMethod = "cl", executeClass = DefaultDemoServiceC.class)
//    @LcnTransaction
    @TxcTransaction
    @Transactional
    public String update(long id, String name, String exFlag) {
        log.info("执行dubboC.rpc({},{})", name, exFlag);
        Demo demo = new Demo();
        demo.setDemoField("dubboC:" + name);
        demo.setId(id);
        demoMapper.update(demo);
        if (ExFlagEnum.DUBBO_C_BEFORE.getFlag().equals(exFlag)) {
            throw new IllegalStateException("by exFlag: "+ exFlag);
        }
        return "ok-service-c";
    }

    public void cm(String name, String exFlag) {
        log.info("tcc-confirm-" + TracingContext.tracing().groupId());
        ids.remove(TracingContext.tracing().groupId());
    }

    public void cl(String name, String exFlag) {
        log.info("tcc-cancel-" + TracingContext.tracing().groupId());
        demoMapper.deleteByKId(ids.get(TracingContext.tracing().groupId()));
    }
}

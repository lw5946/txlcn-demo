package org.txlcn.demo.dubbo.serviceb;

import com.codingapi.txlcn.common.util.Transactions;
import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TxTransaction;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import com.codingapi.txlcn.tracing.TracingContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.txlcn.demo.common.db.domain.Demo;
import org.txlcn.demo.common.dubbo.DemoServiceB;
import org.txlcn.demo.common.enume.ExFlagEnum;

import java.util.Date;

/**
 * Description:
 * Company: CodingApi
 * Date: 2018/12/14
 *
 * @author ujued
 */
@Service
@Slf4j
public class DefaultDemoServiceB implements DemoServiceB {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    @TxcTransaction(propagation = DTXPropagation.SUPPORTS)
//    @LcnTransaction
    @Transactional
    public String insert(String name, String exFlag) {
        log.info("执行dubboB.insert({},{})", name, exFlag);
        Demo demo = new Demo();
        demo.setDemoField("dubboB:" + name);
        demoMapper.save(demo);
        if (ExFlagEnum.DUBBO_B.getFlag().equals(exFlag)) {
            throw new IllegalStateException("by exFlag: "+ exFlag);
        }
        return "ok-service-b";
    }

    @Override
    @TxcTransaction(propagation = DTXPropagation.SUPPORTS)
//    @LcnTransaction
    @Transactional
    public String update(long id, String name, String exFlag) {
        log.info("执行dubboB.update({},{})", name, exFlag);
        Demo demo = new Demo();
        demo.setDemoField("dubboB:" + name);
        demo.setId(id);
        demoMapper.update(demo);
        if (ExFlagEnum.DUBBO_B.getFlag().equals(exFlag)) {
            throw new IllegalStateException("by exFlag: "+ exFlag);
        }
        return "ok-service-b";
    }

}

package org.txlcn.demo.serviceb;

import com.codingapi.txlcn.common.util.Transactions;
import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import com.codingapi.txlcn.tracing.TracingContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.txlcn.demo.common.db.domain.Demo;
import org.txlcn.demo.common.enume.ExFlagEnum;

import java.util.Date;

/**
 * Description:
 * Date: 2018/12/25
 *
 * @author ujued
 */
@Service
@Slf4j
public class DemoServiceImpl implements DemoService {

    private final DemoMapper demoMapper;

    @Autowired
    public DemoServiceImpl(DemoMapper demoMapper) {
        this.demoMapper = demoMapper;
    }

    @Override
//    @TxcTransaction(propagation = DTXPropagation.SUPPORTS)
    @LcnTransaction
    @Transactional
    public String insert(String value, String exFlag) {
        log.info("执行b.insert({},{})", value, exFlag);
        Demo demo = new Demo();
        demo.setDemoField("b:" + value);
        demoMapper.save(demo);
        if (ExFlagEnum.SERVICE_B.getFlag().equals(exFlag)) {
            throw new IllegalStateException("by exFlag: "+ exFlag);
        }
        return "ok-service-b";
    }

    @Override
//    @TxcTransaction(propagation = DTXPropagation.SUPPORTS)
    @LcnTransaction
    @Transactional
    public String update(long id, String value, String exFlag) {
        log.info("执行b.update({},{})", value, exFlag);
        Demo demo = new Demo();
        demo.setDemoField("b:" + value);
        demo.setId(id);
        demoMapper.update(demo);
        if (ExFlagEnum.SERVICE_B.getFlag().equals(exFlag)) {
            throw new IllegalStateException("by exFlag: "+ exFlag);
        }
        return "ok-service-b";
    }
}

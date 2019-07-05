package org.txlcn.demo.common.dubbo;

/**
 * Description:
 * Date: 2018/12/25
 *
 * @author ujued
 */
public interface DemoServiceB {
    String insert(String value, String exFlag);

    String update(long id, String value, String exFlag);
}

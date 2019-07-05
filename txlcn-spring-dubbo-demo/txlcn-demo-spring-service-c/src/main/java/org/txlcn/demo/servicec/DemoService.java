package org.txlcn.demo.servicec;

/**
 * Description:
 * Date: 2018/12/25
 *
 * @author ujued
 */
public interface DemoService {

    String insert(String value, String exFlag);

    String update(long id, String value, String exFlag);
}

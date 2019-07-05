package org.txlcn.demo.servicea;

public interface DemoService {

    String update(String value, String ex);

    String update(String value);

    String insertAndUpdate(String value, String exFlag);
}

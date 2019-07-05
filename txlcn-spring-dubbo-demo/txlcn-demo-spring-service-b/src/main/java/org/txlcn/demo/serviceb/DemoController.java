package org.txlcn.demo.serviceb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 * Date: 2018/12/25
 *
 * @author ujued
 */
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/insert")
    public String insert(@RequestParam("value") String value, @RequestParam("ex") String exFlag) {
        return demoService.insert(value, exFlag);
    }

    @GetMapping("/update")
    public String update(@RequestParam long id, @RequestParam("value") String value, @RequestParam("ex") String exFlag) {
        return demoService.update(id, value, exFlag);
    }
}

package org.txlcn.demo.servicea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/txlcn")
public class DemoController {

    private final DemoService demoService;

    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @RequestMapping("/1")
    public String update(@RequestParam("value") String value, @RequestParam(value = "ex", required = false) String exFlag) {
        return demoService.update(value, exFlag);
    }

    @RequestMapping("/2")
    public String update(@RequestParam("value") String value) {
        return demoService.update(value);
    }

    @RequestMapping("/3")
    public String insertAndUpdate(@RequestParam("value") String value, @RequestParam(value = "ex", required = false) String exFlag) {
        return demoService.insertAndUpdate(value, exFlag);
    }
}

package com.gradus.controller;

import com.gradus.domain.Test;
import com.gradus.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    private TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String ping() {
        return "Pong";
    }

    @RequestMapping(path = "/test", method = RequestMethod.POST)
    public Test saveTestEntity(@RequestBody Test test) {
        return testService.save(test);
    }

    @RequestMapping(path = "/tests", method = RequestMethod.GET)
    public List<Test> getAll() {
        return testService.findAll();
    }
}

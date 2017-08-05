package com.gradus.service;

import com.gradus.dao.TestDao;
import com.gradus.domain.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    private TestDao testDao;

    @Autowired
    public TestService(TestDao testDao) {
        this.testDao = testDao;
    }

    public Test save(Test test) {
        return testDao.save(test);
    }

    public List<Test> findAll() {
        return testDao.findAll();
    }
}

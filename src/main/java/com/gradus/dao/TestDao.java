package com.gradus.dao;

import com.gradus.domain.Test;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TestDao extends CrudRepository<Test, Long> {
    List<Test> findAll();
}

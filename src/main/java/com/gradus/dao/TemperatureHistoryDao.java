package com.gradus.dao;

import com.gradus.domain.TemperatureHistory;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface TemperatureHistoryDao extends CrudRepository<TemperatureHistory, ObjectId> {
    List<TemperatureHistory> findByDateBetween(Date dateFrom, Date dateTo);
}

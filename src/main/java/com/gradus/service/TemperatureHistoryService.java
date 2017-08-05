package com.gradus.service;

import com.gradus.dao.TemperatureHistoryDao;
import com.gradus.domain.TemperatureHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemperatureHistoryService {
    private final TemperatureHistoryDao temperatureHistoryDao;

    @Autowired
    public TemperatureHistoryService(final TemperatureHistoryDao temperatureHistoryDao) {
        this.temperatureHistoryDao = temperatureHistoryDao;
    }

    public TemperatureHistory add(TemperatureHistory temperatureHistory) {
        return temperatureHistoryDao.save(temperatureHistory);
    }

    public List<TemperatureHistory> getTemperatureHistory() {
        return temperatureHistoryDao.findTop20ByOrderByDateDesc();
    }

    public TemperatureHistory getLatestHistory() {
        return temperatureHistoryDao.findTopByOrderByDateDesc();
    }
}

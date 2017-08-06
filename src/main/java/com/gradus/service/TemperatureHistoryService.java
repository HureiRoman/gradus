package com.gradus.service;

import com.gradus.dao.TemperatureHistoryDao;
import com.gradus.domain.TemperatureHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemperatureHistoryService {
    private final TemperatureHistoryDao temperatureHistoryDao;
    private final ConditionerSettingService conditionerSettingService;

    @Autowired
    public TemperatureHistoryService(final TemperatureHistoryDao temperatureHistoryDao,
                             final ConditionerSettingService conditionerSettingService) {
        this.temperatureHistoryDao = temperatureHistoryDao;
        this.conditionerSettingService = conditionerSettingService;
    }

    public String add(TemperatureHistory temperatureHistory) {
        if (temperatureHistory.getHumidity() != null && temperatureHistory.getTemperature() != null) {
            temperatureHistoryDao.save(temperatureHistory);
        }
        
        return conditionerSettingService.getHexCode();
    }

    public List<TemperatureHistory> getTemperatureHistory() {
        return temperatureHistoryDao.findTop20ByOrderByDateDesc();
    }

    public TemperatureHistory getLatestHistory() {
        return temperatureHistoryDao.findTopByOrderByDateDesc();
    }
}

package com.gradus.service;

import com.gradus.dao.TemperatureHistoryDao;
import com.gradus.dao.WeeklySettingDao;
import com.gradus.domain.TemperatureHistory;
import com.gradus.domain.WeeklySetting;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeeklySettingService {
    private final WeeklySettingDao weeklySettingDao;
    private final ObjectId defaultId = new ObjectId("111111111111111111111111");


    @Autowired
    public WeeklySettingService(final WeeklySettingDao weeklySettingDao) {
        this.weeklySettingDao = weeklySettingDao;
    }

    public WeeklySetting add(WeeklySetting weeklySetting) {
        weeklySetting.setId(defaultId);
        return weeklySettingDao.save(weeklySetting);
    }

    public WeeklySetting get() {
        return weeklySettingDao.findOne(defaultId);
    }
}

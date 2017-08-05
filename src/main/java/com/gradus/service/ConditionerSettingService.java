package com.gradus.service;

import com.gradus.dao.ConditionerSettingDao;
import com.gradus.domain.ConditionerSetting;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConditionerSettingService {
    private final ConditionerSettingDao conditionerSettingDao;
    private final ObjectId defaultId = new ObjectId("111111111111111111111111");

    @Autowired
    public ConditionerSettingService(final ConditionerSettingDao conditionerSettingDao) {
        this.conditionerSettingDao = conditionerSettingDao;
    }

    public ConditionerSetting findSettings() {
        return conditionerSettingDao.findOne(defaultId);
    }

    public ConditionerSetting updateSettings(final ConditionerSetting conditionerSetting) {
        ConditionerSetting setting = findSettings();

        if (setting == null) {
           setting = new ConditionerSetting();
           setting.setId(defaultId);
        }

        setting.setTemperature(conditionerSetting.getTemperature());
        return conditionerSettingDao.save(setting);
    }
}

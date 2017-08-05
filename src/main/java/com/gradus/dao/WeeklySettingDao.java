package com.gradus.dao;

import com.gradus.domain.WeeklySetting;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

public interface WeeklySettingDao extends CrudRepository<WeeklySetting, ObjectId> {
    WeeklySetting save(WeeklySetting setting);
}

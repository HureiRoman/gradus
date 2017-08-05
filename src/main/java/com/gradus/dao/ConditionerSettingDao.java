package com.gradus.dao;

import com.gradus.domain.ConditionerSetting;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

public interface ConditionerSettingDao extends CrudRepository<ConditionerSetting, ObjectId> {
}

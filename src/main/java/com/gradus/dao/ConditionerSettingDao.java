package com.gradus.dao;

import com.gradus.domain.ConditionerSetting;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Book;
import java.util.List;

public interface ConditionerSettingDao extends CrudRepository<ConditionerSetting, ObjectId> {
    List<ConditionerSetting> save(List<ConditionerSetting> settings);
    List<ConditionerSetting> findAll();

    @Query("{ 'minutes_before' : { $gte: ?0}, 'minutes_after': {$lte: ?0} }")
    ConditionerSetting findMinutesBetween(Integer currentDayMinutes);
}

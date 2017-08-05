package com.gradus.dao;

import com.gradus.domain.ConditionerSetting;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Book;
import java.util.List;

public interface ConditionerSettingDao extends CrudRepository<ConditionerSetting, ObjectId> {
    ConditionerSetting save(ConditionerSetting settings);
    List<ConditionerSetting> findAll();

    @Query("{$and: [{ 'minutesFrom' : { $lte: ?0}, 'minutesTo': {$gte: ?0} }]}")
    ConditionerSetting findMinutesBetween(Integer currentDayMinutes);
}

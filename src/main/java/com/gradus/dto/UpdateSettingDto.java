package com.gradus.dto;

import com.gradus.domain.ConditionerSetting;
import lombok.Data;

import java.util.List;

@Data
public class UpdateSettingDto {
    List<ConditionerSetting> settings;
}

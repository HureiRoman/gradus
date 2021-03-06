package com.gradus.service;

import com.gradus.constants.ConditionerState;
import com.gradus.constants.FanState;
import com.gradus.constants.Mode;
import com.gradus.dao.ConditionerSettingDao;
import com.gradus.domain.ConditionerSetting;
import com.gradus.domain.WeeklySetting;
import com.gradus.dto.UpdateSettingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConditionerSettingService {
    private static final Integer CHECK_SUM_SHIFT = 4;
    private static final Integer TEMPERATURE_SHIFT = 15;

    private final ConditionerSettingDao conditionerSettingDao;
    private final WeeklySettingService weeklySettingService;
    private final String TURN_OFF_HEX_VALUE = "[143392849]";

    @Autowired
    public ConditionerSettingService(final ConditionerSettingDao conditionerSettingDao,
                                     final WeeklySettingService weeklySettingService) {
        this.conditionerSettingDao = conditionerSettingDao;
        this.weeklySettingService = weeklySettingService;
    }

    public List<ConditionerSetting> findSettings() {
        return conditionerSettingDao.findAll();
    }

    public List<ConditionerSetting> saveSettings(final UpdateSettingDto conditionerSettings) {
       setDefaultValues(conditionerSettings.getSettings());
       deleteAllPreviousSettings();

       List<ConditionerSetting> savedSettings = new ArrayList<>();

       for (ConditionerSetting conditionerSetting: conditionerSettings.getSettings()) {
           conditionerSetting.setId(null);
           savedSettings.add(conditionerSettingDao.save(conditionerSetting));
       }

        return savedSettings;
    }

    private void deleteAllPreviousSettings() {
        conditionerSettingDao.deleteAll();
    }

    private void setDefaultValues(List<ConditionerSetting> conditionerSettings) {
        for (ConditionerSetting setting: conditionerSettings) {
            setting.setUnknown1(8);
            setting.setUnknown2(8);
            setting.setFanState(FanState.Max);
            setting.setMode(Mode.Cooling);
            setting.setIsOn(true);
        }
    }

    public String getHexCode() {
        ConditionerSetting setting = findCurrentSettings();

        if (setting == null || !isRightDay()) {
            return TURN_OFF_HEX_VALUE;
        }

        String hexCodeWithoutChecksum = buildHexCode(setting);
        Integer hextCodeInt = Integer.parseInt(hexCodeWithoutChecksum, 2);

        return getHexCodeWithCheckSum(hextCodeInt, setting);
    }

    private Boolean isRightDay() {
        WeeklySetting weeklySetting = weeklySettingService.get();

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Kiev"));

        switch (now.getDayOfWeek()) {
            case MONDAY:
                return weeklySetting.getIsMonday();
            case TUESDAY:
                return weeklySetting.getIsTuesday();
            case WEDNESDAY:
                return weeklySetting.getIsWednesday();
            case THURSDAY:
                return weeklySetting.getIsThursday();
            case FRIDAY:
                return weeklySetting.getIsFriday();
            case SATURDAY:
                return weeklySetting.getIsSaturday();
            case SUNDAY:
                return weeklySetting.getIsSunday();
        }

        return false;
    }

    private ConditionerSetting findCurrentSettings() {
        return conditionerSettingDao.findMinutesBetween(getCurrentDayMinutes());
    }

    private Integer getCurrentDayMinutes() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Kiev"));

        return now.getHour() * 60 + now.getMinute();
    }

    private String getHexCodeWithCheckSum(Integer hextCodeInt, ConditionerSetting setting) {
        hextCodeInt = shiftHexCodeForCheckSum(hextCodeInt);
        Integer checkSumWithoutMask = calculateCheckSumWithoutMask(setting);

        return getCheckSum(checkSumWithoutMask, hextCodeInt);
    }

    private String getCheckSum(Integer checkSumWithoutMask, Integer hextCodeInt) {
        Integer checkSumWithMask = populateBinaryStringWithOnes(checkSumWithoutMask);

        return "[" + Integer.toString(checkSumWithMask | hextCodeInt, 10) + "]";
    }

    private Integer populateBinaryStringWithOnes(Integer checkSumWithoutMask) {
        String binaryString =  Integer.toString(checkSumWithoutMask,2);
        String checkSumWithMask = ("000000000000000000000000" + binaryString).substring(binaryString.length());
        return Integer.parseInt(checkSumWithMask,2);
    }

    private Integer calculateCheckSumWithoutMask(ConditionerSetting setting) {
        Integer checkSum = (setting.getTemperature() - TEMPERATURE_SHIFT)
                + setting.getUnknown1() + setting.getUnknown2()
                + setting.getFanState().getValue() + getIsOnBinaryValue(setting.getIsOn());

        return checkSum % 16;
    }

    private Integer shiftHexCodeForCheckSum(Integer hextCodeInt) {
        return hextCodeInt << CHECK_SUM_SHIFT;
    }

    private String buildHexCode(ConditionerSetting setting) {
        String hexCode = "";

        hexCode += populateBinaryStringWithZeros(setting.getUnknown1());
        hexCode += populateBinaryStringWithZeros(setting.getUnknown2());
        hexCode += populateBinaryStringWithZeros(getIsOnBinaryValue(setting.getIsOn()));
        hexCode += populateBinaryStringWithZeros(0);
        hexCode += populateBinaryStringWithZeros(setting.getTemperature() - TEMPERATURE_SHIFT);
        hexCode += populateBinaryStringWithZeros(setting.getFanState().getValue());

        return hexCode;
    }

    private Integer getIsOnBinaryValue(Boolean isOn) {
        if (isOn) {
           return ConditionerState.On.getValue();
        } else {
            return ConditionerState.Off.getValue();
        }
    }

    private String populateBinaryStringWithZeros(Integer value) {
        String binaryString =  Integer.toString(value,2);
        return ("0000" + binaryString).substring(binaryString.length());
    }
}

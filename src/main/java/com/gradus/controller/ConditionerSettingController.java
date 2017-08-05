package com.gradus.controller;

import com.gradus.domain.ConditionerSetting;
import com.gradus.service.ConditionerSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*", allowedHeaders="*", methods={RequestMethod.POST, RequestMethod.GET})
@RestController
public class ConditionerSettingController {
    private final ConditionerSettingService conditionerSettingService;

    @Autowired
    public ConditionerSettingController(final ConditionerSettingService conditionerSettingService) {
        this.conditionerSettingService = conditionerSettingService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String ping() {
        return "Poing";
    }


    @RequestMapping(path = "/setting", method = RequestMethod.GET)
    public ConditionerSetting getSettings() {
        return conditionerSettingService.findSettings();
    }

    @RequestMapping(path = "/settings", method = RequestMethod.POST)
    public ConditionerSetting updateSettings(@RequestBody ConditionerSetting conditionerSetting) {
        return conditionerSettingService.updateSettings(conditionerSetting);
    }

    @RequestMapping(path = "/setting/hex", method = RequestMethod.GET)
    public String getSettingsHex() {
        return conditionerSettingService.getHexCode();
    }

}

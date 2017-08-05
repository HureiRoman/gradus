package com.gradus.controller;

import com.gradus.domain.WeeklySetting;
import com.gradus.service.WeeklySettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*", allowedHeaders="*", methods={RequestMethod.POST, RequestMethod.GET})
@RestController
public class WeeklySettingController {
    private final WeeklySettingService weeklySettingService;

    @Autowired
    public WeeklySettingController(final WeeklySettingService weeklySettingService) {
        this.weeklySettingService = weeklySettingService;
    }

    @RequestMapping(path = "/weekly-settings", method = RequestMethod.GET)
    public WeeklySetting get() {
        return weeklySettingService.get();
    }

    @RequestMapping(path = "/weekly-setting", method = RequestMethod.POST)
    public WeeklySetting save(@RequestBody WeeklySetting weeklySetting) {
        return weeklySettingService.add(weeklySetting);
    }

}

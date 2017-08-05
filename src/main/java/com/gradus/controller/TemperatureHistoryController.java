package com.gradus.controller;

import com.gradus.domain.TemperatureHistory;
import com.gradus.dto.GetTemperatureHistoryDto;
import com.gradus.service.TemperatureHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins="*", allowedHeaders="*", methods={RequestMethod.POST, RequestMethod.GET})
@RestController
public class TemperatureHistoryController {
    private final TemperatureHistoryService temperatureHistoryService;

    @Autowired
    public TemperatureHistoryController(final TemperatureHistoryService temperatureHistoryService) {
        this.temperatureHistoryService = temperatureHistoryService;
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public TemperatureHistory add(@RequestBody TemperatureHistory temperatureHistory) {
        return temperatureHistoryService.add(temperatureHistory);
    }

    @RequestMapping(path = "/temperature-history", method = RequestMethod.POST)
    public List<TemperatureHistory> add(@RequestBody GetTemperatureHistoryDto getTemperatureHistoryDto) {
        return temperatureHistoryService.getTemperatureHistory(getTemperatureHistoryDto);
    }
}

package com.gradus.dto;

import lombok.Data;

import java.util.Date;

@Data
public class GetTemperatureHistoryDto {
    Date dateFrom;
    Date dateTo;
}

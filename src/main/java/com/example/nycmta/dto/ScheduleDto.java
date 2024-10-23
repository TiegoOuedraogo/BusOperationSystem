package com.example.nycmta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {
    private Long scheduleId;
    private BusDto bustDto;
    private BusRouteResponseDto busRouteResponseDto;
    private BusStopResponseDto busStopResponseDto;
    private String departureTime;
    private String arrivalTime;
}

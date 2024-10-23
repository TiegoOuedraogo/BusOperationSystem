package com.example.nycmta.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class ScheduleResponseDto {
    private Long scheduleId;
    private BusDto busDto;
    private BusRouteResponseDto busRouteResponseDto;
    private BusStopResponseDto busStopResponseDto;
    private String departureTime;
    private String arrivalTime;
}

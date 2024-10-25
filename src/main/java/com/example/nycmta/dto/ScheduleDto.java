package com.example.nycmta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {
    private Long scheduleId;
    private Long busId;
    private Long routeId;
    private Long stopId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
}


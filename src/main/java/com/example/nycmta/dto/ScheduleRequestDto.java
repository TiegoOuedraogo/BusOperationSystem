package com.example.nycmta.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleRequestDto {
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Long busId;
    private Long routeId;
    private Long stopId;
}


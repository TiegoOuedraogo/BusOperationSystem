package com.example.nycmta.dto;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor

public class ScheduleResponseDto {
    private Long scheduleId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;


    @Builder
    public ScheduleResponseDto(
            Long scheduleId,
            LocalDateTime departureTime,
            LocalDateTime arrivalTime
    ){
        this.scheduleId = scheduleId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

}


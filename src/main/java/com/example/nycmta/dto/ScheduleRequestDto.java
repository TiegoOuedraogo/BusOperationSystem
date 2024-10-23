package com.example.nycmta.dto;

import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ScheduleRequestDto {
    private LocalDateTime departure;
}

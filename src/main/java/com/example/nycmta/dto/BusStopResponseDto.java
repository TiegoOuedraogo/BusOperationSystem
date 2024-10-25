package com.example.nycmta.dto;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusStopResponseDto {
    private Long stopId;
    private String stopName;
    private String location;
}



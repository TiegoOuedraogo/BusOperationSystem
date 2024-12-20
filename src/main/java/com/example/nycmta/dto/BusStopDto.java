package com.example.nycmta.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusStopDto {
    private Long stopId;
    private String stopName;
    private String location;
}


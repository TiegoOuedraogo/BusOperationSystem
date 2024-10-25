package com.example.nycmta.dto;

import lombok.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteStopDto {
    private Long stopId;
    private String stopName;
    private String location;
    private Integer stopOrder;

}


package com.example.nycmta.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteStopRequestDto {
    private Long stopId;
    private Long routeId;
    private Integer stopOrder;
}


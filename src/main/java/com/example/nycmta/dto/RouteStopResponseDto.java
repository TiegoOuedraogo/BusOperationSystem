package com.example.nycmta.dto;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteStopResponseDto {
    private Long routeStopId;
    private Integer stopOrder;
    private BusRouteDto busRoute;
    private BusStopDto busStop;

}


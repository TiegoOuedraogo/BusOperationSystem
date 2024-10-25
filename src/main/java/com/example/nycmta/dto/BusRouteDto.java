package com.example.nycmta.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusRouteDto {
    private Long routeId;
    private String routeName;
}


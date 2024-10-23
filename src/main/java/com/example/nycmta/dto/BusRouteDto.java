package com.example.nycmta.dto;

import com.example.nycmta.entities.RouteStop;
import com.example.nycmta.entities.Schedule;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class BusRouteDto {
    private Long routeId;
    private String routeName;
    private List<RouteStop> routesStop;
    private List<Schedule> schedules;
}

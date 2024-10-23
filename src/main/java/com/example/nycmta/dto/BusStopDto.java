package com.example.nycmta.dto;

import com.example.nycmta.entities.RouteStop;
import com.example.nycmta.entities.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BusStopDto {
    private Long stopId;
    private String stopName;
    private String location;
    private List <RouteStop> routes;
    private List <Schedule> schedules;
}

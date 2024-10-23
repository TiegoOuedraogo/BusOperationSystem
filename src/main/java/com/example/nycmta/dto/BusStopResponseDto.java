package com.example.nycmta.dto;

import com.example.nycmta.entities.Bus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BusStopResponseDto {
    private String stopId;
    private String stopName;
    private String location;
    private List<BusRouteResponseDto> busRoutes;
    private List<ScheduleResponseDto> schedules;
    private List<Bus> bus;
}

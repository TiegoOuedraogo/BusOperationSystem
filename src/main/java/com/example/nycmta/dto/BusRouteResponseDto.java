package com.example.nycmta.dto;

import com.example.nycmta.entities.Bus;
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

public class BusRouteResponseDto {
    private Long routeId;
    private List<BusStopResponseDto> busStops;
    private List<Bus> bus;
    private List<Schedule> schedules;

}

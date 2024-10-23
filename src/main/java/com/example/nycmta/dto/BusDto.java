package com.example.nycmta.dto;

import com.example.nycmta.entities.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BusDto {
    private Long busId;
    private String busNumber;
    private Integer capacity;
    private List<Schedule> schedules;
}


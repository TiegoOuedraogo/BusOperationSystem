package com.example.nycmta.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bus_stop", schema = "nycmta")
public class BusStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stopId;

    private String stopName;
    private String location;

    @OneToMany(mappedBy = "busStop")
    private List<RouteStop> routeStops;

    @OneToMany(mappedBy = "busStop")
    private List<Schedule> schedules;
}

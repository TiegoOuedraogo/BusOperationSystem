package com.example.nycmta.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "bus_stop", schema = "nycmta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BusStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stop_id")
    private Long stopId;

    @Column(name = "stop_name", nullable = false)
    private String stopName;

    @Column(name = "location", nullable = false)
    private String location;

    @OneToMany(mappedBy = "busStop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RouteStop> routeStops;

    @OneToMany(mappedBy = "busStop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> schedules;
}

package com.example.nycmta.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "bus_route", schema = "nycmta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BusRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Long routeId;

    @Column(name = "route_name", nullable = false)
    private String routeName;

    @OneToMany(mappedBy = "busRoute", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RouteStop> routeStops;

    @OneToMany(mappedBy = "busRoute", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> schedules;
}


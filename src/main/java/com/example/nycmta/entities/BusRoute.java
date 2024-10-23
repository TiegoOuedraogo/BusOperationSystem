package com.example.nycmta.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "busRoute", schema = "nycmta")

public class BusRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;

    private String routeName;

    @OneToMany(mappedBy = "busRoute")
    private List<RouteStop> routeStops;

    @OneToMany(mappedBy = "busRoute")
    private List<Schedule> schedules;
}


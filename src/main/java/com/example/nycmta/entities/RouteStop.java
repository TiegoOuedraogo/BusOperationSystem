package com.example.nycmta.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "route_stop", schema = "nycmta")
public class RouteStop {

    @EmbeddedId
    private RouteStopId id;

    @ManyToOne
    @MapsId("routeId")
    @JoinColumn(name = "route_id")
    private BusRoute busRoute;

    @ManyToOne
    @JoinColumn(name = "stop_id")
    private BusStop busStop;


}


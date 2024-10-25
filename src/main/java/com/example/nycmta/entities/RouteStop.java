package com.example.nycmta.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "route_stop", schema = "nycmta")

public class RouteStop {
    @EmbeddedId
    private RouteStopId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("routeId")
    @JoinColumn(name = "route_id", nullable = false)
    private BusRoute busRoute;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("stopId")
    @JoinColumn(name = "stop_id", nullable = false)
    private BusStop busStop;

    @Column(name = "stop_order", nullable = false)
    private Integer stopOrder;
}


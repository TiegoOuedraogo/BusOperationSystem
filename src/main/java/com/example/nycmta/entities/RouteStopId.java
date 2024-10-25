package com.example.nycmta.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


import java.io.Serializable;
import java.util.Objects;
@Embeddable

public class RouteStopId implements Serializable {
    @Column(name = "route_id")
    private Long routeId;

    @Column(name = "stop_id")
    private Long stopId;

    public RouteStopId(Long routeId, Long stopId) {
        this.routeId = routeId;
        this.stopId = stopId;
    }

    public RouteStopId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteStopId that = (RouteStopId) o;
        return Objects.equals(routeId, that.routeId) && Objects.equals(stopId, that.stopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeId, stopId);
    }
}

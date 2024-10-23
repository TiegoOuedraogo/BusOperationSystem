package com.example.nycmta.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RouteStopId implements Serializable {

    private Long routeId;
    private Integer stopOrder;

    public RouteStopId() {}

    public RouteStopId(Long routeId, Integer stopOrder) {
        this.routeId = routeId;
        this.stopOrder = stopOrder;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Integer getStopOrder() {
        return stopOrder;
    }

    public void setStopOrder(Integer stopOrder) {
        this.stopOrder = stopOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteStopId that = (RouteStopId) o;

        if (!Objects.equals(routeId, that.routeId)) return false;
        return Objects.equals(stopOrder, that.stopOrder);
    }

    @Override
    public int hashCode() {
        int result = routeId != null ? routeId.hashCode() : 0;
        result = 31 * result + (stopOrder != null ? stopOrder.hashCode() : 0);
        return result;
    }
}

package com.example.nycmta.repository;

import com.example.nycmta.entities.BusRoute;
import com.example.nycmta.entities.RouteStop;
import com.example.nycmta.entities.RouteStopId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteStopRepository extends JpaRepository<RouteStop, RouteStopId> {

    void deleteAllByBusRoute(BusRoute busRoute);

    Optional<RouteStop> findById_RouteIdAndStopOrder(Long routeId, Integer stopOrder);
}


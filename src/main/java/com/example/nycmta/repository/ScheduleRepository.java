package com.example.nycmta.repository;

import com.example.nycmta.entities.Schedule;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // Custom query to get the next three buses at a stop using pageable for limit
    @Query("SELECT s FROM Schedule s WHERE s.busStop.stopId = :stopId AND s.departureTime > :currentTime ORDER BY s.departureTime ASC")
    List<Schedule> findNextThreeBusesAtStop(@Param("stopId") Long stopId, @Param("currentTime") LocalDateTime currentTime, Pageable pageable);

    // Custom query to find the next bus for a specific route after the current time
    @Query("SELECT s FROM Schedule s WHERE s.busRoute.routeId = :routeId AND s.departureTime > :currentTime ORDER BY s.departureTime ASC")
    Optional<Schedule> findNextBusForRoute(@Param("routeId") Long routeId, @Param("currentTime") LocalDateTime currentTime);

    // Custom query to find schedules between two stops
    @Query("SELECT s FROM Schedule s WHERE s.busRoute.routeId = :routeId AND s.busStop.stopId IN (:stopX, :stopY) ORDER BY s.departureTime ASC")
    List<Schedule> findSchedulesForStops(@Param("routeId") Long routeId, @Param("stopX") Long stopX, @Param("stopY") Long stopY);

    // Custom query to find all schedules ordered by route and departure time
    @Query("SELECT s FROM Schedule s ORDER BY s.busRoute.routeId, s.departureTime")
    List<Schedule> findAllSchedulesOrdered();

    List<Schedule> findTop3ByBusStop_StopIdAndDepartureTimeAfterOrderByDepartureTimeAsc(Long stopId, LocalDateTime currentTime);

    Optional<Schedule> findTop1ByBusRoute_RouteIdAndDepartureTimeAfterOrderByDepartureTimeAsc(Long routeId, LocalDateTime currentTime);

    Optional<Schedule> findTop1ByBusRoute_RouteIdAndBusStop_StopIdOrderByDepartureTimeAsc(Long routeId, Long stopX);

    Optional<Schedule> findTop1ByBusRoute_RouteIdAndBusStop_StopIdOrderByArrivalTimeAsc(Long routeId, Long stopY);

    List<Schedule> findAllByOrderByDepartureTimeAsc();
}




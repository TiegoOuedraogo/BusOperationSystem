package com.example.nycmta.service.serviceImpl;

import com.example.nycmta.dto.*;
import com.example.nycmta.entities.BusRoute;
import com.example.nycmta.entities.BusStop;
import com.example.nycmta.entities.RouteStop;
import com.example.nycmta.entities.RouteStopId;
import com.example.nycmta.repository.BusRouteRepository;
import com.example.nycmta.repository.BusStopRepository;
import com.example.nycmta.repository.RouteStopRepository;
import com.example.nycmta.service.ResourceNotFoundException;
import com.example.nycmta.service.RouteStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteStopServiceImpl implements RouteStopService {

    private final RouteStopRepository routeStopRepository;
    private final BusRouteRepository busRouteRepository;
    private final BusStopRepository busStopRepository;

    @Autowired
    public RouteStopServiceImpl(RouteStopRepository routeStopRepository, BusRouteRepository busRouteRepository, BusStopRepository busStopRepository) {
        this.routeStopRepository = routeStopRepository;
        this.busRouteRepository = busRouteRepository;
        this.busStopRepository = busStopRepository;
    }

    @Transactional
    @Override
    public RouteStopResponseDto createRouteStop(RouteStopRequestDto requestDto) {
        BusRoute busRoute = busRouteRepository.findById(requestDto.getRouteId())
                .orElseThrow(() -> new ResourceNotFoundException("BusRoute", "routeId", requestDto.getRouteId()));
        BusStop busStop = busStopRepository.findById(requestDto.getStopId())
                .orElseThrow(() -> new ResourceNotFoundException("BusStop", "stopId", requestDto.getStopId()));

        RouteStopId routeStopId = new RouteStopId(busRoute.getRouteId(), busStop.getStopId());
        RouteStop routeStop = RouteStop.builder()
                .id(routeStopId)
                .busRoute(busRoute)
                .busStop(busStop)
                .stopOrder(requestDto.getStopOrder())
                .build();

        RouteStop savedRouteStop = routeStopRepository.save(routeStop);
        return mapToRouteStopResponseDto(savedRouteStop);
    }

    @Override
    public RouteStopResponseDto getRouteStopById(Long routeId, Long stopId) {
        RouteStop routeStop = findRouteStop(routeId, stopId);
        return mapToRouteStopResponseDto(routeStop);
    }

    @Override
    public RouteStopResponseDto getRouteStopByOrder(Long routeId, Integer stopOrder) {
        RouteStop routeStop = routeStopRepository.findById_RouteIdAndStopOrder(routeId, stopOrder)
                .orElseThrow(() -> new ResourceNotFoundException("RouteStop", "routeId and stopOrder", routeId + " and " + stopOrder));
        return mapToRouteStopResponseDto(routeStop);
    }

    @Override
    public List<RouteStopResponseDto> getAllRouteStops() {
        List<RouteStop> routeStops = routeStopRepository.findAll();
        return routeStops.stream()
                .map(this::mapToRouteStopResponseDto)
                .collect(Collectors.toList());
    }



    @Transactional
    @Override
    public RouteStopResponseDto updateRouteStop(Long routeId, Long stopId, RouteStopRequestDto requestDto) {
        RouteStop routeStop = findRouteStop(routeId, stopId);
        routeStop.setStopOrder(requestDto.getStopOrder());

        RouteStop updatedRouteStop = routeStopRepository.save(routeStop);
        return mapToRouteStopResponseDto(updatedRouteStop);
    }

    @Transactional
    @Override
    public void deleteRouteStop(Long routeId, Long stopId) {
        RouteStop routeStop = findRouteStop(routeId, stopId);
        routeStopRepository.delete(routeStop);
    }

    // Helper method to find RouteStop by RouteStopId
    private RouteStop findRouteStop(Long routeId, Long stopId) {
        RouteStopId routeStopId = new RouteStopId(routeId, stopId);
        return routeStopRepository.findById(routeStopId)
                .orElseThrow(() -> new ResourceNotFoundException("RouteStop", "id", routeStopId));
    }

    // Mapping methods
    private RouteStopResponseDto mapToRouteStopResponseDto(RouteStop routeStop) {
        return RouteStopResponseDto.builder()
                .routeStopId(routeStop.getBusStop().getStopId())
                .stopOrder(routeStop.getStopOrder())
                .busRoute(mapToBusRouteDto(routeStop.getBusRoute()))
                .busStop(mapToBusStopDto(routeStop.getBusStop()))
                .build();
    }

    private BusRouteDto mapToBusRouteDto(BusRoute busRoute) {
        return BusRouteDto.builder()
                .routeId(busRoute.getRouteId())
                .routeName(busRoute.getRouteName())
                .build();
    }

    private BusStopDto mapToBusStopDto(BusStop busStop) {
        return BusStopDto.builder()
                .stopId(busStop.getStopId())
                .stopName(busStop.getStopName())
                .location(busStop.getLocation())
                .build();
    }
}


package com.example.nycmta.service.serviceImpl;

import com.example.nycmta.dto.*;
import com.example.nycmta.entities.*;
import com.example.nycmta.repository.BusRouteRepository;
import com.example.nycmta.repository.BusStopRepository;
import com.example.nycmta.repository.RouteStopRepository;
import com.example.nycmta.service.BusRouteService;
import com.example.nycmta.service.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusRouteServiceImpl implements BusRouteService {

    private final BusRouteRepository busRouteRepository;
    private final RouteStopRepository routeStopRepository;
    private final BusStopRepository busStopRepository;

    @Autowired
    public BusRouteServiceImpl(BusRouteRepository busRouteRepository, RouteStopRepository routeStopRepository,
                               BusStopRepository busStopRepository) {
        this.busRouteRepository = busRouteRepository;
        this.routeStopRepository = routeStopRepository;
        this.busStopRepository = busStopRepository;
    }

    @Transactional
    @Override
    public BusRouteResponseDto createBusRoute(BusRouteRequestDto requestDto) {
        BusRoute busRoute = new BusRoute();
        busRoute.setRouteName(requestDto.getRouteName());

        BusRoute savedRoute = busRouteRepository.save(busRoute);

        processAndSaveRouteStops(savedRoute, requestDto.getStops());

        return mapToBusRouteResponseDto(savedRoute);
    }

    @Override
    public BusRouteResponseDto getBusRouteById(Long routeId) {
        BusRoute busRoute = busRouteRepository.findById(routeId)
                .orElseThrow(() -> new ResourceNotFoundException("BusRoute", "routeId", routeId));

        return mapToBusRouteResponseDto(busRoute);
    }

    @Override
    public List<BusRouteResponseDto> getAllRoutes() {
        List<BusRoute> routes = busRouteRepository.findAll();
        return routes.stream()
                .map(this::mapToBusRouteResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public BusRouteResponseDto updateBusRoute(Long routeId, BusRouteRequestDto requestDto) {
        BusRoute busRoute = busRouteRepository.findById(routeId)
                .orElseThrow(() -> new ResourceNotFoundException("BusRoute", "routeId", routeId));

        busRoute.setRouteName(requestDto.getRouteName());

        routeStopRepository.deleteAllByBusRoute(busRoute);
        processAndSaveRouteStops(busRoute, requestDto.getStops());

        BusRoute updatedRoute = busRouteRepository.save(busRoute);
        return mapToBusRouteResponseDto(updatedRoute);
    }

    private void processAndSaveRouteStops(BusRoute busRoute, List<RouteStopRequestDto> stopRequestDtos) {
        List<RouteStop> routeStops = stopRequestDtos.stream()
                .map(stopDto -> {
                    BusStop busStop = busStopRepository.findById(stopDto.getStopId())
                            .orElseThrow(() -> new ResourceNotFoundException("BusStop", "stopId", stopDto.getStopId()));

                    RouteStop routeStop = new RouteStop();
                    RouteStopId routeStopId = new RouteStopId(busRoute.getRouteId(), stopDto.getStopId());
                    routeStop.setId(routeStopId);
                    routeStop.setBusRoute(busRoute);
                    routeStop.setBusStop(busStop);
                    routeStop.setStopOrder(stopDto.getStopOrder());

                    return routeStop;
                }).collect(Collectors.toList());

        routeStopRepository.saveAll(routeStops);
    }

    private BusRouteResponseDto mapToBusRouteResponseDto(BusRoute busRoute) {
        return BusRouteResponseDto.builder()
                .routeId(busRoute.getRouteId())
                .routeName(busRoute.getRouteName())
                .stops(busRoute.getRouteStops() != null ? busRoute.getRouteStops().stream()
                        .map(this::mapToRouteStopRequestDto)
                        .collect(Collectors.toList()) : List.of())
                .build();
    }

    private RouteStopRequestDto mapToRouteStopRequestDto(RouteStop routeStop) {
        return RouteStopRequestDto.builder()
                .stopId(routeStop.getBusStop().getStopId())
                .routeId(routeStop.getBusRoute().getRouteId())
                .stopOrder(routeStop.getStopOrder())
                .build();
    }
}

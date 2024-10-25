package com.example.nycmta.service;

import com.example.nycmta.dto.RouteStopRequestDto;
import com.example.nycmta.dto.RouteStopResponseDto;
import com.example.nycmta.entities.RouteStop;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public interface RouteStopService {

    RouteStopResponseDto createRouteStop(RouteStopRequestDto routeStopRequestDto);

    RouteStopResponseDto getRouteStopById(Long routeId, Long stopId);

    RouteStopResponseDto getRouteStopByOrder(Long routeId, Integer stopOrder);

    List<RouteStopResponseDto> getAllRouteStops();

    @Transactional
    RouteStopResponseDto updateRouteStop(Long routeId, Long stopId, RouteStopRequestDto requestDto);

    @Transactional
    void deleteRouteStop(Long routeId, Long stopId);
}


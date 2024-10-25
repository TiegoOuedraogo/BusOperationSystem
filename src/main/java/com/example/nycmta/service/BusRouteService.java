package com.example.nycmta.service;

import com.example.nycmta.dto.BusResponseDto;
import com.example.nycmta.dto.BusRouteRequestDto;
import com.example.nycmta.dto.BusRouteResponseDto;

import java.util.List;

public interface BusRouteService {
    BusRouteResponseDto createBusRoute(BusRouteRequestDto requestDto);

    BusRouteResponseDto getBusRouteById(Long routeId);

    List<BusRouteResponseDto> getAllRoutes();

    BusRouteResponseDto updateBusRoute(Long routeId, BusRouteRequestDto requestDto);

//    void deleteBusRoute(Long id);
}

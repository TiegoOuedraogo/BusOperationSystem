package com.example.nycmta.service;

import com.example.nycmta.dto.BusStopRequestDto;
import com.example.nycmta.dto.BusStopResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BusStopService {
    BusStopResponseDto createBusStop(BusStopRequestDto busStopRequestDto);

    BusStopResponseDto getBusStopById(Long stopId);

    List<BusStopResponseDto> getAllBusStops();

    @Transactional
    BusStopResponseDto updateBusStop(Long stopId, BusStopRequestDto busStopRequestDto);

    @Transactional
    void deleteBusStop(Long stopId);
}

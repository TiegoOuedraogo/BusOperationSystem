package com.example.nycmta.service;

import com.example.nycmta.dto.BusRequestDto;
import com.example.nycmta.dto.BusResponseDto;

import java.util.List;

public interface BusService {
    BusResponseDto createBus(BusRequestDto busRequestDto);
    BusResponseDto getBusById(Long busId);
    List<BusResponseDto> getAllBuses();
    BusResponseDto updateBus(Long busId, BusRequestDto busRequestDto);
    void deleteBus(Long busId);
}

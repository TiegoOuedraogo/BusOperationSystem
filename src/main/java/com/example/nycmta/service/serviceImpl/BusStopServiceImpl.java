package com.example.nycmta.service.serviceImpl;

import com.example.nycmta.dto.*;
import com.example.nycmta.entities.BusStop;
import com.example.nycmta.repository.BusStopRepository;
import com.example.nycmta.service.BusStopService;
import com.example.nycmta.service.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusStopServiceImpl implements BusStopService {

    private final BusStopRepository busStopRepository;

    @Autowired
    public BusStopServiceImpl(BusStopRepository busStopRepository) {
        this.busStopRepository = busStopRepository;
    }

    @Override
    @Transactional
    public BusStopResponseDto createBusStop(BusStopRequestDto busStopRequestDto) {
        BusStop busStop = new BusStop();
        busStop.setStopName(busStopRequestDto.getStopName());
        busStop.setLocation(busStopRequestDto.getLocation());
        BusStop savedStop = busStopRepository.save(busStop);

        return mapToBusStopResponseDto(savedStop);
    }

    @Override
    public BusStopResponseDto getBusStopById(Long stopId) {
        BusStop busStop = busStopRepository.findById(stopId)
                .orElseThrow(() -> new ResourceNotFoundException("BusStop", "stopId", stopId));
        return mapToBusStopResponseDto(busStop);
    }

    @Override
    public List<BusStopResponseDto> getAllBusStops() {
        List<BusStop> busStops = busStopRepository.findAll();
        return busStops.stream()
                .map(this::mapToBusStopResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public BusStopResponseDto updateBusStop(Long stopId, BusStopRequestDto busStopRequestDto) {
        BusStop busStop = busStopRepository.findById(stopId)
                .orElseThrow(() -> new ResourceNotFoundException("BusStop", "stopId", stopId));
        busStop.setStopName(busStopRequestDto.getStopName());
        busStop.setLocation(busStopRequestDto.getLocation());
        BusStop updatedStop = busStopRepository.save(busStop);

        return mapToBusStopResponseDto(updatedStop);
    }

    @Transactional
    @Override
    public void deleteBusStop(Long stopId) {
        BusStop busStop = busStopRepository.findById(stopId)
                .orElseThrow(() -> new ResourceNotFoundException("BusStop", "stopId", stopId));

        busStopRepository.delete(busStop);
    }

    private BusStopResponseDto mapToBusStopResponseDto(BusStop busStop) {
        return BusStopResponseDto.builder()
                .stopId(busStop.getStopId())
                .stopName(busStop.getStopName())
                .location(busStop.getLocation())
                .build();
    }
}

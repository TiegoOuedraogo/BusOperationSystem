package com.example.nycmta.service;

import com.example.nycmta.dto.BusRequestDto;
import com.example.nycmta.dto.BusResponseDto;
import com.example.nycmta.entities.Bus;
import com.example.nycmta.repository.BusRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;

@Service
public class BusServiceImpl implements BusService {
    private static final Logger logger = LoggerFactory.getLogger(BusServiceImpl.class);


    private final BusRepository busRepository;

    @Autowired
    public BusServiceImpl(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Override
    public BusResponseDto createBus(BusRequestDto busRequestDto) {
        logger.info("Creating bus with number: {}", busRequestDto.getBusNumber());
        Bus bus = mapToBus(busRequestDto);
        Bus savedBus = busRepository.save(bus);
        return mapToBusResponseDto(savedBus);
    }

    @Override
    public BusResponseDto getBusById(Long busId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus", "busId", busId));
        return mapToBusResponseDto(bus);
    }

    @Override
    public List<BusResponseDto> getAllBuses() {
        List<Bus> buses = busRepository.findAll();
        return buses.stream()
                .map(this::mapToBusResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public BusResponseDto updateBus(Long busId, BusRequestDto busRequestDto) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus", "busId", busId));
        bus.setBusNumber(busRequestDto.getBusNumber());
        bus.setCapacity(busRequestDto.getCapacity());
        Bus updatedBus = busRepository.save(bus);
        return mapToBusResponseDto(updatedBus);
    }

    @Override
    public void deleteBus(Long busId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus", "busId", busId));
        busRepository.delete(bus);
    }

    // Mapping methods
    private BusResponseDto mapToBusResponseDto(Bus bus) {
        return new BusResponseDto(
                bus.getBusId(),
                bus.getBusNumber(),
                bus.getCapacity()
        );
    }

    private Bus mapToBus(BusRequestDto busRequestDto) {
        return Bus.builder()
                .busNumber(busRequestDto.getBusNumber())
                .capacity(busRequestDto.getCapacity())
                .build();
    }
}

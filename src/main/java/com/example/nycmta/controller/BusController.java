package com.example.nycmta.controller;

import com.example.nycmta.dto.BusRequestDto;
import com.example.nycmta.dto.BusResponseDto;
import com.example.nycmta.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    private final BusService busService;

    @Autowired
    public BusController(BusService busService) {
        this.busService = busService;
    }

    @PostMapping
    public BusResponseDto createBus(@RequestBody BusRequestDto busRequestDto) {
        return busService.createBus(busRequestDto);
    }

    @GetMapping("/{id}")
    public BusResponseDto getBusById(@PathVariable Long id) {
        return busService.getBusById(id);
    }

    @GetMapping
    public List<BusResponseDto> getAllBuses() {
        return busService.getAllBuses();
    }

    @PutMapping("/{id}")
    public BusResponseDto updateBus(@PathVariable Long id, @RequestBody BusRequestDto busRequestDto) {
        return busService.updateBus(id, busRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBus(@PathVariable Long id) {
        busService.deleteBus(id);
    }
}

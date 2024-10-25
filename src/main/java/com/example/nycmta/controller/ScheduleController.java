package com.example.nycmta.controller;

import com.example.nycmta.dto.ScheduleRequestDto;
import com.example.nycmta.dto.ScheduleResponseDto;
import com.example.nycmta.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        ScheduleResponseDto createdSchedule = scheduleService.createSchedule(scheduleRequestDto);
        return new ResponseEntity<>(createdSchedule, HttpStatus.CREATED);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> getScheduleById(@PathVariable Long scheduleId) {
        ScheduleResponseDto scheduleResponse = scheduleService.getScheduleById(scheduleId);
        return new ResponseEntity<>(scheduleResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules() {
        List<ScheduleResponseDto> schedules = scheduleService.getAllSchedules();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long scheduleId,
                                                              @RequestBody ScheduleRequestDto scheduleRequestDto) {
        ScheduleResponseDto updatedSchedule = scheduleService.updateSchedule(scheduleId, scheduleRequestDto);
        return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


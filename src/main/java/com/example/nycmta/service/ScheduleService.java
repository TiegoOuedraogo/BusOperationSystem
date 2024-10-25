package com.example.nycmta.service;

import com.example.nycmta.dto.ScheduleRequestDto;
import com.example.nycmta.dto.ScheduleResponseDto;
import com.example.nycmta.entities.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface ScheduleService {

    ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto);

    ScheduleResponseDto getScheduleById(Long scheduleId);

    List<ScheduleResponseDto> getAllSchedules();

    ScheduleResponseDto updateSchedule(Long scheduleId, ScheduleRequestDto scheduleRequestDto);

    void deleteSchedule(Long scheduleId);

    List<ScheduleResponseDto> findNextThreeBusesAtStop(Long stopId, LocalDateTime currentTime);

    ScheduleResponseDto findNextBusForRoute(Long routeId, LocalDateTime currentTime);

    Duration calculateTravelTime(Long routeId, Long stopX, Long stopY);

    List<ScheduleResponseDto> findAllSchedulesOrdered();
}


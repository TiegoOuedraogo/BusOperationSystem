package com.example.nycmta.repository;

import com.example.nycmta.dto.BusRequestDto;
import com.example.nycmta.dto.BusResponseDto;
import com.example.nycmta.entities.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
}

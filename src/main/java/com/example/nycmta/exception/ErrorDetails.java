package com.example.nycmta.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetails {
    private int statusCode;
    private String message;
}

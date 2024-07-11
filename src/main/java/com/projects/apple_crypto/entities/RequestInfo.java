package com.projects.apple_crypto.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestInfo {
    private LocalDateTime timestamp;
    private String uri;
    private int status;
    private long duration;
}

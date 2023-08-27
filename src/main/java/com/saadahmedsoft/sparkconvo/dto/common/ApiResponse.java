package com.saadahmedsoft.sparkconvo.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private boolean status;
    private String message;
}

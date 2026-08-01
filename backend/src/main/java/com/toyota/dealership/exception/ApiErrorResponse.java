package com.toyota.dealership.exception;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;

public record ApiErrorResponse(
        int status,
        String error,
        String message,
        Map<String, List<String>> validationErrors,
        LocalDateTime timestamp
) {
}
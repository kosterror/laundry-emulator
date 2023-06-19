package ru.tsu.hits.kosterror.laundryemulator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
public class ApiError {

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss:SSS")
    private LocalDateTime timestamp;

    private int code;

    private String message;

    private Map<String, String> validationMessages;

    public ApiError(int code, String message, Map<String, String> validationMessages) {
        this.code = code;
        this.message = message;
        this.validationMessages = validationMessages;
    }

    public ApiError(int code, String message) {
        this.code = code;
        this.message = message;
    }

}

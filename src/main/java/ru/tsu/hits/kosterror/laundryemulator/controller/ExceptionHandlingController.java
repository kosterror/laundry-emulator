package ru.tsu.hits.kosterror.laundryemulator.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.tsu.hits.kosterror.laundryemulator.dto.ApiError;
import ru.tsu.hits.kosterror.laundryemulator.exception.ConflictException;
import ru.tsu.hits.kosterror.laundryemulator.exception.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleInvalidRequestBody(HttpServletRequest request,
                                                             MethodArgumentNotValidException exception
    ) {
        logError(request, exception);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Map<String, String> messages = new HashMap<>();

        exception
                .getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();

                    if (message != null) {
                        messages.put(fieldName, message);
                    }

                });

        ApiError error = new ApiError(
                httpStatus.value(),
                "Тело запроса не прошло валидацию",
                messages
        );

        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(HttpServletRequest request,
                                                            NotFoundException exception
    ) {
        logError(request, exception);
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ApiError error = new ApiError(
                httpStatus.value(),
                exception.getMessage()
        );

        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> handleConflictException(HttpServletRequest request,
                                                            ConflictException exception
    ) {
        logError(request, exception);
        HttpStatus httpStatus = HttpStatus.CONFLICT;

        ApiError error = new ApiError(
                httpStatus.value(),
                exception.getMessage()
        );

        return new ResponseEntity<>(error, httpStatus);
    }


    private void logError(HttpServletRequest request, Exception exception) {
        log.error("Возникла ошибка при запросе: {} {}.",
                request.getMethod(),
                request.getRequestURL(),
                exception
        );
    }

}

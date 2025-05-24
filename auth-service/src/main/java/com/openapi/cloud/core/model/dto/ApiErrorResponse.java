package com.openapi.cloud.core.model.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private String errorCode; // for i18n or internal codes

    public static ApiErrorResponse of(HttpStatus status, String message, String path) {
        return ApiErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(path)
                .build();
    }

//    public ApiErrorResponse(int status, String error, String message, String path) {
//        this.timestamp = LocalDateTime.now();
//        this.status = status;
//        this.error = error;
//        this.message = message;
//        this.path = path;
//    }
}

package com.example.acid.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
// import com.example.acid.web.component.MessageResourceHolder;
import com.example.acid.web.constants.ApiErrorCode;
import lombok.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ApiResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -1674709247477918772L;

    private boolean flag;

    private T data; //NOSONAR

    private ApiError error;

    public ApiResponse() {
        this.error = ApiError.builder().build();
    }

    @JsonProperty("hasData")
    public boolean hasData() {
        if (data instanceof Collection<?>) {
            return CollectionUtils.isNotEmpty((Collection<?>) data);
        } else if (data instanceof Map<?, ?>) {
            return MapUtils.isNotEmpty((Map<?, ?>) data);
        } else {
            return data != null;
        }
    }

    @JsonProperty("hasError")
    public boolean hasError() {
        return this.error != null && this.error.hasError();
    }


    /**
     * ApiError for error message
     * errorCode
     * message
     * errors
     * errorsAsList
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    @ToString
    public static final class ApiError implements Serializable {

        @Serial
        private static final long serialVersionUID = 3687524794654930688L;

        private ApiErrorCode errorCode;
        private String message;

        @Builder.Default
        private Map<String, ErrorDetail> errors = Maps.newHashMap();

        public Collection<ErrorDetail> getErrorsAsList() {
            return errors != null ? errors.values() : Collections.emptyList();
        }

        public boolean hasError() {
            return this.errorCode != null;
        }

        public String getMessage() {
            if (StringUtils.isNotBlank(this.message)) {
                return this.message;
            }
//            if (this.errorCode != null) {
//                //return MessageResourceHolder.get().getLabel(this.errorCode.getLabel());
//                return "";
//            }
            return null;
        }

        @AllArgsConstructor
        @NoArgsConstructor
        @Getter
        @Setter
        @Builder
        @ToString
        public static final class ErrorDetail implements Serializable {

            @Serial
            private static final long serialVersionUID = 5278702444419535244L;
            private String code;
            private String message;
        }
    }
}
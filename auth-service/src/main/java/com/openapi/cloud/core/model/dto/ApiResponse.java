package com.openapi.cloud.core.model.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
//import com.google.common.collect.Maps;

//import io.crm.app.core.constant.ApiErrorCode;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = -1674709247477918772L;

    private boolean flag;

    private T data;

    private ApiError error;

    public ApiResponse() {
        this.error = ApiError.builder().build();
    }

    @JsonProperty("hasData")
    public boolean hasData() {
        if (data instanceof Collection<?>) {
            //return CollectionUtils.isNotEmpty((Collection<?>) data);
            // return data != null && !((Collection<?>) data).isEmpty();
            return !((Collection<?>) data).isEmpty();

        } else if (data instanceof Map<?, ?>) {
            return !((Map<?, ?>) data).isEmpty();
            //  return data != null && !((Map<?, ?>) data).isEmpty();
            // return MapUtils.isNotEmpty((Map<?, ?>) data);
        } else {
            return data != null;
        }
    }

    @JsonProperty("hasError")
    public boolean hasError() {
        return this.error != null && this.error.hasError();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    @ToString
    public static final class ApiError implements Serializable {

        private static final long serialVersionUID = 3687524794654930688L;

      //  private ApiErrorCode code;

        private String code;

        private String message;

//        @Builder.Default
//        private Map<String, ErrorDetail> errors = Maps.newHashMap();

        @Builder.Default
        private Map<String, ErrorDetail> errors = new HashMap<>();


        public Collection<ErrorDetail> getErrorsAsList() {
            return errors != null ? errors.values() : Collections.emptyList();
        }

        public boolean hasError() {
            return this.code != null;
        }

        public String getMessage() {
            if (StringUtils.isNotBlank(this.message))
                return this.message;
            if (this.code != null) {
                return "sample message";
                //  return this.code.getLabel();
//				return Optional.ofNullable(this.code.getLabel()).orElse("Null Value");
            }

            return null;
        }

        @AllArgsConstructor
        @NoArgsConstructor
        @Getter
        @Setter
        @Builder
        @ToString
        public static final class ErrorDetail implements Serializable {

            private static final long serialVersionUID = 5278702444419535244L;

            private String code;

            private String message;
        }
    }
}
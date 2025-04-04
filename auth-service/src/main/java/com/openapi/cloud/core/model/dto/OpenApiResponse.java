package com.openapi.cloud.core.model.dto;

//import com.aig.openapi.model.CustomerAddress;
import com.openapi.cloud.core.model.enums.OpenApiStatusEnum;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpenApiResponse {

    private Integer customerId;

    private String name;

    private LocalDate dateOfBirth;

    private OpenApiStatusEnum statusType;

    private OpenApiAddress address;

}

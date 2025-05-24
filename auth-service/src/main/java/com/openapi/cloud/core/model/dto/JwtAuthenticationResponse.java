package com.openapi.cloud.core.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1200160788658364366L;
    private String accessToken;
    private String tokenType; // = "Bearer";
    private String name;
    private String email;
    private List<String> claims;

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}

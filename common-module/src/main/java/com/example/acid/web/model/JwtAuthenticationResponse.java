package com.example.acid.web.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1200160788658364366L;
    private String accessToken;
    private String tokenType;
    private String name;
    private String email;
    private List<String> claims;

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}

package com.example.security.controller;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AuntheticationResponse {
    private String authenticationToken;

}

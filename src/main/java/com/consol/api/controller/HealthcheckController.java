package com.consol.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthcheck")
@RequiredArgsConstructor
public class HealthcheckController {

    @GetMapping
    public ResponseEntity<Void> getHealth() {
        return ResponseEntity.ok().build();
    }
}
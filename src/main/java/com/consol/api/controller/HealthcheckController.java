package com.consol.api.controller;

@RestController
@RequestMapping("/healthcheck")
@RequiredArgsConstructor
public class FamiliaController {

    private final FamiliaService service;

    @GetMapping
    public ResponseEntity<Void> getHealth() {
        return ResponseEntity.ok().build();
    }
package com.consol.api.controller;

import com.consol.api.service.DoacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doacoes")
@RequiredArgsConstructor
public class DoacaoController {

    private final DoacaoService service;
}

package com.safezone.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapaController {

    @GetMapping("/map")
    public String mostrarMapa() {
        return "map";
    }
}
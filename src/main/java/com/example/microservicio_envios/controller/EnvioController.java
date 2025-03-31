package com.example.microservicio_envios.controller;

import com.example.microservicio_envios.model.Envio;
import com.example.microservicio_envios.service.EnvioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/envios")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @GetMapping
    public List<Envio> obtenerTodos() {
        return envioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Envio obtenerPorId(@PathVariable int id) {
        return envioService.obtenerPorId(id);
    }

    @GetMapping("/{id}/ubicacion")
    public String consultarUbicacion(@PathVariable int id) {
        return envioService.obtenerUbicacionPorId(id);
    }
}
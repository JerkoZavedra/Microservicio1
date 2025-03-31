package com.example.microservicio_envios.service;

import com.example.microservicio_envios.model.Envio;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EnvioService {
    private List<Envio> envios = new ArrayList<>(List.of(
        new Envio(1, "JDE643", "Claudio Pérez", "En tránsito", "Lima, Perú"),
        new Envio(2, "JDE782", "Josefa López", "Entregado", "Madrid, España"),
        new Envio(3, "JDE823", "Carlos Gómez", "En tránsito", "Cordoba, Argentina")
    ));

    public List<Envio> obtenerTodos() {
        return envios;
    }

    public Envio obtenerPorId(int id) {
        return envios.stream()
            .filter(e -> e.getId() == id)
            .findFirst()
            .orElse(null);
    }

    public String obtenerUbicacionPorId(int id) {
        return envios.stream()
            .filter(e -> e.getId() == id)
            .map(Envio::getUbicacion)
            .findFirst()
            .orElse("Envio no encontrado");
    }
}
package com.example.microservicio_envios.service;

import com.example.microservicio_envios.exception.EnvioNotFoundException;
import com.example.microservicio_envios.model.Envio;
import com.example.microservicio_envios.repository.EnvioRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EnvioService {

    @Autowired
    private EnvioRepository repository;

    public List<Envio> obtenerTodos() {
        log.debug("Servicio: obtenerTodos()");
        return repository.findAll(Sort.by("id").ascending());
    }

    public Envio obtenerPorId(Long id) {
        log.debug("Servicio: obtenerPorId({})", id);
        return repository.findById(id)
                .orElseThrow(() -> new EnvioNotFoundException(id));
    }

    public Envio guardar(Envio envio) {
        log.debug("Servicio: guardar({})", envio.getNumeroEnvio());

        if (repository.existsById(envio.getId())) {
            log.error("Ya existe un envío con ID {}", envio.getId());
            throw new IllegalArgumentException("Ya existe un envío con ID " + envio.getId());
        }

        return repository.save(envio);
    }

    public Envio actualizar(Long id, Envio datosActualizados) {
        log.debug("Servicio: actualizar({}, {})", id, datosActualizados.getNumeroEnvio());

        Envio existente = repository.findById(id)
                .orElseThrow(() -> new EnvioNotFoundException(id));

        existente.setNumeroEnvio(datosActualizados.getNumeroEnvio());
        existente.setDestinatario(datosActualizados.getDestinatario());
        existente.setEstado(datosActualizados.getEstado());
        existente.setUbicacion(datosActualizados.getUbicacion());

        return repository.save(existente);
    }

    public void eliminar(Long id) {
        log.debug("Servicio: eliminar({})", id);

        Envio existente = repository.findById(id)
                .orElseThrow(() -> new EnvioNotFoundException(id));

        repository.delete(existente);
    }
}


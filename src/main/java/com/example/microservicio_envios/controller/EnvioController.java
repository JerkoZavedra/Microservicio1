package com.example.microservicio_envios.controller;

import com.example.microservicio_envios.model.Envio;
import com.example.microservicio_envios.model.ResponseWrapper;
import com.example.microservicio_envios.service.EnvioService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/envios")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        log.info("GET /envios - Obteniendo todos los envíos");

        List<Envio> envios = envioService.obtenerTodos();

        if (envios.isEmpty()) {
            log.warn("No hay envíos registrados actualmente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay envíos registrados actualmente");
        }

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "OK",
                        envios.size(),
                        envios));
    }

    @GetMapping("/{id}")
    public Envio obtenerPorId(@PathVariable Long id) {
        log.info("GET /envios/{} - Buscando envío por ID", id);
        return envioService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Envio>> crearEnvio(@Valid @RequestBody Envio envio) {
        log.info("POST /envios - Creando envío: {}", envio.getNumeroEnvio());
        Envio creado = envioService.guardar(envio);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(
                        "Envío creado exitosamente",
                        1,
                        List.of(creado)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Envio>> actualizarEnvio(@PathVariable Long id,
            @Valid @RequestBody Envio envioActualizado) {
        log.info("PUT /envios/{} - Actualizando envío", id);

        Envio actualizado = envioService.actualizar(id, envioActualizado);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Envío actualizado exitosamente",
                        1,
                        List.of(actualizado)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarEnvio(@PathVariable Long id) {
        log.warn("DELETE /envios/{} - Eliminando envío", id);

        envioService.eliminar(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Envío eliminado exitosamente",
                        0,
                        null));
    }
}

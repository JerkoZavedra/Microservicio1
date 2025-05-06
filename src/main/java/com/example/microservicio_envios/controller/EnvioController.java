package com.example.microservicio_envios.controller;

import com.example.microservicio_envios.model.Envio;
import com.example.microservicio_envios.model.ResponseWrapper;
import com.example.microservicio_envios.service.EnvioService;
import com.example.microservicio_envios.hateoas.EnvioModelAssembler;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/envios")
public class EnvioController {

    private final EnvioService envioService;
    private final EnvioModelAssembler assembler;

    public EnvioController(EnvioService envioService, EnvioModelAssembler assembler) {
        this.envioService = envioService;
        this.assembler = assembler;
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

        List<EntityModel<Envio>> enviosModel = envios.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "OK",
                        enviosModel.size(),
                        enviosModel));
    }

    @GetMapping("/{id}")
    public EntityModel<Envio> obtenerPorId(@PathVariable Long id) {
        log.info("GET /envios/{} - Buscando envío por ID", id);
        Envio envio = envioService.obtenerPorId(id);
        return assembler.toModel(envio);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<EntityModel<Envio>>> crearEnvio(@Valid @RequestBody Envio envio) {
        log.info("POST /envios - Creando envío: {}", envio.getNumeroEnvio());
        Envio creado = envioService.guardar(envio);
        EntityModel<Envio> creadoModel = assembler.toModel(creado);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(
                        "Envío creado exitosamente",
                        1,
                        List.of(creadoModel)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<EntityModel<Envio>>> actualizarEnvio(@PathVariable Long id,
            @Valid @RequestBody Envio envioActualizado) {
        log.info("PUT /envios/{} - Actualizando envío", id);
        Envio actualizado = envioService.actualizar(id, envioActualizado);
        EntityModel<Envio> actualizadoModel = assembler.toModel(actualizado);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Envío actualizado exitosamente",
                        1,
                        List.of(actualizadoModel)));
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


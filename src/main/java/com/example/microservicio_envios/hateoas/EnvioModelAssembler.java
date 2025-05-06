package com.example.microservicio_envios.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.microservicio_envios.controller.EnvioController;
import com.example.microservicio_envios.model.Envio;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * Este ensamblador convierte una entidad Envio en un EntityModel<Envio>
 * con enlaces HATEOAS para enriquecer las respuestas REST.
 */
@Component
public class EnvioModelAssembler implements RepresentationModelAssembler<Envio, EntityModel<Envio>> {

    @Override
    public EntityModel<Envio> toModel(Envio envio) {
        return EntityModel.of(
                envio,

                // Enlace al detalle del envío (GET /envios/{id})
                linkTo(methodOn(EnvioController.class)
                        .obtenerPorId(envio.getId()))
                        .withSelfRel(),

                // Enlace para eliminar el envío (DELETE /envios/{id})
                linkTo(methodOn(EnvioController.class)
                        .eliminarEnvio(envio.getId()))
                        .withRel("delete"),

                // Enlace para actualizar el envío (PUT /envios/{id})
                linkTo(methodOn(EnvioController.class)
                        .actualizarEnvio(envio.getId(), null))
                        .withRel("update"),

                // Enlace para ver todos los envíos (GET /envios)
                linkTo(methodOn(EnvioController.class)
                        .obtenerTodos())
                        .withRel("all")
        );
    }
}


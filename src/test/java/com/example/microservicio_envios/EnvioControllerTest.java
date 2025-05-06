package com.example.microservicio_envios;

import com.example.microservicio_envios.controller.EnvioController;
import com.example.microservicio_envios.model.Envio;
import com.example.microservicio_envios.service.EnvioService;
import com.example.microservicio_envios.hateoas.EnvioModelAssembler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@WebMvcTest(EnvioController.class)
public class EnvioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnvioService envioService;

    @MockBean
    private EnvioModelAssembler envioAssembler;

    @Test
    @WithMockUser(username = "admin", password = "admin123", roles = { "ADMIN" })
    public void testObtenerPorId() throws Exception {
        // 📦 Creamos un envío simulado
        Envio envio = new Envio(1L, "ENV-001", "Juan Pérez", "En camino", "Centro de Distribución - Santiago");

        // 🔗 Simulamos los enlaces HATEOAS
        EntityModel<Envio> envioModel = EntityModel.of(envio,
                linkTo(methodOn(EnvioController.class).obtenerPorId(1L)).withSelfRel(),
                linkTo(methodOn(EnvioController.class).eliminarEnvio(1L)).withRel("delete"),
                linkTo(methodOn(EnvioController.class).actualizarEnvio(1L, null)).withRel("update"),
                linkTo(methodOn(EnvioController.class).obtenerTodos()).withRel("all"));

        when(envioService.obtenerPorId(1L)).thenReturn(envio);
        when(envioAssembler.toModel(envio)).thenReturn(envioModel);

        mockMvc.perform(get("/envios/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.numeroEnvio").value("ENV-001"))
                .andExpect(jsonPath("$.destinatario").value("Juan Pérez"))
                .andExpect(jsonPath("$.estado").value("En camino"))
                .andExpect(jsonPath("$.ubicacion").value("Centro de Distribución - Santiago"))
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._links.delete.href").exists())
                .andExpect(jsonPath("$._links.update.href").exists());
    }
}



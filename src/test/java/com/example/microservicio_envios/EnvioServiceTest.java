package com.example.microservicio_envios;

import com.example.microservicio_envios.exception.EnvioNotFoundException;
import com.example.microservicio_envios.model.Envio;
import com.example.microservicio_envios.repository.EnvioRepository;
import com.example.microservicio_envios.service.EnvioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.data.domain.Sort;

public class EnvioServiceTest {

    private EnvioRepository envioRepository;
    private EnvioService envioService;

    @BeforeEach
    public void setUp() {
        envioRepository = mock(EnvioRepository.class);
        envioService = new EnvioService(envioRepository);
    }

    @Test
    public void testObtenerTodos() {
        Envio e1 = new Envio(1L, "ENV123", "Juan Pérez", "En tránsito", "Centro de distribución");
        Envio e2 = new Envio(2L, "ENV456", "María Soto", "Entregado", "Domicilio del cliente");

        when(envioRepository.findAll(Sort.by("id").ascending())).thenReturn(Arrays.asList(e1, e2));

        List<Envio> resultado = envioService.obtenerTodos();

        assertEquals(2, resultado.size());
        assertEquals("ENV123", resultado.get(0).getNumeroEnvio());
    }

    @Test
    public void testObtenerPorId_existente() {
        Envio envio = new Envio(1L, "ENV789", "Carlos Díaz", "En bodega", "Sucursal norte");

        when(envioRepository.findById(1L)).thenReturn(Optional.of(envio));

        Envio resultado = envioService.obtenerPorId(1L);

        assertEquals("ENV789", resultado.getNumeroEnvio());
        assertEquals("Carlos Díaz", resultado.getDestinatario());
    }

    @Test
    public void testObtenerPorId_noExistente() {
        when(envioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EnvioNotFoundException.class, () -> {
            envioService.obtenerPorId(99L);
        });
    }
}


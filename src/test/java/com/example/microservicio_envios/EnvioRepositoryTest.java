package com.example.microservicio_envios;

import com.example.microservicio_envios.model.Envio;
import com.example.microservicio_envios.repository.EnvioRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EnvioRepositoryTest {

    @Autowired
    private EnvioRepository envioRepository;

    @Test
    public void testGuardarYBuscar() {
        // 📦 Creamos un envío de prueba
        Envio envio = new Envio(
                10L,
                "INTL123456",
                "Juan Pérez",
                "En tránsito",
                "Centro logístico de Madrid"
        );

        // 💾 Guardamos el envío en la base de datos embebida
        envioRepository.save(envio);

        // 🔍 Intentamos buscarlo por ID
        Optional<Envio> encontrado = envioRepository.findById(10L);

        // ✅ Verificamos que fue encontrado
        assertTrue(encontrado.isPresent());

        // 🧪 Verificamos que el número de envío coincida
        assertEquals("INTL123456", encontrado.get().getNumeroEnvio());
    }
}


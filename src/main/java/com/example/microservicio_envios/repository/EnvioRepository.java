package com.example.microservicio_envios.repository;

// -------------------- IMPORTACIONES -------------------------

// Importamos JPA Repository para que Spring genere el acceso a base de datos
import org.springframework.data.jpa.repository.JpaRepository;

// Importamos la entidad Envio
import com.example.microservicio_envios.model.Envio;

// -------------------- INTERFAZ -------------------------



public interface EnvioRepository extends JpaRepository<Envio, Long> {
    
}

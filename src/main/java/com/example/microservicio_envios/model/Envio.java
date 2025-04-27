package com.example.microservicio_envios.model;

// -------------------- IMPORTACIONES -------------------------

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;

/**
 * Clase que representa un Envío.
 */
@Data // Lombok: getters, setters, toString(), equals(), hashCode
@AllArgsConstructor // Lombok: constructor con todos los atributos
@NoArgsConstructor  // Lombok: constructor vacío
@Entity // 🔵 Entidad de base de datos
@Table(name = "envios") // 🔵 Tabla mapeada "envios"
public class Envio {

    @Id // 🔵 Primary Key
    @NotNull(message = "El ID no puede ser nulo")
    @Positive(message = "El ID debe ser un número positivo")
    private Long id;

    @NotBlank(message = "El número de envío no puede estar vacío")
    @Size(min = 1, max = 20, message = "El número de envío debe tener entre 1 y 20 caracteres")
    private String numeroEnvio;

    @NotBlank(message = "El destinatario no puede estar vacío")
    @Size(min = 3, max = 100, message = "El destinatario debe tener entre 3 y 100 caracteres")
    private String destinatario;

    @NotBlank(message = "El estado no puede estar vacío")
    @Size(min = 3, max = 50, message = "El estado debe tener entre 3 y 50 caracteres")
    private String estado;

    @NotBlank(message = "La ubicación no puede estar vacía")
    @Size(min = 3, max = 100, message = "La ubicación debe tener entre 3 y 100 caracteres")
    private String ubicacion;
}

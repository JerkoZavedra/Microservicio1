package com.example.microservicio_envios.model;

// -------------------- IMPORTACIONES -------------------------

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDateTime;
import java.util.List;

// -------------------- CLASE -------------------------


@JsonPropertyOrder({ "status", "cantidad", "timestamp", "data" }) 
                                                                  
public class ResponseWrapper<T> {

    // -------------------- ATRIBUTOS -------------------------

    // Indica si la operación fue exitosa u otro estado (por ejemplo: OK, ERROR)
    private String status;

    // Cantidad de elementos devueltos en la lista de datos
    private int cantidad;

    // Fecha y hora exacta en la que se genera la respuesta
    private LocalDateTime timestamp;

    // Lista de datos devueltos (películas u otros objetos)
    private List<T> data;

    // -------------------- CONSTRUCTOR -------------------------

 
    public ResponseWrapper(String status, int cantidad, List<T> data) {
        this.status = status;
        this.cantidad = cantidad;
        this.timestamp = LocalDateTime.now(); // Se asigna automáticamente al momento de crear la respuesta
        this.data = data;
    }

    // -------------------- GETTERS y SETTERS -------------------------

    // Permiten acceder y modificar los atributos si es necesario

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}


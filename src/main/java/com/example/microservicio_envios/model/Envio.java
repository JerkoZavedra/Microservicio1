package com.example.microservicio_envios.model;

public class Envio {
    private int id;
    private String numeroEnvio;
    private String destinatario;
    private String estado;
    private String ubicacion;

    public Envio(int id, String numeroEnvio, String destinatario, String estado, String ubicacion) {
        this.id = id;
        this.numeroEnvio = numeroEnvio;
        this.destinatario = destinatario;
        this.estado = estado;
        this.ubicacion = ubicacion;
    }

    public int getId() { return id; }
    public String getNumeroEnvio() { return numeroEnvio; }
    public String getDestinatario() { return destinatario; }
    public String getEstado() { return estado; }
    public String getUbicacion() { return ubicacion; }

    public void setId(int id) { this.id = id; }
    public void setNumeroEnvio(String numeroEnvio) { this.numeroEnvio = numeroEnvio; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
}
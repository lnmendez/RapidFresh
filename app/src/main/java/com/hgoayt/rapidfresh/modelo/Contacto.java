package com.hgoayt.rapidfresh.modelo;

public class Contacto {

    private String id_usuario;
    private String nombre_contacto;
    private String telefono;
    private String rut_usuario;

    public Contacto() {
    }

    public Contacto(String id_usuario, String nombre_contacto, String telefono, String rut_usuario) {
        this.id_usuario = id_usuario;
        this.nombre_contacto = nombre_contacto;
        this.telefono = telefono;
        this.rut_usuario = rut_usuario;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_contacto() {
        return nombre_contacto;
    }

    public void setNombre_contacto(String nombre_contacto) {
        this.nombre_contacto = nombre_contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRut_usuario() {
        return rut_usuario;
    }

    public void setRut_usuario(String rut_usuario) {
        this.rut_usuario = rut_usuario;
    }
}

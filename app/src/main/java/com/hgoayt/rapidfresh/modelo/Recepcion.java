package com.hgoayt.rapidfresh.modelo;


public class Recepcion {
    private String productor;
    private String pallet;
    private String nombre_ubicacion;
    private String nombre_calidad;
    private String tipoproducto;
    private String bandeja;
    private String nombre_producto;
    private String fecha_recepcion;

    public Recepcion(String productor, String pallet, String nombre_ubicacion, String nombre_calidad, String tipoproducto, String bandeja, String nombre_producto, String fecha_recepcion) {
        this.productor = productor;
        this.pallet = pallet;
        this.nombre_ubicacion = nombre_ubicacion;
        this.nombre_calidad = nombre_calidad;
        this.tipoproducto = tipoproducto;
        this.bandeja = bandeja;
        this.nombre_producto = nombre_producto;
        this.fecha_recepcion = fecha_recepcion;
    }

    public Recepcion() {
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public String getPallet() {
        return pallet;
    }

    public void setPallet(String pallet) {
        this.pallet = pallet;
    }

    public String getNombre_ubicacion() {
        return nombre_ubicacion;
    }

    public void setNombre_ubicacion(String nombre_ubicacion) {
        this.nombre_ubicacion = nombre_ubicacion;
    }

    public String getNombre_calidad() {
        return nombre_calidad;
    }

    public void setNombre_calidad(String nombre_calidad) {
        this.nombre_calidad = nombre_calidad;
    }

    public String getTipoproducto() {
        return tipoproducto;
    }

    public void setTipoproducto(String tipoproducto) {
        this.tipoproducto = tipoproducto;
    }

    public String getBandeja() {
        return bandeja;
    }

    public void setBandeja(String bandeja) {
        this.bandeja = bandeja;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getFecha_recepcion() {
        return fecha_recepcion;
    }

    public void setFecha_recepcion(String fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }
}

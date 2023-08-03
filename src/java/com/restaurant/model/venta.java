package com.restaurant.model;

public class venta extends cliente{

    private Integer idVenta;
    private Integer fkCliente;
    private String venCodigo;
    private String venFecha;
    private Double venTotal;

    public venta() {
    }

    public venta(Integer idVenta, Integer fkCliente, String venCodigo, String venFecha, Double venTotal) {
        this.idVenta = idVenta;
        this.fkCliente = fkCliente;
        this.venCodigo = venCodigo;
        this.venFecha = venFecha;
        this.venTotal = venTotal;
    }

    public venta(Integer fkCliente, String venCodigo, String venFecha, Double venTotal) {
        this.fkCliente = fkCliente;
        this.venCodigo = venCodigo;
        this.venFecha = venFecha;
        this.venTotal = venTotal;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(Integer fkCliente) {
        this.fkCliente = fkCliente;
    }

    public String getVenCodigo() {
        return venCodigo;
    }

    public void setVenCodigo(String venCodigo) {
        this.venCodigo = venCodigo;
    }

    public String getVenFecha() {
        return venFecha;
    }

    public void setVenFecha(String venFecha) {
        this.venFecha = venFecha;
    }

    public Double getDetTotal() {
        return venTotal;
    }

    public void setDetTotal(Double venTotal) {
        this.venTotal = venTotal;
    }

    @Override
    public String toString() {
        return "venta{" + "\n"
                + "idVenta=" + idVenta + "\n"
                + "fkCliente=" + fkCliente + "\n"
                + ", venCodigo=" + venCodigo + "\n"
                + ", venFecha=" + venFecha + "\n"
                + ", venTotal=" + venTotal + "\n"
                + '}';
    }

}

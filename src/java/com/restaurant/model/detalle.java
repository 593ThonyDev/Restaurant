package com.restaurant.model;

public class detalle extends venta {

    public detalle() {
    }

    private Integer idDetalle;
    private Integer fkVenta;
    private Integer fkProducto;
    private String proNombreDet;
    private String proCodigoDet;
    private Integer detCantidad;
    private Double detTotal;

    public detalle(Integer idDetalle, Integer fkVenta, Integer fkProducto, String proNombreDet, String proCodigoDet, Integer detCantidad, Double detTotal) {
        this.idDetalle = idDetalle;
        this.fkVenta = fkVenta;
        this.fkProducto = fkProducto;
        this.proNombreDet = proNombreDet;
        this.proCodigoDet = proCodigoDet;
        this.detCantidad = detCantidad;
        this.detTotal = detTotal;
    }

    public detalle(Integer fkVenta, Integer fkProducto, Integer detCantidad, Double detTotal) {
        this.fkVenta = fkVenta;
        this.fkProducto = fkProducto;
        this.detCantidad = detCantidad;
        this.detTotal = detTotal;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getFkVenta() {
        return fkVenta;
    }

    public void setFkVenta(Integer fkVenta) {
        this.fkVenta = fkVenta;
    }

    public Integer getFkProducto() {
        return fkProducto;
    }

    public void setFkProducto(Integer fkProducto) {
        this.fkProducto = fkProducto;
    }

    public Integer getDetCantidad() {
        return detCantidad;
    }

    public void setDetCantidad(Integer detCantidad) {
        this.detCantidad = detCantidad;
    }

    public Double getDetTotal() {
        return detTotal;
    }

    public void setDetTotal(Double detTotal) {
        this.detTotal = detTotal;
    }

    public String getProNombreDet() {
        return proNombreDet;
    }

    public void setProNombreDet(String proNombreDet) {
        this.proNombreDet = proNombreDet;
    }

    public String getProCodigoDet() {
        return proCodigoDet;
    }

    public void setProCodigoDet(String proCodigoDet) {
        this.proCodigoDet = proCodigoDet;
    }

    @Override
    public String toString() {
        return "detalle{" + "\n"
                + "idDetalle=" + idDetalle + "\n"
                + ", fkVenta=" + fkVenta + "\n"
                + ", fkProducto=" + fkProducto + "\n"
                + ", detCantidad=" + detCantidad + "\n"
                + ", detTotal=" + detTotal + "\n"
                + '}';
    }

}

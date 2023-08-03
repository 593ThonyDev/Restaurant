package com.restaurant.model;

public class menu {

    private Integer idMenu;
    private String menNombre;
    private String menCodigo;
    private Integer menStock;
    private Double menPrecio;

    public menu() {
    }

    public menu(Integer idMenu, String menNombre, String menCodigo, Integer menStock, Double menPrecio) {
        this.idMenu = idMenu;
        this.menNombre = menNombre;
        this.menCodigo = menCodigo;
        this.menStock = menStock;
        this.menPrecio = menPrecio;
    }

    public menu(String menNombre, String menCodigo, Integer menStock, Double menPrecio) {
        this.menNombre = menNombre;
        this.menCodigo = menCodigo;
        this.menStock = menStock;
        this.menPrecio = menPrecio;
    }

    

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getMenNombre() {
        return menNombre;
    }

    public void setMenNombre(String menNombre) {
        this.menNombre = menNombre;
    }

    public String getMenCodigo() {
        return menCodigo;
    }

    public void setMenCodigo(String menCodigo) {
        this.menCodigo = menCodigo;
    }

    public Integer getMenStock() {
        return menStock;
    }

    public void setMenStock(Integer menStock) {
        this.menStock = menStock;
    }

    public Double getMenPrecio() {
        return menPrecio;
    }

    public void setMenPrecio(Double menPrecio) {
        this.menPrecio = menPrecio;
    }

    @Override
    public String toString() {
        return "producto{" + "\n"
                + "idProducto=" + idMenu + "\n"
                + ", proNombre=" + menNombre + "\n"
                + ", proCodigo=" + menCodigo + "\n"
                + ", proStock=" + menStock + "\n"
                + ", proPrecio=" + menPrecio + "\n"
                + '}';
    }

}

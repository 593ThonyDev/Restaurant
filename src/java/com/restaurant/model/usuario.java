package com.restaurant.model;

public class usuario {

    private Integer idUsuario;
    private String usuUsuario;
    private String usuClave;
    private Integer usuIntento;

    public usuario() {
    }

    public usuario(Integer idUsuario, String usuUsuario, String usuClave, Integer usuIntento) {
        this.idUsuario = idUsuario;
        this.usuUsuario = usuUsuario;
        this.usuClave = usuClave;
        this.usuIntento = usuIntento;
    }

    public usuario(String usuUsuario, String usuClave, Integer usuIntento) {
        this.usuUsuario = usuUsuario;
        this.usuClave = usuClave;
        this.usuIntento = usuIntento;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuUsuario() {
        return usuUsuario;
    }

    public void setUsuUsuario(String usuUsuario) {
        this.usuUsuario = usuUsuario;
    }

    public String getUsuClave() {
        return usuClave;
    }

    public void setUsuClave(String usuClave) {
        this.usuClave = usuClave;
    }

    public Integer getUsuIntento() {
        return usuIntento;
    }

    public void setUsuIntento(Integer usuIntento) {
        this.usuIntento = usuIntento;
    }

    @Override
    public String toString() {
        return "usuario{" + "\n"
                + "idUsuario=" + idUsuario + "\n"
                + ", usuUsuario=" + usuUsuario + "\n"
                + ", usuClave=" + usuClave + "\n"
                + ", usuIntento=" + usuIntento + "\n"
                + '}';
    }

}

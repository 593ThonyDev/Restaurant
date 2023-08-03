package com.restaurant.model;

public class cliente {

    private Integer idCliente;
    private String cliNombre;
    private String cliApellido;
    private String cliCedula;
    private String cliEmail;
    private String cliTelefono;

    public cliente() {
    }

    public cliente(Integer idCliente, String cliNombre, String cliApellido, String cliCedula, String cliEmail, String cliTelefono) {
        this.idCliente = idCliente;
        this.cliNombre = cliNombre;
        this.cliApellido = cliApellido;
        this.cliCedula = cliCedula;
        this.cliEmail = cliEmail;
        this.cliTelefono = cliTelefono;
    }

    public cliente(String cliNombre, String cliApellido, String cliCedula, String cliEmail, String cliTelefono) {
        this.cliNombre = cliNombre;
        this.cliApellido = cliApellido;
        this.cliCedula = cliCedula;
        this.cliEmail = cliEmail;
        this.cliTelefono = cliTelefono;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getCliNombre() {
        return cliNombre;
    }

    public void setCliNombre(String cliNombre) {
        this.cliNombre = cliNombre;
    }

    public String getCliApellido() {
        return cliApellido;
    }

    public void setCliApellido(String cliApellido) {
        this.cliApellido = cliApellido;
    }

    public String getCliCedula() {
        return cliCedula;
    }

    public void setCliCedula(String cliCedula) {
        this.cliCedula = cliCedula;
    }

    public String getCliEmail() {
        return cliEmail;
    }

    public void setCliEmail(String cliEmail) {
        this.cliEmail = cliEmail;
    }

    public String getCliTelefono() {
        return cliTelefono;
    }

    public void setCliTelefono(String cliTelefono) {
        this.cliTelefono = cliTelefono;
    }

    @Override
    public String toString() {
        return "cliente{" + "\n"
                + "idCliente=" + idCliente + "\n"
                + ", cliNombre=" + cliNombre + "\n"
                + ", cliApellido=" + cliApellido + "\n"
                + ", cliCedula=" + cliCedula + "\n"
                + ", cliEmail=" + cliEmail + "\n"
                + ", cliTelefono=" + cliTelefono + "\n"
                + '}';
    }

}

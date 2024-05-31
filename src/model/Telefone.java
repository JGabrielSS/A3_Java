package model;

public class Telefone {

    private String principal;
    private String secundario;

    public Telefone() {
    }

    public Telefone(String principal) {
        this.principal = principal;
    }

    public Telefone(String principal, String secundario) {
        this.principal = principal;
        this.secundario = secundario;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getSecundario() {
        return secundario;
    }

    public void setSecundario(String secundario) {
        this.secundario = secundario;
    }
}

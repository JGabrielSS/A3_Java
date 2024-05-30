package model;

public class Atendente extends Pessoa{

    private String usuario;
    private String senha;

    public Atendente() {
    }

    public Atendente(String nome, String email, String cpf, String data_nasc, String usuario, String senha) {
        super(nome, email, cpf, data_nasc);
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}

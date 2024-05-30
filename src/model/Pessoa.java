package model;

public class Pessoa {
    private String nome;
    private String email;
    private String cpf;
    private String data_nasc;

    public Pessoa() {
    }

    public Pessoa(String nome, String email, String cpf, String data_nasc) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.data_nasc = data_nasc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }
}

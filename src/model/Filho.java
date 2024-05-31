package model;

public class Filho {
    private int matricula;
    private String nome;
    private String data_nasc;

    public Filho() {
    }

    public Filho(int matricula, String nome, String data_nasc) {
        this.matricula = matricula;
        this.nome = nome;
        this.data_nasc = data_nasc;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    @Override
    public String toString() {
        return "Filho{" +
                "\nmatricula=" + matricula +
                "\nnome='" + nome + '\'' +
                "\ndata_nasc='" + data_nasc + '\'' +
                '}';
    }
}

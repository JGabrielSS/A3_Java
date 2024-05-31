package model;

import java.sql.Date;

public class Responsavel extends Pessoa{

    public Responsavel() {
    }

    public Responsavel(String nome, String email, String cpf, String data_nasc) {
        super(nome, email, cpf, data_nasc);
    }

}

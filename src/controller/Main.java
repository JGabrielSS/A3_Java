package controller;

import model.*;
import model.DAO.*;
import util.DataUtil;

public class Main {
    public static void main(String[] args) {



        /* Pessoa p1 = PessoaDAO.inserir("Joao Pedro", "email@example.com", "12345678900", DataUtil.transformarData("21/04/2004"), "21991010760");
        Pessoa p2 = PessoaDAO.inserir("Joao Gabriel", "email@example.com", "12345678901", DataUtil.transformarData("21/04/2003"), "21991010761", "21975996008");
        Pessoa p3 = PessoaDAO.inserir("Joao Vitor", "email@example.com", "12345678902", DataUtil.transformarData("21/04/2002"), "21991010762");
        Pessoa p4 = PessoaDAO.inserir("Joao Paulo", "email@example.com", "12345678903", DataUtil.transformarData("21/04/2001"), "21991010763", "21975996001");
        Pessoa p5 = PessoaDAO.inserir("Joao Carlos", "email@example.com", "12345678904", DataUtil.transformarData("21/04/2000"), "21991010764");
        */

        /* Pessoa p2 = PessoaDAO.consultarPessoa("Joao Gabriel");
        System.out.println(p2);

        Pessoa p3 = PessoaDAO.consultarPessoa("Joao Vitor");
        System.out.println(p3); */

        // Pessoa p3 = PessoaDAO.atualizarAniversario(DataUtil.transformarData("21/05/2003"),"12345678901");

        // Telefone t1 = TelefoneDAO.atualizarTelefone("21975996009", "21975996000", "12345678901");

        // Pessoa p2 = PessoaDAO.excluir("12345678900");

        /* Atendente p2 = AtendenteDAO.inserir("Joao Gabriel", "email@example.com", "12345678905", DataUtil.transformarData("21/04/2003"), "21991010761", "21975996008", "jgabriel", "senha");
        Atendente p3 = AtendenteDAO.inserir("Joao Vitor", "email@example.com", "12345678906", DataUtil.transformarData("21/04/2002"), "21991010762", "jvitor", "senha");
        Atendente p4 = AtendenteDAO.inserir("Joao Paulo", "email@example.com", "12345678907", DataUtil.transformarData("21/04/2001"), "21991010763", "21975996001", "jpaulo", "senha");
        */

        // Atendente p3 = AtendenteDAO.consultar("Joao Vitor");
        // System.out.println(p3);

        // Atendente a1 = AtendenteDAO.atualizarCredenciais("jgabriel", "senha", "12345678905");

        // Atendente a1 = AtendenteDAO.excluir("12345678905");

        // Atendente a1 = AtendenteDAO.atualizarAniversario("21/07/1999","12345678906");

//        Responsavel p3 = ResponsavelDAO.inserir("Joao Vitor", "email@example.com", "12345678908", DataUtil.transformarData("21/04/2002"), "21991010762");
//        Responsavel p4 = ResponsavelDAO.inserir("Joao Paulo", "email@example.com", "12345678909", DataUtil.transformarData("21/04/2001"), "21991010763", "21975996001");
//        Responsavel p5 = ResponsavelDAO.inserir("Joao Carlos", "email@example.com", "12345678910", DataUtil.transformarData("21/04/2000"), "21991010764");


        // Responsavel p3 = ResponsavelDAO.consultar("Joao Vitor");
        // System.out.println(p3);

        // Responsavel r1 = ResponsavelDAO.excluir("12345678910");

        // Responsavel r2 = ResponsavelDAO.inserir("Alexandre Valadao", "email@example.com", "10123456169", "07/01/1950", "21912341234");
        Filho f2 = FilhoDAO.consultar("Renato Brigao");
        System.out.println(f2);

    }
}

package controller;

import model.*;
import model.DAO.*;
import util.DataUtil;
import util.Teclado;

import java.util.Objects;

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

//        Atendente p2 = AtendenteDAO.inserir("Joao Gabriel", "email@example.com", "12345678905", "2003-04-21", "21991010761", "21975996008", "jgabriel", "senha");
//        Atendente p3 = AtendenteDAO.inserir("Joao Vitor", "email@example.com", "12345678906", "2002-04-21", "21991010762", "jvitor", "senha");
//        Atendente p4 = AtendenteDAO.inserir("Joao Paulo", "email@example.com", "12345678907", "2001-01-29", "21991010763", "21975996001", "jpaulo", "senha");


        // Atendente p3 = AtendenteDAO.login("jvitor", "senha");
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
        // Filho f2 = FilhoDAO.consultar("Renato Brigao");
        // System.out.println(f2);

        System.out.println("""
                1 - Fazer login
                2 - Efetuar cadastro
                0 - Encerrar""");

        int opc = Teclado.lerInt("Opção escolhida: ");

        if (opc == 0) {
            encerrar();
        } else if (opc == 1) {
            iniciar();
        } else if (opc == 2) {
            cadastrarAtendente();
        }

    }

    public static void iniciar() {
        System.out.println("----- Para iniciar efetue o login -----");
        String usuario = Teclado.lerTexto("Usuario: ");
        String senha = Teclado.lerTexto("Senha: ");

        Atendente a1 = AtendenteDAO.login(usuario, senha);

        if (a1 == null) {
            usuario = Teclado.lerTexto("Usuario: ");
            senha = Teclado.lerTexto("Senha: ");
            a1 = AtendenteDAO.login(usuario, senha);
        } else {
            menuPrincipal();
        }
    }

    public static void menuPrincipal() {
        System.out.println("""
                1 - Cadastrar responsavel
                2 - Alterar dados do responsavel
                3 - Alterar dados da criança
                4 - Alterar dados de atendente
                5 - Excluir responsavel
                6 - Excluir crianca
                7 - Excluir atendente
                8 - Cadastrar atendente
                """);

        int opc = Teclado.lerInt("Opção escolhida: ");

        if (opc == 1) {
           cadastrarResponsavel();
        }
    }

    public static void cadastrarAtendente() {
        String nome = Teclado.lerTexto("Nome: ");
        String email = Teclado.lerTexto("E-mail: ");
        String cpf = Teclado.lerTexto("CPF: ");
        String data_nasc = Teclado.lerTexto("Data de nascimento (XX/XX/XXXX): ");
        String tel_principal = Teclado.lerTexto("Nº celular principal: ");
        String tel_secundario = Teclado.lerTexto("Nº celular secundario: ");

        if (tel_secundario == "") {
            tel_secundario = "vazio";
        }

        String usuario = Teclado.lerTexto("usuario: ");
        String senha = Teclado.lerTexto("senha: ");

        Atendente atendente = AtendenteDAO.inserir(nome, email, cpf, data_nasc, tel_principal, tel_secundario, usuario, senha);
        atendente = AtendenteDAO.login(usuario, senha);
    }

    public static void cadastrarResponsavel() {
        String nome = Teclado.lerTexto("Nome: ");
        String email = Teclado.lerTexto("E-mail: ");
        String cpf = Teclado.lerTexto("CPF: ");
        String data_nasc = Teclado.lerTexto("Data de nascimento (XX/XX/XXXX): ");
        String tel_principal = Teclado.lerTexto("Nº celular principal: ");
        String tel_secundario = Teclado.lerTexto("Nº celular secundario: ");

        if (Objects.equals(tel_secundario, "")) {
            tel_secundario = "vazio";
        }

        Responsavel responsavel = ResponsavelDAO.inserir(nome, email, cpf, data_nasc, tel_principal, tel_secundario);

        int qtd_filho = Teclado.lerInt("Insira a quantidade de filho que serão cadastrados: ");

        for (int i = 1; i <= qtd_filho; i++) {
            cadastrarFilho();
        }
    }

    public static void cadastrarFilho() {
        String nome = Teclado.lerTexto("Nome: ");
        String data_nasc = Teclado.lerTexto("Data de nascimento (XX/XX/XXXX): ");

        Filho filho = FilhoDAO.inserir(nome, data_nasc);

    }
    public static void encerrar() {
        System.exit(0);
    }

}

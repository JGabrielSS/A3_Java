package model.DAO;


import model.*;
import util.Conexao;
import util.DataUtil;
import util.Sessao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResponsavelDAO {

    public static Responsavel inserir(String nome, String email, String cpf, String data_nasc, String principal) {

        Responsavel responsavel = null;

        String sql = "INSERT INTO responsavel (cpfPessoa)" +
                "VALUES (?)";

        try {

            Pessoa pessoa = PessoaDAO.inserir(nome, email, cpf, data_nasc, principal);

            Conexao conex = new Conexao(
                    "jdbc:mysql://localhost:3306/A3_JA",
                    "com.mysql.cj.jdbc.Driver",
                    "root",
                    "root"
            );

            Connection conn = conex.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.setString(1, cpf);

            cmd.execute();

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        responsavel = new Responsavel(nome, email, cpf, data_nasc);
        System.out.println("Responsavel criado com sucesso!");
        return responsavel;
    }

    public static Responsavel inserir(String nome, String email, String cpf, String data_nasc, String principal, String secundario) {
        Responsavel responsavel = null;

        String sql = "INSERT INTO responsavel (cpfPessoa)" +
                "VALUES (?)";

        try {

            Pessoa pessoa = PessoaDAO.inserir(nome, email, cpf, data_nasc, principal, secundario);

            Conexao conex = new Conexao(
                    "jdbc:mysql://localhost:3306/A3_JA",
                    "com.mysql.cj.jdbc.Driver",
                    "root",
                    "root"
            );

            Connection conn = conex.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.setString(1, cpf);

            cmd.execute();

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        responsavel = new Responsavel(nome, email, cpf, data_nasc);

        return responsavel;
    }

    public static Responsavel consultar(String cpf) {
        Responsavel responsavel = null;

        String sql = "SELECT * FROM pessoa INNER JOIN atendente WHERE pessoa.cpf = atendente.cpfPessoa AND pessoa.cpf = ?";

        try {

            Pessoa pessoa = PessoaDAO.consultarPessoa(cpf);

            Conexao conex = new Conexao(
                    "jdbc:mysql://localhost:3306/A3_JA",
                    "com.mysql.cj.jdbc.Driver",
                    "root",
                    "root"
            );

            Connection conn = conex.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);

            cmd.setString(1, cpf);

            ResultSet rs = cmd.executeQuery();

            if (rs.next()) {
                responsavel = new Responsavel();
                Pessoa pessoaS = Sessao.pessoa;
                responsavel.setNome(pessoaS.getNome());
                responsavel.setEmail(pessoaS.getEmail());
                responsavel.setCpf(pessoaS.getCpf());
                responsavel.setData_nasc(pessoaS.getData_nasc());
                Sessao.responsavel = responsavel;
            }

            rs.close();
            cmd.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return responsavel;
    }

    public static String auxResp() {
        int maxResp = 0;
        String last_cpf = "";

        String sql = "SELECT max(idResponsavel) as lastResp FROM responsavel";

        String sql_aux = "SELECT cpfPessoa FROM responsavel WHERE idResponsavel = ?";

        try {
            Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/A3_JA", "com.mysql.cj.jdbc.Driver", "root", "root");

            Connection conn = conexao.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();

            if (rs.next()) {
                maxResp = rs.getInt("lastResp");
            }

            rs.close();
            cmd.close();

            PreparedStatement cmd_aux = conn.prepareStatement(sql_aux);
            cmd_aux.setInt(1, maxResp);

            ResultSet rs_aux = cmd_aux.executeQuery();

            if (rs_aux.next()) {
                last_cpf = rs_aux.getString("cpfPessoa");
            }
            rs_aux.close();
            cmd_aux.close();



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return last_cpf;
    }

    public static Responsavel atualizarAniversario(String data_nasc, String cpf) {
        Pessoa pessoa = PessoaDAO.atualizarAniversario(data_nasc, cpf);
        return null;
    }

    public static Responsavel excluir(String cpf) {
        Responsavel responsavel = null;
        String sql = "DELETE FROM responsavel WHERE cpfPessoa = ?";
        try {
            Conexao conex = new Conexao(
                    "jdbc:mysql://localhost:3306/A3_JA",
                    "com.mysql.cj.jdbc.Driver",
                    "root",
                    "root"
            );

            Connection conn = conex.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.setString(1, cpf);

            cmd.executeUpdate();

            Pessoa pessoa = PessoaDAO.excluir(cpf);

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return responsavel;
    }

}

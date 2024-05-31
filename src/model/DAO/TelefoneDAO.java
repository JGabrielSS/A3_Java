package model.DAO;

import model.Pessoa;
import model.Telefone;
import util.Conexao;
import util.Sessao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelefoneDAO {

    public static Telefone inserirTelefone(String principal, String cpfPessoa) {
        Telefone telefone = null;

        String sql = "INSERT INTO telefone (principal, cpfPessoa)" + "VALUES (?, ?)";

        try {
            Conexao conex = new Conexao("jdbc:mysql://localhost:3306/A3_JA", "com.mysql.cj.jdbc.Driver", "root", "root");

            Connection conn = conex.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.setString(1, principal);
            cmd.setString(2, cpfPessoa);

            cmd.execute();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return telefone;
    }

    public static Telefone inserirTelefone(String principal, String secundario, String cpfPessoa) {
        Telefone telefone = null;

        String sql = "INSERT INTO telefone (principal, secundario, cpfPessoa)" + "VALUES (?, ?, ?)";

        try {
            Conexao conex = new Conexao("jdbc:mysql://localhost:3306/A3_JA", "com.mysql.cj.jdbc.Driver", "root", "root");

            Connection conn = conex.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.setString(1, principal);
            cmd.setString(2, secundario);
            cmd.setString(3, cpfPessoa);

            cmd.execute();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return telefone;
    }

    public static Telefone atualizarTelefone(String principal, String cpf) {
        Telefone telefone = null;
        //cpf = Sessao.pessoa.getCpf();
        String sql = "UPDATE telefone INNER JOIN pessoa SET principal = ? WHERE telefone.cpfPessoa = pessoa.cpf AND pessoa.cpf = ?";

        try {
            Conexao conex = new Conexao("jdbc:mysql://localhost:3306/A3_JA", "com.mysql.cj.jdbc.Driver", "root", "root");

            Connection conn = conex.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.setString(1, principal);
            cmd.setString(2, cpf);

            conn.setAutoCommit(false);
            cmd.executeUpdate();
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return telefone;
    }

    public static Telefone atualizarTelefone(String principal, String secundario, String cpf) {
        Telefone telefone = null;
        String sql = "UPDATE telefone INNER JOIN pessoa SET principal = ?, secundario = ? WHERE telefone.cpfPessoa = pessoa.cpf AND pessoa.cpf = ?";

        try {
            Conexao conex = new Conexao("jdbc:mysql://localhost:3306/A3_JA", "com.mysql.cj.jdbc.Driver", "root", "root");

            Connection conn = conex.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.setString(1, principal);
            cmd.setString(2, secundario);
            cmd.setString(3, cpf);

            conn.setAutoCommit(false);
            cmd.executeUpdate();
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return telefone;
    }

    public static Telefone excluir(String cpf) {
        Telefone telefone = null;

        String sql = "DELETE FROM telefone WHERE telefone.cpfPessoa = ?";

        try {
            Conexao conex = new Conexao("jdbc:mysql://localhost:3306/A3_JA", "com.mysql.cj.jdbc.Driver", "root", "root");

            Connection conn = conex.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.setString(1, cpf);

            cmd.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return telefone;
    }

}

package model.DAO;

import model.Atendente;
import model.Pessoa;
import model.Telefone;
import util.Conexao;
import util.DataUtil;
import util.Sessao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AtendenteDAO {

    public static Atendente inserir(String nome, String email, String cpf, String data_nasc, String principal, String usuario, String senha) {

        Atendente atendente = null;

        String sql = "INSERT INTO atendente (usuario, senha, cpfPessoa)" +
                "VALUES (?, ?, ?)";

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
            cmd.setString(1, usuario);
            cmd.setString(2, senha);
            cmd.setString(3, cpf);


            cmd.execute();

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        atendente = new Atendente(nome, email, cpf, data_nasc, usuario, senha);
        System.out.println("Atendente criado com sucesso!");
        return atendente;
    }

    public static Atendente inserir(String nome, String email, String cpf, String data_nasc, String principal, String secundario, String usuario, String senha) {
        Atendente atendente = null;

        String sql = "INSERT INTO atendente (usuario, senha, cpfPessoa)" +
                "VALUES (?, ?, ?)";

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
            cmd.setString(1, usuario);
            cmd.setString(2, senha);
            cmd.setString(3, cpf);

            cmd.execute();

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        atendente = new Atendente(nome, email, cpf, data_nasc, usuario, senha);
        System.out.println("Atendente criado com sucesso!");
        return atendente;
    }

    public static Atendente consultar(String cpf) {
        Atendente atendente = null;

        String sql = "SELECT * FROM pessoa INNER JOIN atendente WHERE pessoa.cpf = atendente.cpfPessoa AND pessoa.cpf = ?";

        try {

            Pessoa pessoa = PessoaDAO.consultarPessoa(cpf);

            Conexao conexao = new Conexao(
                    "jdbc:mysql://localhost:3306/A3_JA",
                    "com.mysql.cj.jdbc.Driver",
                    "root",
                    "root"
            );

            Connection conn = conexao.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);

            cmd.setString(1, cpf);

            ResultSet rs = cmd.executeQuery();

            if (rs.next()) {
                atendente = new Atendente();
                Pessoa pessoaS = Sessao.pessoa;
                atendente.setNome(pessoaS.getNome());
                atendente.setEmail(pessoaS.getEmail());
                atendente.setCpf(pessoaS.getCpf());
                atendente.setData_nasc(pessoaS.getData_nasc());
                atendente.setUsuario(rs.getString("usuario"));
                atendente.setSenha(rs.getString("senha"));
                Sessao.atendente = atendente;
            }

            rs.close();
            cmd.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(atendente);
        return atendente;
    }

    public static Atendente atualizarCredenciais(String usuario, String senha, String cpf) {

        Atendente atendente = null;
        String sql = "UPDATE pessoa INNER JOIN atendente SET usuario = ?, senha = ? WHERE pessoa.cpf =  atendente.cpfPessoa AND atendente.cpfPessoa = ?";

        try {
            Conexao conex = new Conexao(
                    "jdbc:mysql://localhost:3306/A3_JA",
                    "com.mysql.cj.jdbc.Driver",
                    "root",
                    "root"
            );

            Connection conn = conex.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.setString(1, usuario);
            cmd.setString(2, senha);
            cmd.setString(3, cpf);

            conn.setAutoCommit(false);
            cmd.executeUpdate();
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return atendente;
    }

    public static Atendente atualizarAniversario(String data_nasc, String cpf) {
        Pessoa pessoa = PessoaDAO.atualizarAniversario(data_nasc, cpf);
        return null;
    }

    public static Atendente excluir(String cpf) {
        Atendente atendente = null;
        String sql = "DELETE FROM atendente WHERE cpfPessoa = ?";
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
        return atendente;
    }

    public static Atendente login(String usuario, String senha) {
        Atendente atendente = null;

        String sql = "SELECT * FROM atendente WHERE usuario = ? AND senha = ?";
        try {

            Conexao conexao = new Conexao(
                    "jdbc:mysql://localhost:3306/A3_JA",
                    "com.mysql.cj.jdbc.Driver",
                    "root",
                    "root"
            );

            Connection conn = conexao.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);

            cmd.setString(1, usuario);
            cmd.setString(2, senha);

            ResultSet rs = cmd.executeQuery();

            if (rs.next()) {
                Pessoa pessoa = PessoaDAO.consultarPessoa(rs.getString("cpfPessoa"));
                Pessoa pessoaS = Sessao.pessoa;

                atendente = new Atendente();
                atendente.setNome(pessoaS.getNome());
                atendente.setEmail(pessoaS.getEmail());
                atendente.setCpf(pessoaS.getCpf());
                atendente.setData_nasc(pessoaS.getData_nasc());
                atendente.setUsuario(rs.getString("usuario"));
                atendente.setSenha(rs.getString("senha"));

                Sessao.atendente = atendente;
                System.out.println("Bem-vindo, " + atendente.getNome() + "!");
            } else {
                System.out.println("Usuário e/ou senha incorretos");
            }

            rs.close();
            cmd.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return atendente;
    }
}

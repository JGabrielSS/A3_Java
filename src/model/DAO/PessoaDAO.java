package model.DAO;

import model.Pessoa;
import model.Telefone;
import util.Conexao;
import util.DataUtil;
import util.Sessao;

import java.sql.*;

public class PessoaDAO {

    public static Pessoa inserir(String nome, String email, String cpf, String data_nasc, String principal) {
        Pessoa pessoa = null;
        try {
            String sql = "INSERT INTO pessoa (nome, email, cpf, data_nasc)" + "VALUES (?, ?, ?, ?)";

            Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/A3_JA", "com.mysql.cj.jdbc.Driver", "root", "root");

            Connection conn = conexao.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.setString(1, nome);
            cmd.setString(2, email);
            cmd.setString(3, cpf);
            cmd.setString(4, data_nasc);

            cmd.execute();

            Telefone telefone = TelefoneDAO.inserirTelefone(principal, cpf);

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        pessoa = new Pessoa(nome, email, cpf, data_nasc);
        return pessoa;
    }

    public static Pessoa inserir(String nome, String email, String cpf, String data_nasc, String principal, String secundario) {
        Pessoa pessoa = null;

        try {
            String sql = "INSERT INTO pessoa (nome, email, cpf, data_nasc)" + "VALUES (?, ?, ?, ?)";

            Conexao conex = new Conexao("jdbc:mysql://localhost:3306/A3_JA", "com.mysql.cj.jdbc.Driver", "root", "root");

            Connection conn = conex.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.setString(1, nome);
            cmd.setString(2, email);
            cmd.setString(3, cpf);
            cmd.setString(4, DataUtil.transformarData(data_nasc));

            cmd.execute();

            Telefone telefone = TelefoneDAO.inserirTelefone(principal, secundario, cpf);

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        pessoa = new Pessoa(nome, email, cpf, data_nasc);
        return pessoa;
    }

    public static Pessoa consultarPessoa(String cpf) {
        Telefone telefone = null;
        Pessoa pessoa = null;

        String sql = "SELECT * FROM pessoa INNER JOIN telefone WHERE pessoa.cpf = telefone.cpfPessoa AND pessoa.cpf = ?";

        try {
            Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/A3_JA", "com.mysql.cj.jdbc.Driver", "root", "root");

            Connection conn = conexao.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);

            cmd.setString(1, cpf);

            ResultSet rs = cmd.executeQuery();

            if (rs.next()) {
                pessoa = new Pessoa();
                telefone = new Telefone();

                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setData_nasc(DataUtil.converterData(rs.getString("data_nasc")));
                telefone.setPrincipal(rs.getString("principal"));
                if ((rs.getString("secundario") == null)) {
                    telefone.setSecundario("vazio");
                } else {
                    telefone.setSecundario(rs.getString("secundario"));
                }
                Sessao.telefone = telefone;
                Sessao.pessoa = pessoa;

            }

            rs.close();
            cmd.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        //System.out.println(pessoa);
        return pessoa;
    }

    public static Pessoa atualizarAniversario(String data_nasc, String cpf) {

        Pessoa pessoa = null;
        String sql = "UPDATE pessoa SET data_nasc = ? WHERE pessoa.cpf = ?";

        try {
            Conexao conex = new Conexao("jdbc:mysql://localhost:3306/A3_JA", "com.mysql.cj.jdbc.Driver", "root", "root");

            Connection conn = conex.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.setString(1, DataUtil.transformarData(data_nasc));
            cmd.setString(2, cpf);

            conn.setAutoCommit(false);
            cmd.executeUpdate();
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        consultarPessoa(cpf);
        System.out.println("Alteração realizada com sucesso!");
        return pessoa;
    }

    public static Pessoa excluir(String cpf) {
        Pessoa pessoa = null;
        String sql = "DELETE FROM pessoa WHERE cpf = ?";
        try {
            Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/A3_JA", "com.mysql.cj.jdbc.Driver", "root", "root");

            Connection conn = conexao.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.setString(1, cpf);

            Telefone telefone = TelefoneDAO.excluir(cpf);

            cmd.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Cadastro excluído com sucesso!");
        return pessoa;
    }

}

package model.DAO;

import model.*;
import util.Conexao;
import util.DataUtil;
import util.Sessao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilhoDAO {

    public static Filho inserir(String nome, String data_nasc) {
        Filho filho = null;

        String sql = "INSERT INTO filho (nome, data_nasc)" + "VALUES (?, ?)";

        try {

            Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/A3_JA", "com.mysql.cj.jdbc.Driver", "root", "root");

            Connection conn = conexao.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.setString(1, nome);
            cmd.setString(2, DataUtil.transformarData(data_nasc));

            cmd.execute();

            //Filho filhao = FilhoDAO.consultar(nome);

            conn.close();

        } catch (SQLException e) {
            System.out.printf(e.getMessage());
        }
        Filho filhao = Sessao.filho;
        filho = new Filho(filhao.getMatricula(), nome, data_nasc);
        System.out.println("Filho adicionado com sucesso!");

        Filiacao filiacao = FiliacaoDAO.gerarFiliacao(FilhoDAO.auxFilho(), ResponsavelDAO.auxResp());

        return filho;
    }

    public static Filho consultar(String nome) {
        Filho filho = null;

//        String sql = "SELECT * FROM filho\n" +
//                "INNER JOIN filiacao\n" +
//                "INNER JOIN responsavel\n" +
//                "INNER JOIN pessoa\n" +
//                "WHERE filho.matricula = filiacao.matricula\n" +
//                "AND filiacao.cpfResponsavel = responsavel.cpfPessoa\n" +
//                "AND responsavel.cpfPessoa = pessoa.cpf;";

        String sql = "SELECT * FROM filho WHERE nome = ?";

        try {

            //Responsavel responsavel = ResponsavelDAO.consultar("");
            Responsavel resp = Sessao.responsavel;

            Conexao conex = new Conexao("jdbc:mysql://localhost:3306/A3_JA", "com.mysql.cj.jdbc.Driver", "root", "root");

            Connection conn = conex.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);

            cmd.setString(1, nome);

            ResultSet rs = cmd.executeQuery();

            if (rs.next()) {
                filho = new Filho();

                filho.setMatricula(rs.getInt("matricula"));
                filho.setNome(rs.getString("nome"));
                filho.setData_nasc(DataUtil.converterData(rs.getString("data_nasc")));

                Sessao.filho = filho;

            }

            rs.close();
            cmd.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(filho);
        return filho;
    }

    public static int auxFilho() {
        int maxFilho = 0;

        String sql = "SELECT max(matricula) as lastFilho FROM filho";

        try {
            Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/A3_JA", "com.mysql.cj.jdbc.Driver", "root", "root");

            Connection conn = conexao.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();

            if (rs.next()) {
                maxFilho = rs.getInt("lastFilho");
            }

            rs.close();
            cmd.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return maxFilho;
    }

    public static Filho alterar(String data_nasc ,String nome) {
        Filho filho = null;

        String sql = "UPDATE filho SET data_nasc = ? WHERE nome = ?";

        try {
            Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/A3_JA", "com.mysql.cj.jdbc.Driver", "root", "root");

            Connection conn = conexao.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.setString(1, DataUtil.transformarData(data_nasc));
            cmd.setString(2, nome);

            conn.setAutoCommit(false);
            cmd.executeUpdate();
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Dados alterados com sucesso!");
        return filho;
    }

    public static Filho excluir(int matricula, String nome) {
        Filho filho = null;

        String sql = "DELETE FROM filho WHERE matricula = ? AND nome = ?";

        try {
            Conexao conex = new Conexao("jdbc:mysql://localhost:3306/A3_JA", "com.mysql.cj.jdbc.Driver", "root", "root");

            Connection conn = conex.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.setInt(1, matricula);
            cmd.setString(2, nome);

            cmd.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Filho excluido com sucesso!");
        return filho;
    }

}

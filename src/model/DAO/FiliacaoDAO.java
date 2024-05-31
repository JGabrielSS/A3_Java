package model.DAO;

import model.Filho;
import model.Filiacao;
import model.Responsavel;
import util.Conexao;
import util.Sessao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FiliacaoDAO {

    public static Filiacao gerarFiliacao(int matricula, String cpfResponsavel) {

        Filiacao filiacao = null;

        String sql = "INSERT INTO filiacao (matricula, cpfResponsavel)" +
                "VALUES (?, ?)";

        try {


            Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/A3_JA", "com.mysql.cj.jdbc.Driver", "root", "root");

            Connection conn = conexao.conectar();

            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.setInt(1, matricula);
            cmd.setString(2, cpfResponsavel);

            cmd.execute();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Filiacao gerada com sucesso!");
        return filiacao;
    }

}

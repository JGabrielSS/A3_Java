package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private String url, driver, login, senha;

    public Conexao(String url, String driver, String login, String senha) {
        try {
            this.url = url;
            this.driver = driver;
            this.login = login;
            this.senha = senha;
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Connection conectar() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, login, senha);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}

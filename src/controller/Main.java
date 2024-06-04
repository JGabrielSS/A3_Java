package controller;

import model.*;
import model.DAO.*;
import util.DataUtil;
import util.Sessao;
import util.Teclado;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        iniciar();

    }

    public static void iniciar() {
        System.out.println("""
                1 - Fazer login
                2 - Efetuar cadastro
                0 - Encerrar programa""");

        int opc = Teclado.lerInt("Opção escolhida: ");

        if (opc == 0) {
            encerrar();
        } else if (opc == 1) {
            login();
        } else if (opc == 2) {
            cadastrarAtendente();
        }
    }

    public static void login() {
        String usuario = Teclado.lerTexto("Usuario: ");
        String senha = Teclado.lerTexto("Senha: ");

        Atendente a1 = AtendenteDAO.login(usuario, senha);

        if (a1 == null) {
            System.out.println("Caso erre novamente será necessário executar o código novamente");
            usuario = Teclado.lerTexto("Usuario: ");
            senha = Teclado.lerTexto("Senha: ");
            a1 = AtendenteDAO.login(usuario, senha);
        }
        if (a1 != null) {
            menuPrincipal();
        }
    }

    public static void menuPrincipal() {
        System.out.println("""
                1 - Cadastrar responsável
                2 - Alterar dados do responsável
                3 - Consultar dados do responsável
                4 - Consultar criança
                5 - Alterar dados da criança
                6 - Excluir dados da criança
                7 - Cadastrar atendente
                8 - Alterar dados do atendente
                9 - Excluir dados do atendente
                10 - Calcular estadia da criança
                0 - Encerrar programa""");

        int opc = Teclado.lerInt("Opção escolhida: ");

        if (opc == 0) {
            encerrar();
        } else if (opc == 1) {
            cadastrarResponsavel();
        } else if (opc == 2) {
            alterarResponsavel();
        } else if (opc == 3) {
            consultarResponsavel();
            Responsavel responsavel = Sessao.responsavel;
            responsavel.toString();
            menuPrincipal();
        } else if (opc == 4) {
            consultarCrianca();
            menuPrincipal();
        } else if (opc == 5) {
            alterarCrianca();
        } else if (opc == 6) {
            excluirCrianca();
        } else if (opc == 7) {
            cadastrarAtendente();
        } else if (opc == 8) {
            alterarAtendente();
        } else if (opc == 9) {
            excluirAtendente();
            menuPrincipal();
        } else if (opc == 10) {
            calcularValorEstadia();
        }
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

    public static void alterarResponsavel() {
        String cpf = Teclado.lerTexto("Insira o CPF do responsável: ");
        String novo_aniversario = Teclado.lerTexto("Insira a nova data de aniversário (XX/XX/XXXX): ");

        Responsavel responsavel = ResponsavelDAO.atualizarAniversario(novo_aniversario, cpf);

        menuPrincipal();
    }

    public static Responsavel consultarResponsavel() {
        String cpf = Teclado.lerTexto("Insira o CPF do responsável: ");

        return ResponsavelDAO.consultar(cpf);
    }

    public static void cadastrarFilho() {
        String nome = Teclado.lerTexto("Nome: ");
        String data_nasc = Teclado.lerTexto("Data de nascimento (XX/XX/XXXX): ");

        Filho filho = FilhoDAO.inserir(nome, data_nasc);

        menuPrincipal();

    }

    public static Filho consultarCrianca() {
        String nome = Teclado.lerTexto("Insira o nome da criança: ");

        return FilhoDAO.consultar(nome);
    }

    public static void alterarCrianca() {
        String nome = Teclado.lerTexto("Insira o nome da criança: ");
        String novo_aniversario = Teclado.lerTexto("Insira a nova data de aniversário (XX/XX/XXXX): ");

        Filho filho = FilhoDAO.alterar(novo_aniversario, nome);

        menuPrincipal();
    }

    public static void excluirCrianca() {
        consultarResponsavel();
        Responsavel responsavel = Sessao.responsavel;
        responsavel.toString();
        consultarCrianca();

        Filho filho = Sessao.filho;
        responsavel.toString();
        filho.toString();

        int opc = Teclado.lerInt("""
                1 - Excluir criança (também apagará os dados dos responsáveis)
                2 - Nova busca""");

        if (opc == 1) {
            Filiacao filiacao = FiliacaoDAO.excluir(filho.getMatricula(), responsavel.getCpf());
            Filho f1 = FilhoDAO.excluir(filho.getMatricula(), filho.getNome());
            Responsavel resp = ResponsavelDAO.excluir(responsavel.getCpf());
            menuPrincipal();
        } else if (opc == 2) {
            excluirCrianca();
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

        iniciar();

    }

    public static void alterarAtendente() {
        String cpf = Teclado.lerTexto("Digite o CPF do atendente: ");
        String usuario = Teclado.lerTexto("Insira o usuario do atendente: ");
        String senha = Teclado.lerTexto("Insira a nova senha: ");

        Atendente atendente = AtendenteDAO.atualizarCredenciais(usuario, senha, cpf);

        menuPrincipal();
    }

    public static Atendente excluirAtendente() {
        String cpf = Teclado.lerTexto("Digite o CPF do atendente: ");

        return AtendenteDAO.excluir(cpf);
    }

    public static void encerrar() {
        System.exit(0);
    }

    public static void calcularValorEstadia() {
        int hora_inicio = Teclado.lerInt("Digite a hora, sem pontuação, que a criança entrou no brinquedo:");
        int minuto_inicio = Teclado.lerInt("Digite o minuto, sem pontuação, que a criança entrou no brinquedo:");

        int hora_termino = Teclado.lerInt("Digite a hora, sem pontuação, que a criança saiu no brinquedo:");
        int minuto_termino = Teclado.lerInt("Digite o minuto, sem pontuação, que a criança saiu no brinquedo:");

        int tarifa15 = 20, tarifa30 = 37, tarifa45 = 51, tarifa60 = 65, tarifa75 = 79, tarifa90 = 93, tarifa105 = 107, tarifa120 = 120;
        int tempo_total = 0, minuto_sobressalente = 0;
        hora_inicio = hora_inicio * 60;
        hora_termino = hora_termino * 60;

        int minutos_totais = minuto_termino - minuto_inicio;

        if (minutos_totais < 0) {
            minuto_sobressalente = minuto_termino - minuto_inicio;
        } else if (minutos_totais >= 60) {
            hora_termino += 1;
            minutos_totais -= 60;
        }

        tempo_total = (hora_termino - hora_inicio) + minutos_totais + minuto_sobressalente;
        double valor_total = 0;

        if (tempo_total == 15) {
            valor_total = tarifa15;
            System.out.println("O valor ficou em R$ " + valor_total);
        }else if (tempo_total == 30) {
            valor_total = tarifa30;
            System.out.println("O valor ficou em R$ " + valor_total);
        }else if (tempo_total == 45) {
            valor_total = tarifa45;
            System.out.println("O valor ficou em R$ " + valor_total);
        }else if (tempo_total == 60) {
            valor_total = tarifa60;
            System.out.println("O valor ficou em R$ " + valor_total);
        }else if (tempo_total == 75) {
            valor_total = tarifa75;
            System.out.println("O valor ficou em R$ " + valor_total);
        }else if (tempo_total == 90) {
            valor_total = tarifa90;
            System.out.println("O valor ficou em R$ " + valor_total);
        }else if (tempo_total == 105) {
            valor_total = tarifa105;
            System.out.println("O valor ficou em R$ " + valor_total);
        }else if (tempo_total == 120) {
            valor_total = tarifa120;
            System.out.println("O valor ficou em R$ " + valor_total);
        }

        if (tempo_total < 15) {
            valor_total = tempo_total * 2.0;
            System.out.println("O valor ficou em R$ " + valor_total);
        } else if (tempo_total > 15 && tempo_total < 30) {
            valor_total = tarifa15 + ((tempo_total - 15) * 2.0);
            System.out.println("O valor ficou em R$ " + valor_total);
        } else if (tempo_total > 30 && tempo_total < 45) {
            valor_total = tarifa30 + ((tempo_total - 30) * 2.0);
            System.out.println("O valor ficou em R$ " + valor_total);
        } else if (tempo_total > 45 && tempo_total < 60) {
            valor_total = tarifa45 + ((tempo_total - 45) * 2.0);
            System.out.println("O valor ficou em R$ " + valor_total);
        } else if (tempo_total > 60 && tempo_total < 75) {
            valor_total = tarifa60 + ((tempo_total - 60) * 2.0);
            System.out.println("O valor ficou em R$ " + valor_total);
        } else if (tempo_total > 75 && tempo_total < 90) {
            valor_total = tarifa75 + ((tempo_total - 75) * 2.0);
            System.out.println("O valor ficou em R$ " + valor_total);
        } else if (tempo_total > 90 && tempo_total < 105) {
            valor_total = tarifa90 + ((tempo_total - 90) * 2.0);
            System.out.println("O valor ficou em R$ " + valor_total);
        } else if (tempo_total > 105 && tempo_total < 120) {
            valor_total = tarifa105 + ((tempo_total - 105) * 2.0);
            System.out.println("O valor ficou em R$ " + valor_total);
        } else if (tempo_total > 120) {
            valor_total = tarifa120 + ((tempo_total - 120) * 2.0);
            System.out.println("O valor ficou em R$ " + valor_total);
        }
    }

}

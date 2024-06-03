package model;

import util.Teclado;

public class Atracao {

    private String nome;
    private int capacidade_max;
    private int tarifa15 = 20, tarifa30 = 37, tarifa45 = 51, tarifa60 = 65, tarifa75 = 79, tarifa90 = 93, tarifa105 = 107, tarifa120 = 120;

    public Atracao() {
    }

    public Atracao(String nome, int capacidade_max) {
        this.nome = nome;
        this.capacidade_max = capacidade_max;
    }

    public int getTarifa15() {
        return tarifa15;
    }

    public void setTarifa15(int tarifa15) {
        this.tarifa15 = tarifa15;
    }

    public int getTarifa30() {
        return tarifa30;
    }

    public void setTarifa30(int tarifa30) {
        this.tarifa30 = tarifa30;
    }

    public int getTarifa45() {
        return tarifa45;
    }

    public void setTarifa45(int tarifa45) {
        this.tarifa45 = tarifa45;
    }

    public int getTarifa60() {
        return tarifa60;
    }

    public void setTarifa60(int tarifa60) {
        this.tarifa60 = tarifa60;
    }

    public int getTarifa75() {
        return tarifa75;
    }

    public void setTarifa75(int tarifa75) {
        this.tarifa75 = tarifa75;
    }

    public int getTarifa90() {
        return tarifa90;
    }

    public void setTarifa90(int tarifa90) {
        this.tarifa90 = tarifa90;
    }

    public int getTarifa105() {
        return tarifa105;
    }

    public void setTarifa105(int tarifa105) {
        this.tarifa105 = tarifa105;
    }

    public int getTarifa120() {
        return tarifa120;
    }

    public void setTarifa120(int tarifa120) {
        this.tarifa120 = tarifa120;
    }

    public int alterarTarifa() {
        int valorSelecionado = 0, selecao = 0;

        Teclado.lerInt("""
                    ----- Selecione o número correspondente à tarifa que deseja alterar -----
                    \t 1 - Tarifa de 15 min
                    \t 2 - Tarifa de 30 min
                    \t 3 - Tarifa de 45 min
                    \t 4 - Tarifa de 60 min
                    \t 5 - Tarifa de 75 min
                    \t 6 - Tarifa de 90 min
                    \t 7 - Tarifa de 105 min
                    \t 8 - Tarifa de 120 min
                """);

        if (selecao == 1) {
            valorSelecionado = Teclado.lerInt("Insira o novo valor para a tarifa 1: ");
            this.setTarifa15(valorSelecionado);
        } else if (selecao == 2) {
            valorSelecionado = Teclado.lerInt("Insira o novo valor para a tarifa 2: ");
            this.setTarifa30(valorSelecionado);
        } else if (selecao == 3) {
            valorSelecionado = Teclado.lerInt("Insira o novo valor para a tarifa 3: ");
            this.setTarifa30(valorSelecionado);
        } else if (selecao == 4) {
            valorSelecionado = Teclado.lerInt("Insira o novo valor para a tarifa 4: ");
            this.setTarifa30(valorSelecionado);
        } else if (selecao == 5) {
            valorSelecionado = Teclado.lerInt("Insira o novo valor para a tarifa 5: ");
            this.setTarifa30(valorSelecionado);
        } else if (selecao == 6) {
            valorSelecionado = Teclado.lerInt("Insira o novo valor para a tarifa 6: ");
            this.setTarifa30(valorSelecionado);
        } else if (selecao == 7) {
            valorSelecionado = Teclado.lerInt("Insira o novo valor para a tarifa 7: ");
            this.setTarifa30(valorSelecionado);
        } else if (selecao == 8) {
            valorSelecionado = Teclado.lerInt("Insira o novo valor para a tarifa 8: ");
            this.setTarifa30(valorSelecionado);
        }

        System.out.println("Você alterou a tarifa " + selecao + " para o valor de R$ " + String.valueOf(valorSelecionado));

        return valorSelecionado;
    }
}

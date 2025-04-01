package org.example;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    String[][] tabuleiro = new String[3][3];
    private int jogador = 0; // 0 = O; 1 = X
    private int parada = 1;
    private int cont = 0;

    public Game() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = " ";
            }
        }
    }

    public void executar() {
        do {
            Tela();
            verificarJogador();
            jogada();
            verificarPartida();
        } while (parada != 0);
    }

    public void Tela() {
        System.out.println("   0   1   2 ");
        for (int i = 0; i < 3; i++) {
            System.out.println(i + ": " + tabuleiro[i][0] + " | " + tabuleiro[i][1] + " | " + tabuleiro[i][2]);
        }
    }

    public void verificarJogador() {
        jogador = (jogador == 0) ? 1 : 0;
    }

    public void jogada() {
        int linhaJogada, colunaJogada;
        do {
            System.out.println("Digite a Linha escolhida: ");
            linhaJogada = scanner.nextInt();
            System.out.println("Digite a Coluna escolhida: ");
            colunaJogada = scanner.nextInt();

            if (linhaJogada >= 0 && linhaJogada < 3 && colunaJogada >= 0 && colunaJogada < 3 && tabuleiro[linhaJogada][colunaJogada] == " ") {
                preencherTabuleiro(linhaJogada, colunaJogada);
                cont++;
                break;
            } else {
                System.out.println("Jogada inválida! Tente novamente...");
            }
        } while (true);
    }

    public void preencherTabuleiro(int linha, int coluna) {
        tabuleiro[linha][coluna] = (jogador == 0) ? "O" : "X";
    }

    public void verificarPartida() {
        // Verificar linhas e colunas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][0] == tabuleiro[i][2] && tabuleiro[i][0] != " ") {
                anunciarVencedor(tabuleiro[i][0]);
                return;
            }
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[0][i] == tabuleiro[2][i] && tabuleiro[0][i] != " ") {
                anunciarVencedor(tabuleiro[0][i]);
                return;
            }
        }

        // Verificar diagonais
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[0][0] == tabuleiro[2][2] && tabuleiro[0][0] != " ") {
            anunciarVencedor(tabuleiro[0][0]);
            return;
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[0][2] == tabuleiro[2][0] && tabuleiro[0][2] != " ") {
            anunciarVencedor(tabuleiro[0][2]);
            return;
        }

        // Verificar empate
        if (cont == 9) {
            Tela();
            System.out.println("Empate!");
            parada = 0;
        }
    }

    private void anunciarVencedor(String vencedor) {
        Tela();
        System.out.println("O '" + vencedor + "' Venceu!!");
        parada = 0;
    }
}
